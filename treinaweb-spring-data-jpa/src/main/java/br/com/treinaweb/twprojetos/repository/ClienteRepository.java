package br.com.treinaweb.twprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twprojetos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@EntityGraph(attributePaths = "endereco")
	List<Cliente> findAll();

}
