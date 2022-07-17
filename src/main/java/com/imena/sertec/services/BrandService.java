package com.imena.sertec.services;
import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.Brand;

public interface BrandService {
    List<Brand> getBrands();

    Optional<Brand> getBrandById(int id);

    Brand insert(Brand brand);

}