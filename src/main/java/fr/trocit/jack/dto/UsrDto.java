package fr.trocit.jack.dto;

import fr.trocit.jack.entity.Usr;

public class UsrDto {
	
	public int id;
	public String username;
	public String password;
	public String email;
	public String phone;
	public String town;
	
	public GiveListDto giveList;

	public UsrDto() {
		super();
	}
	
	public UsrDto(Usr usr) {
		super();
		
		this.id = usr.id;
		this.username = usr.username;
		this.password = usr.password;
		this.email = usr.email;
		this.phone = usr.phone;
		this.town = usr.town;
		this.giveList = new GiveListDto(usr.giveList);
	}
}
