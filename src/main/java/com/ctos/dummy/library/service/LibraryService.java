package com.ctos.dummy.library.service;

import java.util.List;
import java.util.Optional;

import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;

public interface LibraryService {

	public List<Aisle> getAllAislesByLibraryName(String libraryName);

	// Save new library
	public Library saveLibrary(Library library);

	// Update existing library
	public Library updateLibrary(int libraryId, Library updatedLibrary);

	// Get all books based on aisle name
	public List<Book> getAllBooksByAisleName(String aisleName);
}
