package br.com.treinaweb.twprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.treinaweb.twprojetos.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@EntityGraph(attributePaths = {"endereco", "cargo"})
	List<Funcionario> findAll();

	/*
	 * Dois metodos difentes de fazer a mesma coisa, um usando query e o outro não
	 */
	@Query("select f from Funcionario f where f.cargo.nome = :cargoNome")
	List<Funcionario> buscarPorCargo(String cargoNome);
	
	List<Funcionario> findByCargoNome(String cargoNome);
	
	/*
	 * Dois metodos difentes de fazer a mesma coisa, um usando query e o outro não
	 */
	@Query("select f from Funcionario f where f.cargo.nome <> :cargoNome")
	List<Funcionario> buscarPorCargoExeto(String cargoNome);
	
	List<Funcionario> findByCargoNomeNot(String cargoNome);

}
