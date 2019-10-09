package fr.trocit.jack.entity;

import javax.persistence.Entity;

@Entity
public class Usr extends GenericEntity {

	public String username;
	public String password;
	public String phone;
	public String town;
	
	public GiveList giveList;
}
