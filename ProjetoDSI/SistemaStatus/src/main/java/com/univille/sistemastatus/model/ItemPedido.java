package com.univille.sistemastatus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Produto produto = new Produto();
	private int quantidade;
	private float precoUni;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(float precoUni) {
		this.precoUni = precoUni;
	}
	
}
