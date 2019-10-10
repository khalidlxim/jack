package fr.trocit.jack.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity

public class Item extends GenericEntity {

	private String title;
	private String photo;
	private String description;
	private int idGiveList;
	
	private List<String> categories = new ArrayList<String>();
	
	private List<Usr> likers = new ArrayList<>();

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdList() {
		return idGiveList;
	}

	public void setIdList(int idList) {
		this.idGiveList = idList;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	

	public List<Usr> getLikers() {
		return likers;
	}

	public void setLikers(List<Usr> likers) {
		this.likers = likers;
	}

	
	public ObjectNode toJsonNode() {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode itemNode = mapper.createObjectNode();
		ArrayNode categoryArrayNode = mapper.createArrayNode();
		ArrayNode likerArrayNode = mapper.createArrayNode();
		
		itemNode.put("id", this.id);
		itemNode.put("title", this.title);
		itemNode.put("photo", this.photo);
		itemNode.put("description", this.description);
		itemNode.put("idGiveList", this.idGiveList);
		itemNode.putArray("categories");
		
		for (String category:this.categories) {
			categoryArrayNode.add(category);
		}
		itemNode.set("categories", categoryArrayNode);
		
		itemNode.putArray("likers");
		
		for (Usr liker:this.likers) {
			likerArrayNode.add(liker.toJsonNode());
		}
		itemNode.set("likers", likerArrayNode);
		
		return itemNode;
	}
}
