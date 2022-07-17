package com.imena.sertec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(nullable = false, unique = true)
	String name;
	@Enumerated(value = EnumType.STRING)
	State state = State.ACTIVO;
}
