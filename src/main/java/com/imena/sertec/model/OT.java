package com.imena.sertec.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OT {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(nullable = false)
	String model;
	@Column(nullable = false)
	String serialNumber;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date buyDate;
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date entryDate   ;
	@Column(nullable = false)
	String problem;
	@Enumerated(value = EnumType.STRING)
	Warranty warranty = Warranty.PENDIENTE;
	@Column(nullable = false)
	String fac;
	
	@Enumerated(value = EnumType.STRING)
	State state = State.ACTIVO;
	
//	@OneToMany
//	List<Obs> obs = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Person> person = new ArrayList<>();
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_ot")
	Type types;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_ot")
	Client client;
}
