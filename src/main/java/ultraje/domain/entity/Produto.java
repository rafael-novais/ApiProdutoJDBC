package ultraje.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	private int id;
    private String nome;
    private String descricao;
    private double preco;
    
}