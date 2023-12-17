package com.stilus.StilusViagens.controller;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stilus.StilusViagens.model.Destino;
import com.stilus.StilusViagens.model.Promocoes;
import com.stilus.StilusViagens.model.Usuario;
import com.stilus.StilusViagens.repository.DestinoRepository;
import com.stilus.StilusViagens.repository.PromocoesRepository;
import com.stilus.StilusViagens.repository.UsuarioRepository;

@Controller
@RequestMapping("/promocoes")
public class PromocoesController {
	
	@Autowired
	private PromocoesRepository promocoesRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DestinoRepository destinoRepository;
	
	@SuppressWarnings("unused")
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("promocoes/listar.html");
 
		List<Promocoes> promocoes = promocoesRepository.findAll();
		List<Usuario> usuario = usuarioRepository.findAll();
		List<Destino> destino = destinoRepository.findAll();
		modelAndView.addObject("promocoes", promocoes);
 
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("promocoes/cadastro");
 
		modelAndView.addObject("promocoes", new Promocoes());
		modelAndView.addObject("usuario", usuarioRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(Promocoes promocoes) throws IOException {
		
		promocoesRepository.save(promocoes);

		ModelAndView modelAndView = new ModelAndView("redirect:/promocoes");
		return modelAndView;
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("promocoes/edicao");
 
		modelAndView.addObject(promocoesRepository.getReferenceById(id));
		modelAndView.addObject("usuario", usuarioRepository.findAll());
		modelAndView.addObject("destino", destinoRepository.findAll());
 
		return modelAndView;
	}
	
	@PostMapping("/{id}/editar")
	public ModelAndView editar(Promocoes promocoes) {	
		
		promocoesRepository.save(promocoes);
		ModelAndView modelAndView = new ModelAndView("redirect:/promocoes");
 
		return modelAndView;
	}
	
	@GetMapping("/{id}/excluir")
	public ModelAndView excluir(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/promocoes");
 
		promocoesRepository.deleteById(id);
 
		return modelAndView;
	}

}
