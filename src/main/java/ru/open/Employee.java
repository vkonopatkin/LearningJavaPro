package ru.open;

import lombok.Getter;
import lombok.ToString;

import javax.management.ConstructorParameters;

@ToString
public class Employee {
	@Getter
	private String name;
	@Getter
	private Integer age;
	@Getter
	private String position;

	public Employee(String name, Integer age, String position) {
		this.name = name;
		this.age = age;
		this.position = position;
	}

}
