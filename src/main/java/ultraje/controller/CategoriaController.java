package ultraje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ultraje.DTO.CategoriaRequest;
import ultraje.mapper.CategoriaMapper;
import ultraje.model.Categoria;
import ultraje.service.CategoriaService;

@RequestMapping("/categorias")
@RestController()
public class CategoriaController {

	CategoriaService service;
	CategoriaMapper mapper;
	
	@Autowired
	public CategoriaController(CategoriaService service, CategoriaMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping
	public ResponseEntity<?> listarCategorias(){
		return new ResponseEntity<String>(service.getCategorias(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody CategoriaRequest categoriaRequest){
		return new ResponseEntity<String>(service.salvar(mapper.dtoToCategoria(categoriaRequest)), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Short id){
		return new ResponseEntity<Categoria>(service.getById(id), HttpStatus.OK);
	}
	
}
