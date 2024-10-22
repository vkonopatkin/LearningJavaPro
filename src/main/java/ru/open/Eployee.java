package ru.open;

import lombok.Getter;

public class Eployee {
	private String name;
	private Integer age;
	private String position;

	public Eployee(String name, Integer age, String position) {
		this.name = name;
		this.age = age;
		this.position = position;
	}

}
