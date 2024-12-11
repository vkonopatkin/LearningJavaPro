package ru.open.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "user_limit")
public class Limit {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Getter
	@Column(name = "user_id")
	private long userId;

	@Getter
	@Setter
	@Column(name = "limit_value")
	private double limitValue;

	public Limit(long userId, double limitValue) {
		this.userId = userId;
		this.limitValue = limitValue;
	}
}
