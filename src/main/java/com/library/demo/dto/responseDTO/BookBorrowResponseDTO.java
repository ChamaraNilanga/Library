package com.library.demo.dto.responseDTO;

import com.library.demo.entity.Book;
import com.library.demo.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookBorrowResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private BookResponseDTO book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
