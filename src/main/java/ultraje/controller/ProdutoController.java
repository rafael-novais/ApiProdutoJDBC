package ultraje.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ultraje.domain.dto.ProdutoRequest;
import ultraje.domain.dto.ProdutoResponse;
import ultraje.mapper.ProdutoMapper;
import ultraje.service.ProdutoService;


@RequestMapping("/produtos")
@RestController()
public class ProdutoController {
	
	ProdutoService service;
	ProdutoMapper mapper;
	
	@Autowired
	public ProdutoController(ProdutoService service, ProdutoMapper mapper){
		this.service = service;
		this.mapper = mapper;
	}

    @GetMapping
    public ResponseEntity<?> listarProdutos(){
    	try {
    		return new ResponseEntity<List<ProdutoResponse>>(
    				this.mapper.produtoListToResonseList(this.service.getProdutos()), 
    				HttpStatus.OK); 
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
        
    }

    @PostMapping
    public ResponseEntity<?> adicionarProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
    	try {
    		return new ResponseEntity<Integer>(
    				service.adicionarProduto(mapper.produtoRequestToProduto(produtoRequest)), 
    				HttpStatus.CREATED);    		
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto(@PathVariable int id){
    	try {
    		return new ResponseEntity<Integer>(
    				service.removerProduto(id), 
    				HttpStatus.NO_CONTENT);
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarProduto(@RequestBody ProdutoRequest request, @PathVariable int id){
    	try {
    		return new ResponseEntity<ProdutoResponse>(
    				mapper.produtoToResponse(service.alterarProduto(mapper.produtoRequestToProduto(request), id)), 
    				HttpStatus.OK);    		
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
    	try {
    		return new ResponseEntity<ProdutoResponse>(
    				mapper.produtoToResponse(service.getById(id)),
    				HttpStatus.OK);    		
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

}