package com.estudoRest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudoRest.model.Pessoa;
import com.estudoRest.repository.PessoaRepository;

@RestController
public class Controller {
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@PostMapping(path="/{email}")
	public ResponseEntity<Pessoa> salvar(@Valid @PathVariable("email") String email, @RequestBody Pessoa pessoa) {			
				
		pessoa.setEmail(email);									
		ArrayList<Integer> list = gerarNumerosAleatorios();
		pessoa.setList(list);
		pessoaRepo.save(pessoa);
		
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
	}
	
	@GetMapping("/{email}") 
		public List<Pessoa> pessoa(@PathVariable String email) { 
		Iterable<Pessoa> p = pessoaRepo.findByEmail(email);
		return (List<Pessoa>) p; 
	}

	private ArrayList<Integer> gerarNumerosAleatorios() {
		
		Random random = new Random();
		int[] num = new int[6];
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0;i<num.length;i++) {
			num[i] = random.nextInt(66);
			list.add(num[i]);
		}
		return list;
	}
}
