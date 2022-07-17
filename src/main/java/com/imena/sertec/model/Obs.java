package com.imena.sertec.model;

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
public class Obs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String obs;
	@Enumerated(value = EnumType.STRING)
	public Stage stage = Stage.INGRESO;
	
}
