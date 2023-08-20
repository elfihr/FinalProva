package com.loja.loja.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.loja.loja.model.Hamburguer;

public interface LojaRepository extends MongoRepository<Hamburguer, String> {
    
}