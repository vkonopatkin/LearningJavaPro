package ru.open.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@NoArgsConstructor
@Table(name = "vk_jp7_products")
public class Product {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Getter
	@Column(name = "productname")
	private String productName;

	@Getter
	@Column(name = "accnumber")
	private String accNumber;

	@Getter
	@Setter
	private double balance;

	@Getter
	@Column(name = "producttype")
	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@Getter
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "userid")
	private User userId;

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", accNumber='" + accNumber + '\'' +
				", balance=" + balance +
				", productType=" + productType +
				", userId=" + userId +
				'}';
	}
}
