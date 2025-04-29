package com.library.demo.service.bookborrow.impl;

import com.library.demo.dto.responseDTO.BookBorrowResponseDTO;
import com.library.demo.entity.Book;
import com.library.demo.entity.BorrowRecord;
import com.library.demo.entity.User;
import com.library.demo.repository.BorrowRecordRepository;
import com.library.demo.service.book.BookService;
import com.library.demo.service.bookborrow.BookBorrowService;
import com.library.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookBorrowServiceImpl implements BookBorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookService bookService;
    private final UserService userService;

    @Override
    public BookBorrowResponseDTO borrowBook(Long bookId, Long userId) {
        Book book = bookService.getBookById(bookId);

        User user = userService.getUserById(userId);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBook(book);
        borrowRecord.setUser(user);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setReturnDate(null);

        borrowRecord = borrowRecordRepository.save(borrowRecord);
        bookService.updateBookAvailableCount(book);
        BookBorrowResponseDTO response = new BookBorrowResponseDTO();
        BeanUtils.copyProperties(borrowRecord,response);

        return response;
    }
}
