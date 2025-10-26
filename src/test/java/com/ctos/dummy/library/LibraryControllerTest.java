package com.ctos.dummy.library;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.ctos.dummy.library.controller.LibraryController;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.service.LibraryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    private Library library;
    private Aisle aisle;
    private Book book;

    @BeforeEach
    void setUp() {
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
    void testGetBooksFromLibraryAndAisle() throws Exception {
        when(libraryService.getAllAislesByLibraryName("Central Library")).thenReturn(List.of(aisle));

        mockMvc.perform(get("/api/libraries/books")
                .param("libraryName", "Central Library")
                .param("aisleName", "Natural History"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookName").value("Core Java"));
    }

    @Test
    void testSaveLibrary() throws Exception {
        when(libraryService.saveLibrary(any(Library.class))).thenReturn(library);

        mockMvc.perform(post("/api/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(library)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libraryName").value("Central Library"));
    }

    @Test
    void testGetAllAisles() throws Exception {
        when(libraryService.getAllAislesByLibraryName("Central Library")).thenReturn(List.of(aisle));

        mockMvc.perform(get("/api/libraries/Central Library/aisles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aisleName").value("Natural History"));
    }
}

