package ultraje.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.domain.entity.Categoria;
import ultraje.mapper.CategoriaMapper;

@Service
public class CategoriaService {
	
	CategoriaMapper mapper;
	
	@Autowired
	public CategoriaService(CategoriaMapper mapper) {
		this.mapper = mapper;
	}

	public String getCategorias() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("categorias");
		EntityManager em = emf.createEntityManager();
		
		return "teste";
	}
	
	public String salvar(Categoria categoria) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("categorias");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(categoria);
		
		
		em.getTransaction().commit();
		return "ok";
	}
	
	public Categoria getById(Short id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("categorias");
		EntityManager em = emf.createEntityManager();
		return em.find(Categoria.class, id);
	}
	
}
