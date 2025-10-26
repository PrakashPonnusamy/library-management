package com.ctos.dummy.library;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.repository.AisleRepository;
import com.ctos.dummy.library.repository.BookRepository;
import com.ctos.dummy.library.repository.LibraryRepository;
import com.ctos.dummy.library.service.impl.LibraryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LibraryServiceImplTest {

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private AisleRepository aisleRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    private Library library;
    private Aisle aisle;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setBookId(1);
        book.setBookName("Core Java");

        aisle = new Aisle();
        aisle.setAisleId(1);
        aisle.setAisleName("Natural History");
        aisle.setBooks(List.of(book));

        library = new Library();
        library.setLibraryId(1);
        library.setLibraryName("Central Library");
        library.setAisles(List.of(aisle));
    }

    @Test
    void testSaveLibrary() {
        when(libraryRepository.save(library)).thenReturn(library);
        Library saved = libraryService.saveLibrary(library);
        assertNotNull(saved);
        assertEquals("Central Library", saved.getLibraryName());
    }

    @Test
    void testUpdateLibrary_Success() {
        when(libraryRepository.findById(1)).thenReturn(Optional.of(library));
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        Library updatedLibrary = new Library();
        updatedLibrary.setLibraryName("Updated Library");
        updatedLibrary.setAisles(List.of());

        Library result = libraryService.updateLibrary(1, updatedLibrary);
        assertEquals("Updated Library", result.getLibraryName());
    }

    @Test
    void testGetAllAislesByLibraryName() {
        when(libraryRepository.findByNameLike("%Central%")).thenReturn(List.of(library));
        when(aisleRepository.findByLibrary(library)).thenReturn(List.of(aisle));

        List<Aisle> aisles = libraryService.getAllAislesByLibraryName("Central");
        assertEquals(1, aisles.size());
        assertEquals("Natural History", aisles.get(0).getAisleName());
    }

    @Test
    void testGetAllBooksByAisleName() {
        when(aisleRepository.findByAisleName("Natural History")).thenReturn(aisle);

        List<Book> books = libraryService.getAllBooksByAisleName("Natural History");
        assertEquals(1, books.size());
        assertEquals("Core Java", books.get(0).getBookName());
    }
}

