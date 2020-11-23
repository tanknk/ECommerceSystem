package SOP.Project;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
	private int id;
	private String name;
	private static int count = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		File f = new File("category.dat");
		if (f.exists()) {
			try {
				FileInputStream fin_category = new FileInputStream("category.dat");
				ObjectInputStream oin_category = new ObjectInputStream(fin_category);
				ArrayList<Category> categorys = (ArrayList<Category>) oin_category.readObject();
				count = categorys.get(categorys.size() - 1).getId();
			} catch (EOFException ex) {
			} catch (FileNotFoundException ex) {
			} catch (IOException ex) {
			} catch (ClassNotFoundException ex) {
			}
		}
		this.id = ++count;
		this.name = name;
	}
	
	public Category() {
		this("");
	}
	
}
