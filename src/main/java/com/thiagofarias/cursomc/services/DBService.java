package com.thiagofarias.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thiagofarias.cursomc.domain.Categoria;
import com.thiagofarias.cursomc.domain.Cidade;
import com.thiagofarias.cursomc.domain.Cliente;
import com.thiagofarias.cursomc.domain.Endereco;
import com.thiagofarias.cursomc.domain.Estado;
import com.thiagofarias.cursomc.domain.ItemPedido;
import com.thiagofarias.cursomc.domain.Pagamento;
import com.thiagofarias.cursomc.domain.PagamentoComBoleto;
import com.thiagofarias.cursomc.domain.PagamentoComCartao;
import com.thiagofarias.cursomc.domain.Pedido;
import com.thiagofarias.cursomc.domain.Produto;
import com.thiagofarias.cursomc.domain.enums.EstadoPagamento;
import com.thiagofarias.cursomc.domain.enums.Perfil;
import com.thiagofarias.cursomc.domain.enums.TipoCliente;
import com.thiagofarias.cursomc.repositories.CategoriaRepository;
import com.thiagofarias.cursomc.repositories.CidadeRepository;
import com.thiagofarias.cursomc.repositories.ClienteRepository;
import com.thiagofarias.cursomc.repositories.EnderecoRepository;
import com.thiagofarias.cursomc.repositories.EstadoRepository;
import com.thiagofarias.cursomc.repositories.ItemPedidoRepository;
import com.thiagofarias.cursomc.repositories.PagamentoRepository;
import com.thiagofarias.cursomc.repositories.PedidoRepository;
import com.thiagofarias.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bpe;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat5 = new Categoria(null, "Casa");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 50.00);
		Produto p4 = new Produto(null, "Abajour", 500.00);
		Produto p6 = new Produto(null, "Toalha", 150.00);
		Produto p7 = new Produto(null, "Shampoo", 130.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p1, p3));
		cat4.getProdutos().addAll(Arrays.asList(p6, p7));
		cat5.getProdutos().addAll(Arrays.asList(p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat5));
		p6.getCategorias().addAll(Arrays.asList(cat4));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p6, p7));
		
		Estado est1 = new Estado(null, "Maranhão");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Timbiras", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "purrmageddon@gmail.com", "3422355", TipoCliente.PESSOAFISICA, bpe.encode("123"));	
		cli1.getTelefones().addAll(Arrays.asList("212134334", "23323223"));
		
		Cliente cli2 = new Cliente(null, "Anoun", "thiagojosef99@gmail.com", "52758290006", TipoCliente.PESSOAFISICA, bpe.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("3452134334", "47553223"));
		
		
		Endereco e1 = new Endereco(null, "Rua Manel", "389", "Próximo ao morro", "São Caetaano", "64312000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Bigres", "233", "Próximo ao morro", "São Jose", "432134", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida Brasil", "478", "Sala 309", "Centro", "234523", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("01/10/2017 11:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
