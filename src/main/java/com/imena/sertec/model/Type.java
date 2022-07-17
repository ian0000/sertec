package com.imena.sertec.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	int years;
	@Enumerated(value = EnumType.STRING)
	State state = State.ACTIVO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_type")
	Brand brand;

	
}
