package SOP.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	// Get Product by Id
	@RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable("productId") String productId)
			throws InterruptedException, ExecutionException {
		return productService.getProductbyId(productId);
	}

	// Get All Product
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public List<Product> getAllProduct() throws InterruptedException, ExecutionException {
		return productService.getAllProduct();
	}

	// Get Product by Category
	@RequestMapping(value = "/get/category/{categoryId}", method = RequestMethod.GET)
	public List<Product> getProductByCategoryId(@PathVariable("categoryId") String categoryId)
			throws InterruptedException, ExecutionException {
		return productService.getProductbyCategoryId(categoryId);
	}

	// Get Product Options
	@RequestMapping(value = "/get/{productId}/options", method = RequestMethod.GET)
	public List<Option> getOptionsbyProductId(@PathVariable("productId") String productId)
			throws InterruptedException, ExecutionException {
		return productService.getOptionbyProductId(productId);
	}

	// Get Product by Shop id
	@RequestMapping(value = "/get/shop/{shopId}", method = RequestMethod.GET)
	public List<Product> getProductByShopId(@PathVariable("shopId") String shopId)
			throws InterruptedException, ExecutionException {
		return productService.getProductbyShopId(shopId);
	}

	// Create Product
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProduct(String nameMain, String pictureMain, String description, String[] categorysId,
			String nameOption, double price, int amount, String pictureOption, double weight,
			@ApiParam(required = true, type = "String") @RequestParam String shop_id)
			throws InterruptedException, ExecutionException {
		ArrayList<Option> option = new ArrayList<Option>();
		List<Category> categorys = productService.getAllCategory();
		List<CategoryProduct> catpros = productService.getAllCategoryProduct();
		for (int i = 0; i < catpros.size(); i++) {
			CategoryProduct.setCount(Integer.parseInt(catpros.get(i).getId()));
		}
		Option.setCount(1);
		option.add(new Option(amount, nameOption, pictureOption, price, weight));
		Product.setCount(Integer.parseInt(productService.getLastId()) + 1);
		ArrayList<Category> cat = new ArrayList<Category>();
		for (int i = 0; i < categorys.size(); i++) {
			for (int j = 0; j < categorysId.length; j++) {
				if (categorys.get(i).getId().equals(categorysId[j])) {
					cat.add(categorys.get(i));
				}
			}
		}
		Product product = new Product(nameMain, pictureMain, description, option, cat, shop_id);
		for (int i = 0; i < categorys.size(); i++) {
			for (int j = 0; j < categorysId.length; j++) {
				if (categorysId[j].equals(categorys.get(i).getId())) {
					CategoryProduct.setCount(CategoryProduct.getCount() + 1);
					CategoryProduct catpro = new CategoryProduct(product.getId(), categorysId[j]);
					productService.saveProductCategory(catpro);
				}
			}
		}
		return productService.saveProduct(product);
	}

	// Create Category
	@RequestMapping(value = "/create/category", method = RequestMethod.POST)
	public String createCategory(String name) throws InterruptedException, ExecutionException {
		List<Category> category_all = productService.getAllCategory();
		for (int i = 0; i < category_all.size(); i++) {
			Category.setCount(Integer.parseInt(category_all.get(i).getId()) + 1);
		}
		Category category = new Category(name);
		return productService.saveCategory(category);
	}

	// Create Option
	@RequestMapping(value = "/create/option", method = RequestMethod.POST)
	public String createOption(@ApiParam(required = true, type = "String") @RequestParam String productId, String name,
			double price, int amount, String picture, double weight) throws InterruptedException, ExecutionException {
		Product product = productService.getProductbyId(productId);
		ArrayList<Option> options = new ArrayList<Option>();
		Option option = null;
		for (int i = 0; i < product.getOptions().size(); i++) {
			Option.setCount(Integer.parseInt(product.getOptions().get(i).getId()) + 1);
		}
		option = new Option(amount, name, picture, price, weight);
		options = product.getOptions();
		options.add(option);
		product.setOptions(options);
		return productService.saveOption(product);
	}

	// Update Product
	@RequestMapping(value = "/update/{productId}", method = RequestMethod.PATCH)
	public String updateProduct(@PathVariable("productId") String productId, String name, String picture,
			String description, String[] categorysId) throws InterruptedException, ExecutionException {
		Product product = productService.getProductbyId(productId);
		if (name != null) {
			product.setName(name);
		}
		if (picture != null) {
			product.setPicture(picture);
		}
		if (description != null) {
			product.setDescription(description);
		}
		if (categorysId != null) {
			productService.deleteCategoryProduct(productId);
			List<CategoryProduct> catpros = productService.getAllCategoryProduct();
			for (int i = 0; i < catpros.size(); i++) {
				CategoryProduct.setCount(Integer.parseInt(catpros.get(i).getId()));
			}
			for (int j = 0; j < categorysId.length; j++) {
				CategoryProduct.setCount(CategoryProduct.getCount() + 1);
				CategoryProduct catpro = new CategoryProduct(productId, categorysId[j]);
				productService.saveProductCategory(catpro);
			}
			ArrayList<Category> cats = new ArrayList<Category>();
			List<Category> cat_all = productService.getAllCategory();
			for (int i = 0; i < cat_all.size(); i++) {
				for (int j = 0; j < categorysId.length; j++) {
					if (cat_all.get(i).getId().equals(categorysId[j])) {
						cats.add(cat_all.get(i));
					}
				}
			}
			product.setCategorys(cats);
		}
		return productService.saveProduct(product);
	}

	// Update Option
	@RequestMapping(value = "/update/{productId}/{optionId}", method = RequestMethod.PATCH)
	public String updateOption(@PathVariable("productId") String productId, @PathVariable("optionId") String optionId,
			String name, String picture, Integer amount, Double weight, Double price)
			throws InterruptedException, ExecutionException {
		Product product = productService.getProductbyId(productId);

		ArrayList<Option> options = product.getOptions();
		Option option = null;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getId().equals(optionId)) {
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

		return productService.saveOption(product);
	}

	// Update Category
	@RequestMapping(value = "/update/category/{categoryId}", method = RequestMethod.PATCH)
	public String updateCategory(@PathVariable("categoryId") String categoryId, String name)
			throws InterruptedException, ExecutionException {
		List<Category> categorys = productService.getAllCategory();
		Category category = null;
		for (int i = 0; i < categorys.size(); i++) {
			if (categorys.get(i).getId().equals(categoryId)) {
				category = categorys.get(i);
			}
		}
		if (name != null) {
			category.setName(name);
			List<Product> product = productService.getProductbyCategoryId(categoryId);
			for (int i = 0; i < product.size(); i++) {
				for (int j = 0; j < product.get(i).getCategorys().size(); j++) {
					if (product.get(i).getCategorys().get(j).getId().equals(categoryId)) {
						product.get(i).getCategorys().get(j).setName(name);
						productService.saveProduct(product.get(i));
					}
				}
			}
		}
		return productService.saveCategory(category);
	}

	// Delete Product Picture
	@RequestMapping(value = "/delete/{productId}/picture/{optionId}", method = RequestMethod.DELETE)
	public String deletePicture(@PathVariable("productId") String productId, @PathVariable("optionId") String optionId)
			throws InterruptedException, ExecutionException {
		Product product = productService.getProductbyId(productId);
		Option option = null;
		for (int i = 0; i < product.getOptions().size(); i++) {
			if (product.getOptions().get(i).getId().equals(optionId)) {
				option = product.getOptions().get(i);
			}
		}
		option.setPicture(null);
		return productService.saveProduct(product);
	}

	// Delete Product Option (ArrayList)
	@RequestMapping(value = "/delete/{productId}/{optionId}", method = RequestMethod.DELETE)
	public String deleteOption(@PathVariable("productId") String productId, @PathVariable("optionId") String optionId)
			throws InterruptedException, ExecutionException {
		Product product = productService.getProductbyId(productId);
		ArrayList<Option> options = product.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getId().equals(optionId)) {
				options.remove(i);
				break;
			}
		}
		product.setOptions(options);
		System.out.println("THIS IS SIZE : " + product.getOptions().size());
		if (product.getOptions().size() == 0) {
			System.out.println("ENTER");
			return productService.deleteProduct(productId);
		}
		return productService.saveProduct(product);
	}

	// Delete Product
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("productId") String productId)
			throws InterruptedException, ExecutionException {
		return productService.deleteProduct(productId);
	}
}
