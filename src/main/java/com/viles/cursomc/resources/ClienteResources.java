package com.viles.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viles.cursomc.domain.Cliente;
import com.viles.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResources {

	@Autowired
	ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Integer id) {
		Cliente cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	
	}
	
}
