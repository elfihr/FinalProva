package com.loja.loja.model;

import java.util.List;

import com.loja.loja.shared.LojaCompletoDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("hamburguer")
public class Hamburguer {
    @Id
    private String id;
    private String saborCarne;
    private List<String> ingredientes;
    private TamanhoHamburguer tamanho;
    private Double valor;

    public Hamburguer() {}


    public Hamburguer(LojaCompletoDto dto) {
        this.id = dto.id();
        this.saborCarne = dto.saborCarne();
        this.ingredientes = dto.ingredientes();
        this.tamanho = dto.tamanho();
        this.valor = dto.valor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaborCarne() {
        return saborCarne;
    }

    public void setSaborCarne(String saborCarne) {
        this.saborCarne = saborCarne;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public TamanhoHamburguer getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoHamburguer tamanho) {
        this.tamanho = tamanho;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}