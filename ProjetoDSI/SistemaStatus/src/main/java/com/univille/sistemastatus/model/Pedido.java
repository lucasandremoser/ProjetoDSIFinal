package com.univille.sistemastatus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Mesa mesa;
	private float valorTotal;
	
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="pedido_id")
	private List<ItemPedidoProduto> pedidoProduto = new ArrayList<ItemPedidoProduto>();
	
	
	public long getId() {
		return id;
	}
	public List<ItemPedidoProduto> getPedidoProduto() {
		return pedidoProduto;
	}
	public void setPedidoProduto(List<ItemPedidoProduto> pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

}
