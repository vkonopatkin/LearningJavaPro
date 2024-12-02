package ru.open.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

//	@Getter
//	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
//	Set<Product> product = new HashSet<>();

//	public User(long id, String username) {
//		this.id = id;
//		this.username = username;
//	}
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}
}
