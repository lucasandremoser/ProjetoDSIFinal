package com.univille.sistemastatus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.univille.sistemastatus.model.Usuario;
import com.univille.sistemastatus.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")//
public class UsuarioController {
	@Autowired
	private UsuarioRepository UsuarioRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Usuario> listaUsuario= this.UsuarioRepository.findAll();

		return new ModelAndView("usuario/index","listausu",listaUsuario);
	}

	@GetMapping("/novo")
	public String createForm(@ModelAttribute Usuario usuario) {
		return "usuario/form";
	}

	@PostMapping(params="form")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirect) {

		usuario = this.UsuarioRepository.save(usuario);

		return new ModelAndView("redirect:/usuario");
	}

	@GetMapping(value="/alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Usuario usuario) {

		return new ModelAndView("usuario/form","usuario",usuario);
	}

	@GetMapping(value="remover/{id}")
	public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
		this.UsuarioRepository.deleteById(id);
		return new ModelAndView("redirect:/usuario");
	}


}
