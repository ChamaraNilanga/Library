package com.library.demo.service.book;

import com.library.demo.dto.requestDTO.BookRequestDTO;
import com.library.demo.dto.responseDTO.BookListDTO;
import com.library.demo.dto.responseDTO.BookListResponseDTO;
import com.library.demo.dto.responseDTO.BookResponseDTO;
import com.library.demo.dto.searchDTO.BookSearchDTO;
import com.library.demo.entity.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDTO addBook(BookRequestDTO userRequestDTO);

    BookListResponseDTO searchBooks(Pageable pageRequest, BookSearchDTO searchDTO);

    Book getBookById(Long bookId);

    void updateBookAvailableCount(Book book);

    BookListDTO getAllBooks();
}
