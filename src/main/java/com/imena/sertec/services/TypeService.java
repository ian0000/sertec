package com.imena.sertec.services;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.Type;

public interface TypeService {
    List<Type> getTypes();

    Optional<Type> getTypeById(int id);

    Type insert(Type type);
}