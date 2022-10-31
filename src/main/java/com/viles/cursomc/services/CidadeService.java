package com.viles.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viles.cursomc.domain.Cidade;
import com.viles.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired 
	CidadeRepository repository;
	

	public Cidade buscarPorId(Integer id) {
		
		Optional<Cidade> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada no id "
				+ id, "Tipo:" + Cidade.class.getName()));
	}
	
	public List<Cidade> listar(){

		return repository.findAll();
	}
	

}
