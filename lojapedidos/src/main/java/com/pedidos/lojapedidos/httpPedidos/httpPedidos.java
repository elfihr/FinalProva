package com.pedidos.lojapedidos.httpPedidos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pedidos.lojapedidos.model.HamburguerLoja;

@FeignClient("lojaHamburguer")
public interface httpPedidos {
     @RequestMapping(method = RequestMethod.GET, value = "/produto/{id}")
    HamburguerLoja obterProduto(@PathVariable String id);
}
