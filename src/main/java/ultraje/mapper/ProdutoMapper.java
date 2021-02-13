package ultraje.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ultraje.DTO.ProdutoRequest;
import ultraje.DTO.ProdutoResponse;
import ultraje.model.Produto;

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
