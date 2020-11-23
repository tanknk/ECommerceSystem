package SOP.Project;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Option implements Serializable {
	private int amount;
	private String id, name, picture;
	private double price, weight;
	private static int count = 1;
	
	

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Option.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Option(int amount, String name, String picture, double price, double weight) {
		super();
		this.id = count + "";
		this.amount = amount;
		this.name = name;
		this.picture = picture;
		this.price = price;
		this.weight = weight;
	}

	public Option() {
		this(0, "", "", 0, 0);
	}

}
