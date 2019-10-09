package fr.trocit.jack.dto;

import java.util.ArrayList;
import java.util.List;

import fr.trocit.jack.entity.GiveList;
import fr.trocit.jack.entity.Item;

public class GiveListDto {
	
	public int id;
	public int idOwner;
	public List<ItemDto> items = new ArrayList<>();
	
	public GiveListDto() {
		super();
	}
	
	public GiveListDto(GiveList list) {
		super();
		
		this.id = list.id;
		this.idOwner = list.idOwner;
		for(Item item:list.items)this.items.add(new ItemDto(item));
	}

}
