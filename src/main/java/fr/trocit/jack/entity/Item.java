package fr.trocit.jack.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity

public class Item extends GenericEntity {

	public String title;
	public String photo;
	public String description;
	
	
	public int idList;
	
	
	public List<Category> categories = new ArrayList<>();
	
	public String status;
}
