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

import com.univille.sistemastatus.model.Cardapio;
import com.univille.sistemastatus.model.ItemPedido;
import com.univille.sistemastatus.model.Produto;
import com.univille.sistemastatus.repository.CardapioRepository;
import com.univille.sistemastatus.repository.ProdutoRepository;

@Controller
@RequestMapping("/cardapio")
public class CardapioController {
	@Autowired
    private CardapioRepository cardapioRepository;
	@Autowired
    private ProdutoRepository produtoRepository;

	
	@GetMapping("")
    public ModelAndView index() {
        List<Cardapio> listaCardapio = this.cardapioRepository.findAll();
        
        return new ModelAndView("cardapio/index","listaCardapio",listaCardapio);
    }
    
    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Cardapio cardapio) {
        List<Produto> listaProduto = this.produtoRepository.findAll();
       
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("listaItem", cardapio.getItens());
        dados.put("listaProduto", listaProduto);
        dados.put("novoitem", new ItemPedido());
        
        return new ModelAndView("cardapio/form",dados);
    }
    
    @PostMapping(params= {"save"})
	public ModelAndView save(@Valid Cardapio cardapio, @Valid ItemPedido novoitem, BindingResult result, RedirectAttributes redirect) {
    	this.cardapioRepository.save(cardapio);
		return new ModelAndView("redirect:/cardapio");
	}
    
	@PostMapping(params= {"insertItem"})
	public ModelAndView insertItem(@Valid Cardapio cardapio, @Valid ItemPedido novoitem, BindingResult result, RedirectAttributes redirect) {
		List<Produto> listaProduto = this.produtoRepository.findAll();
	       
		cardapio.getItens().add(novoitem);
		
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("listaItem", cardapio.getItens());
        dados.put("listaProduto", listaProduto);
        dados.put("novoitem", new ItemPedido());
        dados.put("cardapio", cardapio);
		
		return new ModelAndView("cardapio/form",dados);
	}
	
	@PostMapping(params= {"removeItem"})
	public ModelAndView removeItem(@RequestParam(name = "removeItem") int index, Cardapio cardapio, ItemPedido novoitem, BindingResult result, RedirectAttributes redirect) {
		List<Produto> listaProduto = this.produtoRepository.findAll();
		
		cardapio.getItens().remove(index);	
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaItem", cardapio.getItens());
        dados.put("listaProduto", listaProduto);
        dados.put("novoitem", new ItemPedido());
        dados.put("cardapio", cardapio);
		
		
		return new ModelAndView("cardapio/form",dados);
	}
	
	@GetMapping(value="/alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Cardapio cardapio) {
		List<Produto> listaProduto = this.produtoRepository.findAll();
		
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listaItem", cardapio.getItens());
        dados.put("listaProduto", listaProduto);
        dados.put("novoitem", new ItemPedido());
        dados.put("cardapio", cardapio);
		
		return new ModelAndView("cardapio/form",dados);
	}
	@GetMapping(value="remover/{id}")
	public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		this.cardapioRepository.deleteById(id);
		return new ModelAndView("redirect:/cardapio");
	}
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	/*@GetMapping("")
	public ModelAndView index() {
		List<Cardapio> listaCardapio= new ArrayList<Cardapio>();
		List<Produto> listaProduto= new ArrayList<Produto>();
		
		
		Cardapio p1 = new Cardapio();
		p1.setId(1);
		p1.setDataInicio("20/08/2018");
		p1.setDataFim("01/12/2018");
		p1.setProdutos(listaProduto);
		
		listaCardapio.add(p1);
		return new ModelAndView("cardapio/index","listapac",listaCardapio);
	}
}*/
