package com.univille.sistemastatus.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cardapio {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	//@NotNull
	//@Length(min=2, max=9, message="O tamanho do nome deve ser entre {min} e {max}")
	@Temporal(value=TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;
	
	
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="itempedido_id")
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
