package com.library.demo.dto.searchDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookSearchDTO {
    private String title;
    private String author;
    private int publishedYear;
}
