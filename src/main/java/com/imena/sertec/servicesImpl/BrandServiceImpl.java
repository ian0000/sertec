package com.imena.sertec.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imena.sertec.model.Brand;
import com.imena.sertec.repositories.BrandRepository;
import com.imena.sertec.services.BrandService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandRepository brandRepository;

	@Override
	public List<Brand> getBrands() {
		log.info("Listar marcas");
		return brandRepository.findAll();
	}

	@Override
	public Optional<Brand> getBrandById(int id) {
		log.info("Buscar marca con id {}", id);
		return brandRepository.findById(id);
	}

	@Override
	public Brand insert(Brand brand) {
		log.info("Se han agregado los datos {}", brand);
		return brandRepository.save(brand);
	}

	


}
