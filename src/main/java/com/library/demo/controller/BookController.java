package com.library.demo.controller;

import com.library.demo.dto.requestDTO.BookRequestDTO;
import com.library.demo.dto.requestDTO.UserRequestDTO;
import com.library.demo.dto.responseDTO.BookListResponseDTO;
import com.library.demo.dto.responseDTO.BookResponseDTO;
import com.library.demo.dto.responseDTO.UserResponseDTO;
import com.library.demo.dto.searchDTO.BookSearchDTO;
import com.library.demo.entity.Book;
import com.library.demo.service.book.BookService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.demo.util.Helper.getSortingProperty;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book/v1")
public class BookController {

    private final BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<BookResponseDTO> addBook (
            @RequestBody BookRequestDTO userRequestDTO
    ){
        log.info("CALLED BOOK CREATION END POINT");
        return new ResponseEntity<>(bookService.addBook(userRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<BookListResponseDTO> searchApplicationLocation(
            @Min(1)
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,

            @Min(10)
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,

            @RequestParam(name = "sortType", required = false, defaultValue = "DESC") String sortType,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,

            @RequestBody BookSearchDTO searchDTO
    ) {
        log.info("SEARCH BOOKS");
        Sort sort = getSortingProperty(sortType, sortBy);
        Pageable pageRequest = PageRequest.of(page - 1, pageSize, sort);
        return new ResponseEntity<>(bookService.searchBooks(pageRequest, searchDTO), HttpStatus.OK);

    }
}
