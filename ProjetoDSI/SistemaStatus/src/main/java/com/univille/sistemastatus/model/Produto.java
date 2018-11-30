package com.univille.sistemastatus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Length(min=2, max=1000, message="O tamanho do nome deve ser entre {min} e {max}")
	private String descricaoProduto;
	private float valorUni;
	private int quantidade;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoproduto) {
		this.descricaoProduto = descricaoproduto;
	}
	public float getValorUni() {
		return valorUni;
	}
	public void setValorUni(float valorUni) {
		this.valorUni = valorUni;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
