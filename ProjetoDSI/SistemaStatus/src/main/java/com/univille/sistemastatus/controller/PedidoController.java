package com.univille.sistemastatus.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.univille.sistemastatus.model.ItemPedidoProduto;
import com.univille.sistemastatus.model.Pedido;
import com.univille.sistemastatus.model.Produto;
import com.univille.sistemastatus.repository.MesaRepository;
import com.univille.sistemastatus.repository.PedidoRepository;
import com.univille.sistemastatus.repository.ProdutoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;	
	@Autowired
	private MesaRepository mesaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Pedido> listaPedido = this.pedidoRepository.findAll();

		return new ModelAndView("pedido/index","listaPedido",listaPedido);
	}
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Pedido pedido) {
		List<Produto> listaProduto = this.produtoRepository.findAll();

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaPedido", pedido.getPedidoProduto());
		dados.put("listaProduto", listaProduto);
		dados.put("novopedido", new ItemPedidoProduto());

		return new ModelAndView("pedido/form",dados);
	}

	@PostMapping(params= {"save"})
	public ModelAndView save(@Valid Pedido pedido, @Valid ItemPedidoProduto novopedido, BindingResult result, RedirectAttributes redirect) {
		this.pedidoRepository.save(pedido);
		return new ModelAndView("redirect:/pedido");
	}

	@PostMapping(params= {"insertPedido"})
	public ModelAndView insertPedido(@Valid Pedido pedido, @Valid ItemPedidoProduto novopedido, BindingResult result, RedirectAttributes redirect) {
		List<Produto> listaProduto = this.produtoRepository.findAll();
		
		novopedido.setPrecoUni(novopedido.getProduto().getValorUni() * novopedido.getQuantidade());
		

		pedido.getPedidoProduto().add(novopedido);

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaPedido", pedido.getPedidoProduto());
		dados.put("listaProduto", listaProduto);
		dados.put("novopedido", new ItemPedidoProduto());

		return new ModelAndView("pedido/form",dados);
	}

	@PostMapping(params= {"removePedido"})
	public ModelAndView removePedido(@RequestParam(name = "removePedido") int index, Pedido pedido, ItemPedidoProduto novopedido, BindingResult result, RedirectAttributes redirect) {
		List<Produto> listaProduto = this.produtoRepository.findAll();

		pedido.getPedidoProduto().remove(index);

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaPedido", pedido.getPedidoProduto());
		dados.put("listaProduto", listaProduto);
		dados.put("novopedido", new ItemPedidoProduto());


		return new ModelAndView("pedido/form",dados);
	}

	@GetMapping(value="/alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Pedido pedido) {
		List<Produto> listaProduto = this.produtoRepository.findAll();

		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaPedido", pedido.getPedidoProduto());
		dados.put("listaProduto", listaProduto);    
		dados.put("novopedido", new ItemPedidoProduto());

		return new ModelAndView("pedido/form",dados);
	}
	@GetMapping(value="remover/{id}")
	public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		this.pedidoRepository.deleteById(id);
		return new ModelAndView("redirect:/pedido");
	}


}

