package SOP.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/product")
public class ProductController {
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Category> categorys = new ArrayList<Category>();

	public ProductController() {
		File f = new File("product.dat");
		if (f.exists()) {
			ReadFile();
		} else {
			WriteFile(null);
		}
	}

	// Get Product by Index
	@RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("productId") int productId) {
		Product product = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
				break;
			}
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// Get All Product
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getAll() {
		return new ResponseEntity<ArrayList<Product>>(products, HttpStatus.OK);
	}

	// Get Product by Category
	@RequestMapping(value = "/get/category/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
		ArrayList<Product> product_category = new ArrayList<Product>();
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < products.get(i).getCategorys().size(); j++) {
				if (products.get(i).getCategorys().get(j).getId() == categoryId) {
					product_category.add(products.get(i));
				}
			}
		}
		return new ResponseEntity<ArrayList<Product>>(product_category, HttpStatus.OK);
	}

	// Get Product Options
	@RequestMapping(value = "/get/{productId}/options", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Option>> getOptions(@PathVariable("productId") int productId) {
		ArrayList<Option> product_options = new ArrayList<Option>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product_options = products.get(i).getOptions();
				break;
			}
		}
		return new ResponseEntity<ArrayList<Option>>(product_options, HttpStatus.OK);
	}

	// Get Product by Shop id
	@RequestMapping(value = "/get/shop/{shopId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getByShop(@PathVariable("shopId") int shopId) {
		ArrayList<Product> product_shop = new ArrayList<Product>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getShop_id() == shopId) {
				product_shop.add(products.get(i));
			}
		}
		return new ResponseEntity<ArrayList<Product>>(product_shop, HttpStatus.OK);
	}

	// Create Product
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(String nameMain, String pictureMain, String description,
			int[] categorysId, String nameOption, double price, int amount, String pictureOption, double weight,
			int shop_id) {
		ArrayList<Category> category = new ArrayList<Category>();
		ArrayList<Option> option = new ArrayList<Option>();
		for (int i = 0; i < categorys.size(); i++) {
			for (int j = 0; j < categorysId.length; j++) {
				if (categorysId[j] == categorys.get(i).getId()) {
					category.add(categorys.get(i));
				}
			}

		}
		Option.setCount(0);
		option.add(new Option(amount, nameOption, pictureOption, price, weight));
		Product product = new Product(nameMain, pictureMain, description, category, option, shop_id);
		products.add(product);
		WriteFile("product.dat");
		ReadFile();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// Create Option
	@RequestMapping(value = "/create/option", method = RequestMethod.POST)
	public ResponseEntity<Option> createOption(int productId, String name, double price, int amount, String picture,
			double weight) {
		Product product = new Product();
		ArrayList<Option> options = new ArrayList<Option>();
		Option option = null;
		boolean checkProduct = false;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
				checkProduct = true;
				break;
			}
		}
		if (checkProduct) {
			option = new Option(amount, name, picture, price, weight, product.getId());
			options = product.getOptions();
			options.add(option);
			product.setOptions(options);
			WriteFile("product.dat");
			ReadFile();
		} else {
			System.out.println("Product not found");
			return null;
		}
		return new ResponseEntity<Option>(option, HttpStatus.OK);
	}

	// Create Category
	@RequestMapping(value = "/create/category", method = RequestMethod.POST)
	public ResponseEntity<Category> createCategory(String name) {
		Category category = new Category(name);
		categorys.add(category);
		WriteFile("category.dat");
		ReadFile();
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	// Update Product
	@RequestMapping(value = "/update/{productId}", method = RequestMethod.PATCH)
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId, String name, String picture,
			String description, int[] categorysId) {
		Product product = null;
		ArrayList<Category> category = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
			}
		}
		if (categorysId != null) {
			category = new ArrayList<Category>();
			for (int i = 0; i < categorys.size(); i++) {
				for (int j = 0; j < categorysId.length; j++) {
					if (categorysId[j] == categorys.get(i).getId()) {
						category.add(categorys.get(i));
					}
				}

			}
		}
		if (name != null) {
			product.setName(name);
		}
		if (picture != null) {
			product.setPicture(picture);
		}
		if (description != null) {
			product.setDescription(description);
		}
		if (category != null) {
			product.setCategorys(category);
		}
		WriteFile("product.dat");
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// Update Option
	@RequestMapping(value = "/update/{productId}/{optionId}", method = RequestMethod.PATCH)
	public ResponseEntity<Option> updateOption(@PathVariable("productId") int productId,
			@PathVariable("optionId") int optionId, String name, String picture, Integer amount, Double weight,
			Double price) {
		Product product = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
			}
		}
		ArrayList<Option> options = product.getOptions();
		Option option = null;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getId() == optionId) {
				option = options.get(i);
				break;
			}
		}
		if (name != null) {
			option.setName(name);
		}
		if (picture != null) {
			option.setPicture(picture);
		}
		if (amount != null) {
			option.setAmount(amount);
		}
		if (weight != null) {
			option.setWeight(weight);
		}
		if (price != null) {
			option.setPrice(price);
		}
		WriteFile("product.dat");
		return new ResponseEntity<Option>(option, HttpStatus.OK);
	}

	// Update Category Name
	@RequestMapping(value = "/update/category/{categoryId}", method = RequestMethod.PATCH)
	public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") int categoryId, String name) {
		Category category = null;
		for (int i = 0; i < categorys.size(); i++) {
			if (categorys.get(i).getId() == categoryId) {
				category = categorys.get(i);
			}
		}
		if (name != null) {
			category.setName(name);
			for (int i = 0; i < products.size(); i++) {
				for (int j = 0; j < products.get(i).getCategorys().size(); j++) {
					if (products.get(i).getCategorys().get(j).getId() == categoryId) {
						products.get(i).getCategorys().get(j).setName(name);
					}
				}
			}
		}
		WriteFile("product.dat");
		WriteFile("category.dat");
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	// Delete Product Picture
	@RequestMapping(value = "/delete/{productId}/picture/{optionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Option> deletePicture(@PathVariable("productId") int productId,
			@PathVariable("optionId") int optionId) {
		Product product = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
			}
		}
		Option option = null;
		for (int i = 0; i < product.getOptions().size(); i++) {
			if (product.getOptions().get(i).getId() == optionId) {
				option = product.getOptions().get(i);
			}
		}
		option.setPicture(null);
		WriteFile("product.dat");
		return new ResponseEntity<Option>(option, HttpStatus.OK);
	}

	// Delete Product Option (ArrayList)
	@RequestMapping(value = "/delete/{productId}/{optionId}", method = RequestMethod.DELETE)
	public ResponseEntity<ArrayList<Option>> deleteOption(@PathVariable("productId") int productId,
			@PathVariable("optionId") int optionId) {
		Product product = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				product = products.get(i);
			}
		}
		ArrayList<Option> options = product.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getId() == optionId) {
				options.remove(i);
				break;
			}
		}
		product.setOptions(options);
		WriteFile("product.dat");
		return new ResponseEntity<ArrayList<Option>>(options, HttpStatus.OK);
	}

	// Delete Product
	@RequestMapping(value = "/delete/{productId}/", method = RequestMethod.DELETE)
	public ResponseEntity<ArrayList<Product>> deleteProduct(@PathVariable("productId") int productId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				products.remove(i);
				break;
			}
		}
		WriteFile("product.dat");
		return new ResponseEntity<ArrayList<Product>>(products, HttpStatus.OK);
	}

	public void WriteFile(String fileName) {
		if (fileName == "product.dat") {
			try {
				FileOutputStream fout_product = new FileOutputStream("product.dat");
				ObjectOutputStream oout_product = new ObjectOutputStream(fout_product);
				oout_product.writeObject(products);
			} catch (FileNotFoundException ex) {
			} catch (IOException ex) {
			}
		} else if (fileName == "category.dat") {
			try {
				FileOutputStream fout_category = new FileOutputStream("category.dat");
				ObjectOutputStream oout_category = new ObjectOutputStream(fout_category);
				oout_category.writeObject(categorys);
			} catch (FileNotFoundException ex) {
			} catch (IOException ex) {
			}
		} else {
			try {
				FileOutputStream fout_product = new FileOutputStream("product.dat");
				FileOutputStream fout_category = new FileOutputStream("category.dat");
				ObjectOutputStream oout_product = new ObjectOutputStream(fout_product);
				ObjectOutputStream oout_category = new ObjectOutputStream(fout_category);
				categorys.add(new Category("Gaming"));
				oout_category.writeObject(categorys);
				products.add(new Product("Nintendo Switch™ Joy-Con Controllers",
						"https://cf.shopee.co.th/file/1d840ac92507ef12dbbfa9cb299012ed_tn",
						"Nintendo Switch™ Joy-Con Controllers สินค้าของแท้ กล่องญี่ปุ่น",
						new ArrayList<Category>(Arrays.asList(categorys.get(0))),
						new ArrayList<Option>(Arrays.asList(new Option(5, "แดงฟ้า Neon",
								"https://cf.shopee.co.th/file/1d840ac92507ef12dbbfa9cb299012ed_tn", 2490.0, 0.049),
								new Option(7, "ม่วงส้ม PurpleOrange",
										"https://cf.shopee.co.th/file/7dff664bb3aee95f4211a77b59f0beb3", 2500.0, 0.049),
								new Option(3, "เขียวชมพู GreenPink", "https://j.lnwfile.com/8p5jwe.jpg", 2590.0,
										0.049))),
						1));
				Option.setCount(0);
				products.add(new Product("Nintendo Switch V2 (Neon)", "https://fn.lnwfile.com/q9q0jr.jpg",
						"เครื่องนอกประกันร้าน สินค้าของแท้", new ArrayList<Category>(Arrays.asList(categorys.get(0))),
						new ArrayList<Option>(Arrays.asList(
								new Option(15, "แดงฟ้า Neon", "https://fn.lnwfile.com/q9q0jr.jpg", 10990.0, 0.297))),
						2));
				oout_product.writeObject(products);
			} catch (FileNotFoundException ex) {
				System.out.println(ex);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}

	public void ReadFile() {
		try {
			FileInputStream fin_product = new FileInputStream("product.dat");
			FileInputStream fin_category = new FileInputStream("category.dat");
			ObjectInputStream oin_product = new ObjectInputStream(fin_product);
			ObjectInputStream oin_category = new ObjectInputStream(fin_category);
			products = (ArrayList<Product>) oin_product.readObject();
			categorys = (ArrayList<Category>) oin_category.readObject();
		} catch (EOFException ex) {
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		} catch (ClassNotFoundException ex) {
		}
	}
}
