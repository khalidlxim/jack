package fr.trocit.jack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.trocit.jack.entity.Item;
import fr.trocit.jack.service.ItemService;

@RestController
@RequestMapping("users/{usrId}/items")
public class ItemController {
	
	@Autowired ItemService serv;
	
	@GetMapping("")
	public ResponseEntity<ArrayNode> getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode displayList = mapper.createArrayNode();
		
		List<Item> listAll = serv.getAll();
		
		for(Item item:listAll) {
			displayList.add(item.toJsonNode());
		}
		
		return new ResponseEntity<ArrayNode>(displayList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ObjectNode> getById(@PathVariable int id) {
		Item item = serv.getById(id);
		if(item==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(item.toJsonNode(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createItem(@RequestBody Item item) {
		int id = serv.save(item);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateItem(@RequestBody Item newItem, @PathVariable int id) {
		if(serv.getById(id)==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Item currentItem = serv.getById(id);
		
		currentItem.setTitle(newItem.getTitle());
		currentItem.setPhoto(newItem.getPhoto());
		currentItem.setDescription(newItem.getDescription());
		currentItem.setCategories(newItem.getCategories());
		currentItem.setLikers(newItem.getLikers());
		
		serv.save(currentItem);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsr(@PathVariable int id) {
		Item currentItem = serv.getById(id);
		if(!serv.existItem(currentItem)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		serv.delete(currentItem);
		return new ResponseEntity<>("L'item a bien été supprimmé", HttpStatus.OK);
	}

}
