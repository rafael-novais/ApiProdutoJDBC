package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.DTOs.ProdutoRequest;
import com.example.demo.DTOs.ProdutoResponse;
import com.example.demo.model.Produto;

@Component
public class ProdutoMapper {

	public List<ProdutoResponse> produtoListToResonseList(List<Produto> produtos){
		ArrayList<ProdutoResponse> produtosResponse = new ArrayList<ProdutoResponse>();
		for(Produto produto : produtos) {
			produtosResponse.add(
					new ProdutoResponse(
							produto.getId(), 
							produto.getNome(), 
							produto.getDescricao(), 
							produto.getPreco()));
		}
		return produtosResponse;
	}
	
	public Produto produtoRequestToProduto(ProdutoRequest produtoRequest) {
		Produto produto = new Produto();
		produto.setNome(produtoRequest.getNome());
		produto.setDescricao(produtoRequest.getDescricao());
		produto.setPreco(produtoRequest.getPreco());
		return produto;
	}
	
	public ProdutoResponse produtoToResponse(Produto produto) {
		return new ProdutoResponse(
				produto.getId(),
				produto.getNome(), 
				produto.getDescricao(),
				produto.getPreco());
	}
	
}
