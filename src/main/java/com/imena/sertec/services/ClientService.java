package com.imena.sertec.services;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.Client;

public interface ClientService {
    List<Client> getClients();

    Optional<Client> getClientById(int id);
    Optional<Client> findByCiClient(String clientCI);

    Client insert(Client client);

}