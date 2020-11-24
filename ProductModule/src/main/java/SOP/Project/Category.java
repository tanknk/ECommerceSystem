package SOP.Project;

public class Category {
	private String id;
	private String name;
	private static int count = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(String name) {
		super();
		this.id = count + "";
		this.name = name;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Category.count = count;
	}

	public Category() {
		this("");
	}

}
