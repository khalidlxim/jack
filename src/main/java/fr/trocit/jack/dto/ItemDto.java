package fr.trocit.jack.dto;

import java.util.ArrayList;
import java.util.List;

import fr.trocit.jack.entity.Category;
import fr.trocit.jack.entity.Item;

public class ItemDto {
	
	public int id;
	public String title;
	public String photo;
	public String description;
	public int idList;
	public List<Category> categories = new ArrayList<>();
	public String status;
	
	public ItemDto() {
		super();
	}
	
	public ItemDto(Item item) {
		super();
		
		this.id = item.id;
		this.title = item.title;
		this.photo = item.photo;
		this.description = item.description;
		this.idList = item.idList;
		for(Category category:item.categories)this.categories.add(category);
		this.status = item.status;
	}

}
