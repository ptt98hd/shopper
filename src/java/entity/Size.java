package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Size {

	private int sizeId;
	private short sizeEu;

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + this.sizeId;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final Size other = (Size) obj;
		return this.sizeId == other.sizeId;
	}

}
