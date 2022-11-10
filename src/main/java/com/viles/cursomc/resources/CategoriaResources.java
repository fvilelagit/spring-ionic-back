package com.viles.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.viles.cursomc.domain.Categoria;
import com.viles.cursomc.dto.CategoriaDTO;
import com.viles.cursomc.services.CategoriaService;
import com.viles.cursomc.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

	@Autowired
	CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		List<Categoria> lista = service.listar();
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) {
		Categoria cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria cat){
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria cat, @PathVariable Integer id){
		
		cat.setId(id);
		cat = service.update(cat);

		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value="/{id}", method= RequestMethod.DELETE)	
	public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
