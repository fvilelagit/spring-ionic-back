package com.viles.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viles.cursomc.domain.Pedido;
import com.viles.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResources {

	@Autowired
	PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Pedido> listarPorId(@PathVariable Integer id) {
		Pedido cat = service.buscarPorId(id);
		return ResponseEntity.ok().body(cat);
	
	}
	
}
