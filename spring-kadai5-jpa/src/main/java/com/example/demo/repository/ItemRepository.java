package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

	//@Query(value = "SQL文", nativeQuery = true)
	// → List<エンティティ> 使いたい処理名 (?に対応した引数)
	
	//@Query →カスタマイズ見たなイメージ
//	@Query(value=""
//			+"SELECT * "
//			+"FROM items "
//			+"WHERE name LIKE ?1", nativeQuery = true)
//	List<Item> findByKeyword(String keyword);
	
	List<Item> findByCategoryId(Integer categoryId);
	
	@Query(value=""
			+"SELECT * "
			+"FROM items "
			+"WHERE name price <= ?", nativeQuery = true)
	List<Item> findByPrice(Integer price);
}
