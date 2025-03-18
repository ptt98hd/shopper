package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {

	private int orderDetailId;
	private int orderId;
	private ProductSize productSize;
	private int quantity;
	private double total;
}
