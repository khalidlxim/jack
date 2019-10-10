package fr.trocit.jack.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity
@Table(name="givelist")
public class GiveList extends GenericEntity {

	@OneToOne
	@JoinColumn(name="usr_id")
	private Usr owner;

	@OneToMany(mappedBy="list")
	private List<Item> usrItems = new ArrayList<>();

	public Usr getIdOwner() {
		return owner;
	}

	public void setIdOwner(Usr owner) {
		this.owner = owner;
	}

	public List<Item> getItems() {
		return usrItems;
	}

	public void setItems(List<Item> items) {
		this.usrItems = items;
	}
	
	public ObjectNode toJsonNode() {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode listNode = mapper.createObjectNode();
		ArrayNode itemArrayNode = mapper.createArrayNode();
		
		listNode.put("id", this.id);
		
		listNode.putArray("owner");
		listNode.set("owner", this.owner.toJsonNode());
		
		listNode.putArray("items");
		
		for(Item item:this.usrItems) {
			itemArrayNode.add(item.toJsonNode());
		}
		
		listNode.set("items", itemArrayNode);
		
		return listNode;
	}
}
	
