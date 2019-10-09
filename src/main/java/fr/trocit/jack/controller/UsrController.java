package fr.trocit.jack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.trocit.jack.dto.UsrDto;
import fr.trocit.jack.entity.Usr;
import fr.trocit.jack.service.GiveListService;
import fr.trocit.jack.service.UsrService;

@RestController
@RequestMapping("users")
public class UsrController {
	
	@Autowired UsrService serv;
	@Autowired GiveListService listServ;
	
	@GetMapping("")
	public List<UsrDto> getAll() {
		List<Usr> listAll = serv.getAll();
		List<UsrDto> displayList = new ArrayList<UsrDto>();
		
		for(Usr usr:listAll) {
			UsrDto dto = new UsrDto(usr);
			displayList.add(dto);
		}
		return displayList;
	}
}
