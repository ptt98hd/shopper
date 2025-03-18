package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

	private int orderId;
	private String consignee;
	private String phone;
	private String address;
	private double total;
	private Account account;
	private OrderStatus status;
}
