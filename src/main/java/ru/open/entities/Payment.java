package ru.open.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Payment {

	@Getter
	private long productId;
	@Getter
	private double balanceDelta;

	public Payment(long productId, double balanceDelta) {
		this.productId = productId;
		this.balanceDelta = balanceDelta;
	}

	public Payment() {
	}

}
