package ru.open.dto;

public record ProductDto(
	long id,
	String productName,
	String accNumber,
	double balance,
	String productType,
	long userId
) {
}
