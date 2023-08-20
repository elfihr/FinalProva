package com.pedidos.lojapedidos.model;

public class HamburguerLoja {
    
    private String nomeHamburguer;
    private double valor;
    private int qtd;
    
    public String getnomeHamburguer() {
        return nomeHamburguer;
    }
    public void setNomeProduto(String nomeHamburguer) {
        this.nomeHamburguer = nomeHamburguer;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
