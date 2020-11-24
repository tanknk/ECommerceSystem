package SOP.Project;

import java.util.ArrayList;

public class Product {
	private static int count = 1;
	private String shop_id;
	private String name, picture, description, id;
	private ArrayList<Option> options;
	private ArrayList<Category> categorys;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Product.count = count;
	}
	
	public ArrayList<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(ArrayList<Category> categorys) {
		this.categorys = categorys;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product(String name, String picture, String description, ArrayList<Option> options, ArrayList<Category> categorys, String shop_id) {
		super();
		this.id = count + "";
		this.name = name;
		this.picture = picture;
		this.description = description;
		this.options = options;
		this.categorys = categorys;
		this.shop_id = shop_id;
	}

	public Product() {
		this("", "", "", new ArrayList<Option>(), new ArrayList<Category>(), "");
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
