package fr.trocit.jack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.trocit.jack.entity.GiveList;
import fr.trocit.jack.repository.CommonRepository;

@Service
public class GiveListService {
	
	@Autowired CommonRepository<GiveList> repo;
	
	public List<GiveList> getAll() {
		return repo.findAll();
	}
	
	public GiveList getById(int id) {
		return repo.getById(id);
	}
	
	public int create(GiveList list) {
		GiveList updatedList = repo.save(list);
		return updatedList.id;
	}
	
	public void remove(int id) {
		repo.delete(id);
	}

}
