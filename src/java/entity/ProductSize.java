package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductSize {

	private int productSizeId;
	private Product product;
	private Size size;

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + this.productSizeId;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final ProductSize other = (ProductSize) obj;
		return this.productSizeId == other.productSizeId;
	}

}
