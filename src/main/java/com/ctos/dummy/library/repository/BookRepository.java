package com.ctos.dummy.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	List<Book> findByAisles(Aisle aisle);
}
