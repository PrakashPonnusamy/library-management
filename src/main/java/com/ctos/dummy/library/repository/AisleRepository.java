package com.ctos.dummy.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Library;

@Repository
public interface AisleRepository extends JpaRepository<Aisle, Integer> {

	List<Aisle> findByLibrary(Library library);

	Aisle findByAisleName(String aisleName);
}
