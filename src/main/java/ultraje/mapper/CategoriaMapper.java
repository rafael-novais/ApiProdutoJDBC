package ultraje.mapper;

import org.springframework.stereotype.Component;

import ultraje.domain.dto.CategoriaRequest;
import ultraje.domain.entity.Categoria;

@Component
public class CategoriaMapper {

	public Categoria dtoToCategoria(CategoriaRequest catReq) {
		Categoria categoria =  new Categoria();
		categoria.setNome(catReq.getNome());
		return categoria;
	}
}
