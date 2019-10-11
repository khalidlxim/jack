package fr.trocit.jack.repository;

import java.util.List;

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
	
	public T getById(int id) {
		return em.find(entityClass, id);
	}
	
	public List<T> getAll() {
		return findAll();
	}
	
//	public void delete(int id) {
//		T t = getById(id);
//		em.remove(t);
//	}
	
	public void delete(T entity) {
		em.remove(entity);
	}
	
	public void persist(T entity) {
		em.persist(entity);
	}
	public T merge(T entity) {
		return em.merge(entity);
	}
	
//	public T saveEntity(T entity) {
//		if(entity.id>0) return merge(entity);
//		else {
//			persist(entity);
//			return entity;
//		}
//	}

}
