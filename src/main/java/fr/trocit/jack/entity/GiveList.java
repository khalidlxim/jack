package fr.trocit.jack.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity

public class GiveList extends GenericEntity {

	private int idOwner;

	private List<Item> items = new ArrayList<>();

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public ObjectNode toJsonNode() {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode listNode = mapper.createObjectNode();
		ArrayNode itemArrayNode = mapper.createArrayNode();
		
		listNode.put("id", this.id);
		listNode.put("idOwner", this.idOwner);
		
		listNode.putArray("items");
		
		for(Item item:this.items) {
			itemArrayNode.add(item.toJsonNode());
		}
		
		listNode.set("items", itemArrayNode);
		
		return listNode;
	}
}
	
