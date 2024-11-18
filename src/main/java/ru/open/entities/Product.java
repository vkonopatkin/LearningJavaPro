package ru.open.entities;

public class Product {
	private Long id;
	private String productName;
	private String accNumber;
	private double balance;
	private ProductType productType;
	private long userId;

	public Product() {
	}

	public Product(Long id, String productName, String accNumber, double balance, ProductType productType, long userId) {
		this.id = id;
		this.productName = productName;
		this.accNumber = accNumber;
		this.balance = balance;
		this.productType = productType;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public double getBalance() {
		return balance;
	}

	public ProductType getProductType() {
		return productType;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", accNumber='" + accNumber + '\'' +
				", balance=" + balance +
				", productType=" + productType +
				", userId=" + userId +
				'}';
	}
}
