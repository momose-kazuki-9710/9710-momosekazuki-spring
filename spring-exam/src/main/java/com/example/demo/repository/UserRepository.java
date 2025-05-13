package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value=""
            +"SELECT * "
            +"FROM users "
            +"WHERE name LIKE ?1", nativeQuery = true)
    List<User> findByKeyword(String keyword);

	@Query(value = ""
			+ "SELECT * "
			+ "FROM users "
			+ "WHERE email = ?1 "
			+ "  AND password = ?2", nativeQuery = true)
	Optional<User> findByEmailAndPassword(String email, String password);
}


