package com.imena.sertec.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imena.sertec.model.Client;
import com.imena.sertec.repositories.ClientRepository;
import com.imena.sertec.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Client> getClients() {
		log.info("Listar accesorios");
		return clientRepository.findAll();
	}

	@Override
	public Optional<Client> getClientById(int id) {
		log.info("Buscar cliente con id {}", id);
		return clientRepository.findById(id);
	}

	@Override
	public Client insert(Client client) {
		log.info("Se han agregado los datos {}", client);
		return clientRepository.save(client);
	}

	@Override
	public Optional<Client> findByCiClient(String clientCI) {
		log.info("Buscar accesorio con ci {}", clientCI);
		return clientRepository.findByClientId(clientCI);
	}

	

}
