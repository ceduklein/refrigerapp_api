package br.com.refrigerappapi.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String model;
	
	@Column(nullable = false)
	private String ean;
	
	@Column
	private String voltage;
	
	@Column(nullable = false)
	private Double value;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private String image;
	
	@Column(nullable = false)
	private boolean isActive;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	private LocalDateTime updated_at;
}
