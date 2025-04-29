package com.library.demo.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequestDTO {
    private String title;
    private String author;
    private int publishedYear;
    private int availableCopies;
    private int count;
}
