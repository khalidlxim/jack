package fr.trocit.jack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.trocit.jack.entity.Item;

public interface AbstractItemRepository extends JpaRepository<Item, Integer> {
	@Query("SELECT i FROM Item i")
	public List<Item> displayAllItems();
	
	@Query("SELECT id FROM GiveList WHERE usr_id=?1")
	public int findGiveListId(int idInput);
	
	@Query("SELECT i FROM Item i WHERE gl_id<>?1")
	public List<Item> displayOtherItems(int idInput);

}
