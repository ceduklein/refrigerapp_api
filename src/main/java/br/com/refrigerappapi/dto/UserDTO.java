package br.com.refrigerappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String name;
	private String login;
	private String cpf;
	private String password;
}
