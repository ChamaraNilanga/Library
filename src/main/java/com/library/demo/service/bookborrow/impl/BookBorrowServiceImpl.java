package com.library.demo.service.bookborrow.impl;

import com.library.demo.dto.responseDTO.BookBorrowResponseDTO;
import com.library.demo.entity.Book;
import com.library.demo.entity.BorrowRecord;
import com.library.demo.repository.BorrowRecordRepository;
import com.library.demo.service.book.BookService;
import com.library.demo.service.bookborrow.BookBorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookBorrowServiceImpl implements BookBorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookService bookService;

    @Override
    public BookBorrowResponseDTO borrowBook(Long bookId, Long userId) {
        Book book = bookService.getBookById(bookId);
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBook(book);
        return new BookBorrowResponseDTO();
    }
}
