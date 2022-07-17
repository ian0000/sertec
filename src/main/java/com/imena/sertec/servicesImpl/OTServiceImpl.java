package com.imena.sertec.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imena.sertec.model.OT;
import com.imena.sertec.repositories.OTRepository;
import com.imena.sertec.services.OTService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OTServiceImpl implements OTService {
	@Autowired
	OTRepository oTRepository;

	@Override
	public List<OT> getOTs() {
		log.info("Listar accesorios");
		return oTRepository.findAll();
	}

	@Override
	public Optional<OT> getOTById(int id) {
		log.info("Buscar accesorio con id {}", id);
		return oTRepository.findById(id);
	}

	@Override
	public OT insert(OT oT) {
		return oTRepository.save(oT);
	}

}
