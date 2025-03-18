/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entity;

/**
 *
 * @author thanh
 */
public enum OrderStatus {

	PENDING("Pending"), CONFIRMED("Confirmed"), SHIPPING("Shipping"), SHIPPED("Shipped"), CANCELED("Canceled");

	private final String name;

	private OrderStatus(String status) {
		this.name = status;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "OrderStatus{" + "status=" + name + '}';
	}

	public static OrderStatus getOrderStatus(String status) {
		switch (status) {
			case "Pending" -> {
				return PENDING;
			}
			case "Confirmed" -> {
				return CONFIRMED;
			}
			case "Shipping" -> {
				return SHIPPING;
			}
			case "Shipped" -> {
				return SHIPPED;
			}
			case "Canceled" -> {
				return CANCELED;
			}
			default -> {
				return null;
			}
		}
	}
}
