package fr.trocit.jack.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.trocit.jack.entity.Category;

@Repository
public class CategoryRepository extends CommonRepository<Category> {

	public CategoryRepository(Class<Category> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		// TODO Auto-generated constructor stub
	}

}
