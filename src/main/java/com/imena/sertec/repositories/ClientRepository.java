package com.imena.sertec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imena.sertec.model.Client;

public interface ClientRepository  extends JpaRepository<Client, Integer>{

	Optional<Client> findByClientId(String clientId);
}
