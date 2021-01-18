package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.DTOs.ProdutoResponse;
import com.example.demo.model.Produto;

@Component
public class ProdutoMapper {

	public List<ProdutoResponse> dtoFromProduto(List<Produto> produtos){
		
		ArrayList<ProdutoResponse> produtosResponse = new ArrayList<ProdutoResponse>();
		
		for(Produto produto : produtos) {
			produtosResponse.add(
					new ProdutoResponse(
							produto.getId(), 
							produto.getNome(), 
							produto.getDescricao(), 
							produto.getPreco())
					);
		}
		return produtosResponse;
	}
	
}
