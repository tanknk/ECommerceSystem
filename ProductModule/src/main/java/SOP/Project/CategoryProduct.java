package SOP.Project;

public class CategoryProduct {
	private String id, product_id, category_id;
	private static int count = 1;
	
	

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		CategoryProduct.count = count;
	}

	public CategoryProduct() {
		this("", "");
	}
	
	public CategoryProduct(String product_id, String category_id) {
		super();
		this.id = count + "";
		this.product_id = product_id;
		this.category_id = category_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
}
