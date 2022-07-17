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

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(nullable = false)
	String personId;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String lastName;
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String phoneNumber;
	@Column(nullable = false)
	String username;
	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	String code;
	@Column(nullable = false)
	Roles role;
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	State state = State.ACTIVO;


}
