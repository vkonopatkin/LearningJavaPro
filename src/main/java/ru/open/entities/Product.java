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
//	private ProductType productType;
	private String productType;

	@Getter
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)

	@JoinColumn(name = "userid")
	private User userId;

//	@Column(name = "userid")
//	private long userId;

//	public Product(Long id, String productName, String accNumber, double balance, ProductType productType, long userId) {
//		this.id = id;
//		this.productName = productName;
//		this.accNumber = accNumber;
//		this.balance = balance;
//		this.productType = productType;
//		this.userId = userId;
//	}

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
