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
		if(item==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(item.toJsonNode(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createUsr(@RequestBody Usr usr) {
		int id = serv.save(usr);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateUsr(@RequestBody Usr newUsr, @PathVariable int id) {
		if(serv.getById(id)==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Usr currentUsr = serv.getById(id);
		
		currentUsr.setUsername(newUsr.getUsername());
		currentUsr.setPassword(newUsr.getPassword());
		currentUsr.setAvatar(newUsr.getAvatar());
		currentUsr.setEmail(newUsr.getEmail());
		currentUsr.setPhone(newUsr.getPhone());
		currentUsr.setTown(newUsr.getTown());
		currentUsr.setGiveList(newUsr.getGiveList());
		currentUsr.setLikedItems(newUsr.getLikedItems());
		
		serv.save(currentUsr);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsr(@PathVariable int id) {
		Usr currentUsr = serv.getById(id);
		if(!serv.existUsr(currentUsr)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		serv.delete(currentUsr);
		return new ResponseEntity<>("L'utilisateur a bien été supprimmé", HttpStatus.OK);
	}

}
