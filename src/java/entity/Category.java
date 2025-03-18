package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

	private int categoryId;
	private String categoryName;
	private String categoryImg;
}
