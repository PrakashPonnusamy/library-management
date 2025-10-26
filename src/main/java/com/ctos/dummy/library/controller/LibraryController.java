package com.ctos.dummy.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.service.LibraryService;

@RestController
@RequestMapping(path = "/api/libraries")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@GetMapping("/books")
	public List<Book> getBooksFromLibraryAndAisle(@RequestParam String libraryName, @RequestParam String aisleName) {

		// Ensure that the aisle belongs to the given library
		List<Aisle> aisles = libraryService.getAllAislesByLibraryName(libraryName);
		return aisles.stream().filter(a -> a.getAisleName().equalsIgnoreCase(aisleName)).findFirst().map(Aisle::getBooks)
				.orElse(List.of());
	}

	// 14. POST - save library information
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Library saveLibrary(@RequestBody Library library) {
		return libraryService.saveLibrary(library);
	}

	// 15. PUT - update library information
	@PutMapping("/{libraryId}")
	public Library updateLibrary(@PathVariable int libraryId, @RequestBody Library library) {
		return libraryService.updateLibrary(libraryId, library);
	}

	// 16. GET - get all aisles based on library name
	@GetMapping("/{libraryName}/aisles")
	public List<Aisle> getAllAisles(@PathVariable String libraryName) {
		return libraryService.getAllAislesByLibraryName(libraryName);
	}

}
