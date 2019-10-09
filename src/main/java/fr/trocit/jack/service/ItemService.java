package fr.trocit.jack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.trocit.jack.entity.Item;
import fr.trocit.jack.repository.CommonRepository;

@Service
public class ItemService {

	@Autowired CommonRepository<Item> repo;
	
	public List<Item> getAll() {
		return repo.findAll();
	}
	
	public Item getById(int id) {
		return repo.getById(id);
	}
	
	public int create(Item item) {
		Item updatedItem = repo.save(item);
		return updatedItem.id;
	}
	
	public void remove(int id) {
		repo.delete(id);
	}
	
}
