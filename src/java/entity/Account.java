package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {

	private int accountId;
	private String fullname;
	private String email;
	private String password;
	private boolean isAdmin;
}
