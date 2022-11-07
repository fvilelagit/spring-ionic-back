package com.viles.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viles.cursomc.domain.Categoria;
import com.viles.cursomc.domain.Cidade;
import com.viles.cursomc.domain.Cliente;
import com.viles.cursomc.domain.Endereco;
import com.viles.cursomc.domain.Estado;
import com.viles.cursomc.domain.ItemPedido;
import com.viles.cursomc.domain.Pagamento;
import com.viles.cursomc.domain.PagamentoComBoleto;
import com.viles.cursomc.domain.PagamentoComCartao;
import com.viles.cursomc.domain.Pedido;
import com.viles.cursomc.domain.Produto;
import com.viles.cursomc.domain.enums.EstadoPagamento;
import com.viles.cursomc.domain.enums.TipoCliente;
import com.viles.cursomc.repositories.CategoriaRepository;
import com.viles.cursomc.repositories.CidadeRepository;
import com.viles.cursomc.repositories.ClienteRepository;
import com.viles.cursomc.repositories.EnderecoRepository;
import com.viles.cursomc.repositories.EstadoRepository;
import com.viles.cursomc.repositories.ItemPedidoRepository;
import com.viles.cursomc.repositories.PedidoRepository;
import com.viles.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.flush();
		categoriaRepository.flush();
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2); 

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));

		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		estadoRepository.flush();
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		Cliente cli1 = new Cliente (null,"Maria Silva", "maria@gmail.com", "965441325", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("39354752", "99885566"));
		clienteRepository.save(cli1);
		clienteRepository.flush();
		
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 302", "jardim", "45889952",cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Zezito Amarante", "96", "Sem Complemento", "Lot Casa Azul", "84457255",cli1, c2);
		Endereco ez = new Endereco(null, "Rua flores", "300", "Apto 302", "jardim", "45889952",null, c1);
		
		enderecoRepository.save(ez);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2022 11:40"),cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2022 11:40"),cli1,e1);

		
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2,sdf.parse("30/10/2022 00:00"),null);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		pedidoRepository.save(ped1);
		pedidoRepository.save(ped2);
		
		ItemPedido ip1 = new ItemPedido(ped1,p1, 0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3, 0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2, 100.00,1,800.00);
		
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}
}
	
