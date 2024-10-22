package ru.open;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

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

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof Employee employee)) return false;
		return Objects.equals(name, employee.name) && Objects.equals(age, employee.age) && Objects.equals(position, employee.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age, position);
	}
}
