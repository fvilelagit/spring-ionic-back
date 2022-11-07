package com.viles.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viles.cursomc.domain.Pedido;

import com.viles.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired 
	PedidoRepository repository;
	

	public Pedido buscarPorId(Integer id) {
		
		Optional<Pedido> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrada no id "
				+ id, "Tipo:" + Pedido.class.getName()));
	}
	
	public List<Pedido> listar(){

		return repository.findAll();
	}
	

}
