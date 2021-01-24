package com.estudoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudoRest.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
	
	Iterable<Pessoa> findByEmail(String email);
	
	

}
