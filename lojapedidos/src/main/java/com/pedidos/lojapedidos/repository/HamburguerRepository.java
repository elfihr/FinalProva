package com.pedidos.lojapedidos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.pedidos.lojapedidos.model.Pedidos;



public interface HamburguerRepository extends MongoRepository <Pedidos, String> {
    
}
