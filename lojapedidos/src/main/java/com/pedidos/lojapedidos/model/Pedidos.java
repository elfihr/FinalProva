package com.pedidos.lojapedidos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pedidos.lojapedidos.shared.HamburguerCompletoDto;



@Document("pedido")
public class Pedidos {
    
    @Id
    private String idPedidos; 
    private String Cliente;
    private String idProduto;

    public Pedidos(){}

    public Pedidos(HamburguerCompletoDto dto){
        this.idPedidos = dto.idPedidos();
        this.Cliente = dto.Cliente();
        this.idProduto = dto.idProduto();
    }
    

    
    public String getIdPedidos() {
        return idPedidos;
    }
    public void setIdPedidos(String idPedidos) {
        this.idPedidos = idPedidos;
    }
    public String getCliente() {
        return Cliente;
    }
    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }
    public String getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
}
