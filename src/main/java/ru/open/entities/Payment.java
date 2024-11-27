package ru.open.entities;

public class Payment {
	private long productId;
	private double balanceDelta;

	public Payment(long productId, double balanceDelta) {
		this.productId = productId;
		this.balanceDelta = balanceDelta;
	}

	public Payment() {
	}

	public long getProductId() {
		return productId;
	}

	public double getBalanceDelta() {
		return balanceDelta;
	}
}
