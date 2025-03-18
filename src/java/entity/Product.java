package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	private int productId;
	private String productName;
	private String productImg;
	private double price;
	private Category category;
	private Brand brand;
	private Color color;
}
