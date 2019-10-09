package fr.trocit.jack.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.trocit.jack.entity.GenericEntity;

public class CommonRepository<T extends GenericEntity> extends SimpleJpaRepository<T, Integer> {
	
	protected final EntityManager em;
	protected final Class<T> entityClass;

	public CommonRepository(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.em = entityManager;
		this.entityClass = domainClass;
	}
	
	public void persist(T entity) {
		em.persist(entity);
	}
	
	public void remove(T entity) {
		em.remove(entity);
	}
	
	public T merge(T entity) {
		return em.merge(entity);
	}
	
	public T getById(Integer id) {
		return em.find(entityClass, id);
	}

}
