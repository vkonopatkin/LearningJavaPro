package ru.open.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "vk_jp7_users")
public class User {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Getter
	private String username;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}
}
