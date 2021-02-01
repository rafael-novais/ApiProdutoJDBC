package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.ProdutoRequest;
import com.example.demo.DTOs.ProdutoResponse;
import com.example.demo.mappers.ProdutoMapper;
import com.example.demo.services.ProdutoService;


@RestController()
public class ProdutoController {
	
	ProdutoService service;
	ProdutoMapper mapper;
	
	@Autowired
	public ProdutoController(ProdutoService service, ProdutoMapper mapper){
		this.service = service;
		this.mapper = mapper;
	}

    @GetMapping("/produtos")
    public List<ProdutoResponse> listarProdutos() throws SQLException{
        return this.mapper.dtoFromProduto(this.service.getProdutos()); 
    }

    @PostMapping("/add/produto")
    public int adicionarProduto(@RequestBody ProdutoRequest produtoRequest) throws SQLException{
    	return service.adicionarProduto(mapper.produtoRequestToProduto(produtoRequest));
    }
    
    @DeleteMapping("/produto/{id}")
    public int removerProduto(@PathVariable int id) throws SQLException  {
    	return service.removerProduto(id);
    }

}