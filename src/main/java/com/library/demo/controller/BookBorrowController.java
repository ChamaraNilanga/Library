package com.library.demo.controller;

import com.library.demo.dto.requestDTO.BookRequestDTO;
import com.library.demo.dto.responseDTO.BookBorrowResponseDTO;
import com.library.demo.dto.responseDTO.BookResponseDTO;
import com.library.demo.service.bookborrow.BookBorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book-borrow/v1")
public class BookBorrowController {

    private final BookBorrowService bookBorrowService;

    @GetMapping("/borrow-book/{bookId}")
    public ResponseEntity<BookBorrowResponseDTO> borrowBook (
            @PathVariable Long bookId,
            @RequestParam Long userId
    ){
        log.info("CALLED BOOK BORROW END POINT");
        return new ResponseEntity<>(bookBorrowService.borrowBook(bookId,userId), HttpStatus.OK);
    }
}
