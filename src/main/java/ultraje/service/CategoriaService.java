package ultraje.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import ultraje.model.Categoria;

@Service
public class CategoriaService {

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
		
		Categoria categoria = em.find(Categoria.class, id);
		
		return categoria;
	}
	
}
