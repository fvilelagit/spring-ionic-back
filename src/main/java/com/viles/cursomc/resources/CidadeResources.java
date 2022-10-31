package com.viles.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viles.cursomc.domain.Cidade;
import com.viles.cursomc.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResources {

	@Autowired
	CidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		List<Cidade> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Cidade> listarPorId(@PathVariable Integer id) {
		Cidade cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	
	}
	
}
