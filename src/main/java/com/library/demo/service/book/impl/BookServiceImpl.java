package com.library.demo.service.book.impl;

import com.library.demo.dto.requestDTO.BookRequestDTO;
import com.library.demo.dto.responseDTO.BookListDTO;
import com.library.demo.dto.responseDTO.BookListResponseDTO;
import com.library.demo.dto.responseDTO.BookResponseDTO;
import com.library.demo.dto.searchDTO.BookSearchDTO;
import com.library.demo.entity.Book;
import com.library.demo.exception.DataValidationException;
import com.library.demo.exception.ResourceNotFoundException;
import com.library.demo.repository.BookRepository;
import com.library.demo.service.book.BookService;
import com.library.demo.spcification.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequestDTO,book);
        bookRepository.findByTitle(bookRequestDTO.getTitle())
                .ifPresent(record -> {
                    throw new DataValidationException(
                            "Book already added.", bookRequestDTO.getTitle()
                    );
                });
        book = bookRepository.save(book);
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        BeanUtils.copyProperties(book,bookResponseDTO);
        return bookResponseDTO;
    }

    @Override
    public BookListResponseDTO searchBooks(Pageable pageRequest, BookSearchDTO bookSearch) {
        Specification<Book> specification = BookSpecification.bookSearch(bookSearch);
        Page<Book> books = bookRepository.findAll(specification,pageRequest);
        return mapToBookListResponse(books);
    }

    @Override
    public Book getBookById(Long bookId) {

        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
    }

    @Override
    public void updateBookAvailableCount(Book book) {
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }

    @Override
    public BookListDTO getAllBooks() {
        List<BookResponseDTO> bookResponseDTOList = bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();

        return new BookListDTO(bookResponseDTOList);
    }

    private BookResponseDTO convertToDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }

    private BookListResponseDTO mapToBookListResponse(Page<Book> books) {
        List<BookResponseDTO> bookResponseDTOList = books.stream()
                .map(book -> {
                    BookResponseDTO dto = new BookResponseDTO();
                    BeanUtils.copyProperties(book, dto);
                    return dto;
                })
                .toList();

        return BookListResponseDTO.builder()
                .books(bookResponseDTOList)
                .currentPage(books.getNumber() + 1)
                .totalPages(books.getTotalPages())
                .totalRecords(books.getTotalElements())
                .build();
    }


}
