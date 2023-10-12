package br.com.refrigerappapi.dto;

import lombok.Data;

@Data
public class PassDTO {

	private String oldPassword;
	private String password;
	private String passwordConfirmation;
}
