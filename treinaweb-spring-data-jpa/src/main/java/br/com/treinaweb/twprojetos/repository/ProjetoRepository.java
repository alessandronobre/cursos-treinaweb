package br.com.treinaweb.twprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twprojetos.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	
	@EntityGraph(attributePaths = {"cliente", "lider"})
	List<Projeto> findAll();
}
