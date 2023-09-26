package br.com.treinaweb.twprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojetos.model.Funcionario;
import br.com.treinaweb.twprojetos.model.UF;
import br.com.treinaweb.twprojetos.repository.CargoRepository;
import br.com.treinaweb.twprojetos.repository.FuncionarioRepository;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoRepository cargoRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("funcionario/home");
		modelAndView.addObject("funcionarios", funcionarioRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");
		modelAndView.addObject("funcionario", funcionarioRepository.getOne(id));

		return modelAndView;
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
		Funcionario funcionario = funcionarioRepository.getOne(id);
		modelAndView.addObject("funcionario", funcionario);
		modelAndView.addObject("ufs", UF.values());
		modelAndView.addObject("cargos", cargoRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
		modelAndView.addObject("funcionario", new Funcionario());;
		modelAndView.addObject("ufs", UF.values());
		modelAndView.addObject("cargos", cargoRepository.findAll());

		return modelAndView;
	}
	
	@PostMapping({"/cadastrar", "/{id}/editar"})
	public String salvarCadastro(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);

		return "redirect:/funcionarios";
	}
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		funcionarioRepository.deleteById(id);

		return "redirect:/funcionarios";
	}
	
}
