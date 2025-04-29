package com.library.demo.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private int publishedYear;
    private int availableCopies;
    private int count;
}
