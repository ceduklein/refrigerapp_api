package br.com.refrigerappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
  
  private String name;
  private String doc;
  private String email;
  private String phone;
}
