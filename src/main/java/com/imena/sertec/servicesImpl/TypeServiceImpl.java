package com.imena.sertec.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imena.sertec.model.Type;
import com.imena.sertec.repositories.TypeRepository;
import com.imena.sertec.services.TypeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypeRepository typeRepository;

	@Override
	public List<Type> getTypes() {
		log.info("Listar tipos");
		return  typeRepository.findAll();
	}

	@Override
	public Optional<Type> getTypeById(int id) {
		log.info("Buscar tipos con id {}", id);
		return typeRepository.findById(id);
	}

	@Override
	public Type insert(Type type) {
		log.info("Se han agregado los datos {}", type);
		return typeRepository.save(type);
	}

}
