package fr.trocit.jack.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.trocit.jack.entity.Usr;

@Repository
public class UsrRepository extends CommonRepository<Usr> {

	public UsrRepository(Class<Usr> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		// TODO Auto-generated constructor stub
	}

}
