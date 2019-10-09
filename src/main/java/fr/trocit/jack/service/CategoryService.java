package fr.trocit.jack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.trocit.jack.entity.Category;
import fr.trocit.jack.repository.CommonRepository;

@Service
public class CategoryService {

	@Autowired CommonRepository<Category> repo;
	
	public List<Category> getAll() {
		return repo.findAll();
	}
	
	public Category getById(int id) {
		return repo.getById(id);
	}
	
	public int create(Category category) {
		Category updatedCategory = repo.save(category);
		return updatedCategory.id;
	}
	
	public void remove(int id) {
		repo.deleteById(id);
	}
	
}
