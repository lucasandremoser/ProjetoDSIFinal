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

import com.univille.sistemastatus.model.Mesa;
import com.univille.sistemastatus.repository.MesaRepository;

@Controller
@RequestMapping("/mesa")
public class MesaController {
	@Autowired
    private MesaRepository mesaRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Mesa> listaMesa= this.mesaRepository.findAll();
		
		return new ModelAndView("mesa/index","listamesa",listaMesa);
	}
	
	@GetMapping("/novo")
    public String createForm(@ModelAttribute Mesa mesa) {
        return "mesa/form";
    }
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid Mesa mesa, BindingResult result, RedirectAttributes redirect) {
        
        mesa = this.mesaRepository.save(mesa);
        
        return new ModelAndView("redirect:/mesa");
    }
	
	@GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Mesa mesa) {
    		
		return new ModelAndView("mesa/form","mesa",mesa);
    }
	
	@GetMapping(value="remover/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.mesaRepository.deleteById(id);
        return new ModelAndView("redirect:/mesa");
    }
	
	
}

