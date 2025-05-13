package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	//@Query(value = "SQL文", nativeQuery = true)
	// → List<エンティティ> 使いたい処理名 (?に対応した引数)
	
	//@Query →カスタマイズ見たなイメージ
	@Query(value=""
			+"SELECT * "
			+"FROM items "
			+"WHERE name LIKE ?1", nativeQuery = true)
	List<Item> findByKeyword(String keyword);
	
	//categoryId による検索
	List<Item> findByCategoryId(Integer categoryId);
	
	//値段による範囲検索
	@Query(value=""
			+"SELECT * "
			+"FROM items "
			+"WHERE price <= ?1", nativeQuery = true)
	List<Item> findByMaxPrice(Integer maxPrice);
	
	//値段による範囲検索×ソート
	@Query(value=""
			+"SELECT * "
			+"FROM items "
			+"WHERE price <= ?1", nativeQuery = true)
	List<Item> findByMaxPrice(Integer maxPrice, Sort sort);
	
	@Query(value=""
			+"SELECT * "
			+"FROM items "
			+"ORDER BY price" , nativeQuery = true)
	List<Item> orderByPrice();
}
