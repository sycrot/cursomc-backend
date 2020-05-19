package com.thiagofarias.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.thiagofarias.cursomc.domain.Categoria;
import com.thiagofarias.cursomc.domain.Produto;
import com.thiagofarias.cursomc.repositories.CategoriaRepository;
import com.thiagofarias.cursomc.repositories.ProdutoRepository;
import com.thiagofarias.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+ ", Tipo: "+Produto.class.getName()
				));
		
	}
	
	public Page<Produto> search(
			String nome, 
			List<Integer> ids, 
			Integer page, 
			Integer linesPerPage, 
			String orderBy, 
			String direction) {
		
		PageRequest pageRequest = PageRequest.of(
				page, 
				linesPerPage, 
				Direction.valueOf(direction), 
				orderBy);
		
		List<Categoria> categorias = catRepo.findAllById(ids);
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}
	
	/* public Produto insert(Produto obj) {
		
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Produto update(Produto obj) {
		
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluír uma categoria que tem produtos");
		}
	}
	
	public List<Produto> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Produto fromDTO(ProdutoDTO obj) {
		return new Produto(obj.getId(), obj.getNome());
	}
	
	private void updateData(Produto newObj, Produto obj) {
		
		newObj.setNome(obj.getNome()); 
		
	} */
	
}
