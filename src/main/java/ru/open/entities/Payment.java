package ru.open.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Getter
	private long userId;
	@Getter
	private double paymentSumma;

	// Дальше должны быть остальные параметры платежа - получатель, назначение и т.п.
	// но, поскольку платежный сервис - это тестовая заглушка, будем передавать признак, возвращать ошибку (!=0) или нет (=0)
	@Getter
	private int returnError;

}
