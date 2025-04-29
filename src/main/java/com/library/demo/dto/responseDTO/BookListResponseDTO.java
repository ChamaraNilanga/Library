package com.library.demo.dto.responseDTO;

import com.library.demo.dto.requestDTO.PageableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BookListResponseDTO extends PageableDto {
    private List<BookResponseDTO> books;
}
