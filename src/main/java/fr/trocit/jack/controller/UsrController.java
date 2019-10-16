package fr.trocit.jack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import fr.trocit.jack.entity.Usr;
import fr.trocit.jack.repository.AbstractItemRepository;
import fr.trocit.jack.repository.AbstractUsrRepository;
import fr.trocit.jack.entity.GiveList;
import fr.trocit.jack.entity.Item;
import fr.trocit.jack.entity.Usr;
import fr.trocit.jack.service.GiveListService;
import fr.trocit.jack.service.ItemService;
import fr.trocit.jack.service.UsrService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("")
public class UsrController {
	
	@Autowired UsrService serv;
	@Autowired AbstractItemRepository irepo;
	@Autowired AbstractUsrRepository usrRepo;
	@Autowired GiveListService listServ;
	@Autowired ItemService iServ;
	
	@GetMapping("users")
	public ResponseEntity<ArrayNode> getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode displayList = mapper.createArrayNode();
		
		List<Usr> listAll = serv.getAll();
		
		for(Usr usr:listAll) {
			displayList.add(usr.toJsonNode());
		}
		
		return new ResponseEntity<ArrayNode>(displayList, HttpStatus.OK);
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<ObjectNode> getById(@PathVariable int id) {
		Usr usr = serv.getById(id);
		if(usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(usr.toJsonNode(), HttpStatus.OK);
	}
	
	@PostMapping("users")
	public ResponseEntity<Integer> createUsr(@RequestBody Usr usr) {
		
		GiveList list = new GiveList();
		
		list.setItems(new ArrayList<Item>());
		
		usr.setGiveList(list);
		usr.setLikedItems(new ArrayList<Item>());
		
		int id = serv.save(usr);
		
		list.setOwner(usr);
		
		listServ.save(list);
		
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<Integer> updateUsr(@RequestBody Usr newUsr, @PathVariable int id) {
		if(serv.getById(id)==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Usr currentUsr = serv.getById(id);
		
		currentUsr.setUsername(newUsr.getUsername());
		currentUsr.setPassword(newUsr.getPassword());
		currentUsr.setAvatar(newUsr.getAvatar());
		currentUsr.setEmail(newUsr.getEmail());
		currentUsr.setPhone(newUsr.getPhone());
		currentUsr.setTown(newUsr.getTown());
		
		serv.save(currentUsr);
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<String> deleteUsr(@PathVariable int id) {
		Usr currentUsr = serv.getById(id);
		if(!serv.existUsr(currentUsr)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(!currentUsr.getGiveList().getItems().isEmpty()) {
			for (Item item:currentUsr.getGiveList().getItems()) {
				iServ.delete(item);
			}
		}
		
		listServ.delete(currentUsr.getGiveList());
		
		serv.delete(currentUsr);
		return new ResponseEntity<>("L'utilisateur a bien été supprimmé", HttpStatus.OK);
	}
	
	@GetMapping("users/{id}/sql")
	public ResponseEntity<ArrayNode> displayItem1(@PathVariable int id){
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode displayList = mapper.createArrayNode();
		int idGl=irepo.findGiveListId(id);
		List<Item> listAll = irepo.displayOtherItems(idGl);
		
		for(Item item:listAll) {
			displayList.add(item.toJsonNode());
		}
		
		return new ResponseEntity<ArrayNode>(displayList, HttpStatus.OK);
	}
	
	@GetMapping("/sql")
	public ResponseEntity<ArrayNode> displayItem2(){
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode displayList = mapper.createArrayNode();
		List<Item> listAll = irepo.displayAllItems();
		
		for(Item item:listAll) {
			displayList.add(item.toJsonNode());
		}
		
		return new ResponseEntity<ArrayNode>(displayList, HttpStatus.OK);
	}
	
	@GetMapping("users/{id}/liked")
	public ResponseEntity<ArrayNode> displayMyLikes(@PathVariable int id){
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode displayList = mapper.createArrayNode();
		
		Usr usr=serv.getById(id);
		
		List<Item> listAll = usr.getLikedItems();
		
		for(Item item:listAll) {
			displayList.add(item.toJsonNode());
		}
		
		return new ResponseEntity<ArrayNode>(displayList, HttpStatus.OK);
	}
	
	@PostMapping("users/validateLogin")
	public ResponseEntity<Integer> authenticateUsr(@RequestBody String login) {
		System.out.println(login);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(login);
		
		return new ResponseEntity<Integer>(usrRepo.validateLogin((String) map.get("username"),(String) map.get("password")).id, HttpStatus.OK);
	}
}
