package com.viles.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viles.cursomc.domain.Cliente;

import com.viles.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired 
	ClienteRepository repository;
	

	public Cliente buscarPorId(Integer id) {
		
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrada no id "
				+ id, "Tipo:" + Cliente.class.getName()));
	}
	
	public List<Cliente> listar(){

		return repository.findAll();
	}
	

}
