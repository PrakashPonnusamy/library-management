package com.ctos.dummy.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctos.dummy.library.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

	@Query(name = "Library.findByNameLike")
    List<Library> findByNameLike(@Param("name") String name);
}
