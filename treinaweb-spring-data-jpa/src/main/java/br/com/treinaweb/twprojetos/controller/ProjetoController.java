package br.com.treinaweb.twprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.model.Projeto;
import br.com.treinaweb.twprojetos.repository.ClienteRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;
import br.com.treinaweb.twprojetos.repository.ProjetoRepository;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("projeto/home");
		modelAndView.addObject("projetos", projetoRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("projeto/detalhes");
		modelAndView.addObject("projeto", projetoRepository.getOne(id));

		return modelAndView;
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("projeto/formulario");
		modelAndView.addObject("projeto", projetoRepository.getOne(id));
		modelAndView.addObject("clientes", clienteRepository.findAll());
		modelAndView.addObject("lideres", funcionarioRepository.findByCargoNome("Coordenador"));
		modelAndView.addObject("funcionarios", funcionarioRepository.findByCargoNomeNot("Coordenador"));

		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Projeto projeto) {
		ModelAndView modelAndView = new ModelAndView("projeto/formulario");
		modelAndView.addObject("projeto", new Projeto());
		modelAndView.addObject("clientes", clienteRepository.findAll());
		modelAndView.addObject("lideres", funcionarioRepository.findByCargoNome("Coordenador"));
		modelAndView.addObject("funcionarios", funcionarioRepository.findByCargoNomeNot("Coordenador"));

		return modelAndView;
	}
	
	@PostMapping({"/cadastrar", "/{id}/editar"})
	public String salvarCadastro(Projeto projeto) {
		projetoRepository.save(projeto);

		return "redirect:/projetos";
	}
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		projetoRepository.deleteById(id);

		return "redirect:/projetos";
	}
	
}
