package br.com.refrigerappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
  
  private String name;
  private String brand;
  private String category;
  private String model;
  private String ean;
  private String voltage;
  private Double value;
  private Integer quantity;
  private String image;
}
