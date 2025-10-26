package com.ctos.dummy.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.repository.AisleRepository;
import com.ctos.dummy.library.repository.BookRepository;
import com.ctos.dummy.library.repository.LibraryRepository;
import com.ctos.dummy.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	private AisleRepository aisleRepository;

	@Autowired
	private BookRepository bookRepository;

	public List<Aisle> getAllAislesByLibraryName(String libraryName) {
		List<Library> libraries = libraryRepository.findByNameLike("%" + libraryName + "%");
		if (!libraries.isEmpty()) {
			return aisleRepository.findByLibrary(libraries.get(0));
		}
		return List.of();
	}

	// Save new library
	public Library saveLibrary(Library library) {
		if (library.getAisles() != null) {
			library.getAisles().forEach(aisle -> {
				aisle.setLibrary(library);

				if (aisle.getBooks() != null) {
					aisle.getBooks().forEach(book -> {
						// ensure no null or transient conflict
						if (book.getBookId() == null) {
							// Hibernate will persist due to cascade
						}
					});
				}
			});
		}
		return libraryRepository.save(library);
	}

	// Update existing library
	public Library updateLibrary(int libraryId, Library updatedLibrary) {
		return libraryRepository.findById(libraryId).map(lib -> {
			lib.setLibraryName(updatedLibrary.getLibraryName());
			lib.setAisles(updatedLibrary.getAisles());
			return libraryRepository.save(lib);
		}).orElseThrow(() -> new RuntimeException("Library not found"));
	}

	// Get all books based on aisle name
	public List<Book> getAllBooksByAisleName(String aisleName) {
		Aisle aisle = aisleRepository.findByAisleName(aisleName);
		if (aisle != null) {
			return aisle.getBooks();
		}
		return List.of();
	}

}
