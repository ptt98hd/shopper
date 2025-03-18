package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Color {

	private int colorId;
	private String colorName;
	private String hex;
}
