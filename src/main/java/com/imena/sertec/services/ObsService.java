package com.imena.sertec.services;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.Obs;

public interface ObsService {
	List<Obs> getPersons();

    Optional<Obs> getObsById(int id);

    Obs insert(Obs obs);
}
