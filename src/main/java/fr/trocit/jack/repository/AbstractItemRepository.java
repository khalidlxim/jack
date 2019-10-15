package fr.trocit.jack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.trocit.jack.entity.Item;

public interface AbstractItemRepository extends JpaRepository<Item, Integer> {
	@Query("SELECT title, photo, description, categories FROM Item")
	public List<Item> displayItems();
	/*@Query("SELECT title, photo, description, categorie FROM" + 
					"	(SELECT * FROM givelist) allLists" + 
					"	LEFT JOIN" + 
					"	(SELECT * FROM usr WHERE id={id}) currentUsr ON allLists.usr_id=currentUsr.id")
	public List<Item> displayItems();*/

}
