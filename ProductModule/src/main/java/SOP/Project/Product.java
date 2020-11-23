package SOP.Project;

import java.io.*;
import java.util.ArrayList;

public class Product implements Serializable {
	private static int count = 0;
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Product.count = count;
	}

	private int id, shop_id;
	private String name, picture, description;
	private ArrayList<Category> categorys;
	private ArrayList<Option> options;

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(ArrayList<Category> categorys) {
		this.categorys = categorys;
	}

	public Product(String name, String picture, String description, ArrayList<Category> categorys, ArrayList<Option> options, int shop_id) {
		super();
		File f = new File("product.dat");
		if (f.exists()) {
			try {
				FileInputStream fin_product = new FileInputStream("product.dat");
				ObjectInputStream oin_product = new ObjectInputStream(fin_product);
				ArrayList<Product> products = (ArrayList<Product>) oin_product.readObject();
				count = products.get(products.size() - 1).getId();
			} catch (EOFException ex) {
			} catch (FileNotFoundException ex) {
			} catch (IOException ex) {
			} catch (ClassNotFoundException ex) {
			}
		}
		this.id = ++count;
		this.name = name;
		this.picture = picture;
		this.description = description;
		this.categorys = categorys;
		this.options = options;
		this.shop_id = shop_id;
	}

	public Product() {
		this("", "", "", new ArrayList<Category>(), new ArrayList<Option>(), 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
