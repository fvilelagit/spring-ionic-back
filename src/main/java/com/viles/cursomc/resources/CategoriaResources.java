package com.viles.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viles.cursomc.domain.Categoria;
import com.viles.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

	@Autowired
	CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) {
		Categoria cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	
	}
	
}
