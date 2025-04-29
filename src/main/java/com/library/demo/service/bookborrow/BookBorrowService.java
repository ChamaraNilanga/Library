package com.library.demo.service.bookborrow;

import com.library.demo.dto.responseDTO.BookBorrowResponseDTO;

public interface BookBorrowService {
    BookBorrowResponseDTO borrowBook(Long bookId, Long userId);
}
