package fr.trocit.jack.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.trocit.jack.entity.GiveList;

@Repository
public class GiveListRepository extends CommonRepository<GiveList> {

	public GiveListRepository(Class<GiveList> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		// TODO Auto-generated constructor stub
	}

}
