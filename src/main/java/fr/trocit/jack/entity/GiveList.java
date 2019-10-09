package fr.trocit.jack.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class GiveList extends GenericEntity {

	public int idOwner;
	
	@JsonIgnore // TODO Remove this
	@OneToMany(mappedBy="idList")
	public List<Item> items = new ArrayList<>();
}
