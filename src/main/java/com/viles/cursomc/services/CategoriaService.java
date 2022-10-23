package com.viles.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viles.cursomc.domain.Categoria;
import com.viles.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired 
	CategoriaRepository repository;
	

	public Categoria buscarPorId(Integer id) {
		
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada no id "
				+ id, "Tipo:" + Categoria.class.getName()));
	}
	
	public List<Categoria> listar(){

		return repository.findAll();
	}
	

}
