package br.com.refrigerappapi.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Double total;
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Customer customer;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	private LocalDateTime updated_at;
}
