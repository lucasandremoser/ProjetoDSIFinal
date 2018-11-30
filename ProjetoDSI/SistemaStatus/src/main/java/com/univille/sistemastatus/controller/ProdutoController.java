package com.univille.sistemastatus.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.univille.sistemastatus.model.Produto;
import com.univille.sistemastatus.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
    private ProdutoRepository produtoRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Produto> listaProduto= this.produtoRepository.findAll();
		
		return new ModelAndView("produto/index","listapac",listaProduto);
	}
	
	@GetMapping("/novo")
    public String createForm(@ModelAttribute Produto produto) {
        return "produto/form";
    }
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes redirect) {
        
        produto = this.produtoRepository.save(produto);
        
        return new ModelAndView("redirect:/produto");
    }
	
	@GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Produto produto) {
    		
		return new ModelAndView("produto/form","produto",produto);
    }
	
	@GetMapping(value="remover/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.produtoRepository.deleteById(id);
        return new ModelAndView("redirect:/produto");
    }
	
	
}

