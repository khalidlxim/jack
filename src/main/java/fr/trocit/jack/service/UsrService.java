package fr.trocit.jack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.trocit.jack.entity.Usr;
import fr.trocit.jack.repository.CommonRepository;

@Service
public class UsrService {
	
	@Autowired CommonRepository<Usr> repo;
	
	public List<Usr> getAll() {
		return repo.getAll();
	}
	
	public Usr getById(int id) {
		return repo.getById(id);
	}
	
	public int save(Usr usr) {
		Usr updatedUsr = repo.save(usr);	
		return updatedUsr.id;
	}
	
	public void delete(Usr usr) {
		repo.delete(usr);
	}
	
	public boolean existUsr(Usr usr) { 
	return repo.existsById(usr.id);
	}
}
