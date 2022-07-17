package com.imena.sertec.services;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.OT;

public interface OTService {
	List<OT> getOTs();

	Optional<OT> getOTById(int id);

	OT insert(OT oT);

}