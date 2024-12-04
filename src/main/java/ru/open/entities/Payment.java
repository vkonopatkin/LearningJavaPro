package ru.open.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Getter
	private long productId;
	@Getter
	private double balanceDelta;
}
