package com.library.demo.spcification;

import com.library.demo.dto.searchDTO.BookSearchDTO;
import com.library.demo.entity.Book;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BookSpecification {
    public static Specification<Book> bookSearch(BookSearchDTO bookSearchDTO) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (bookSearchDTO.getTitle()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("title"), bookSearchDTO.getTitle()));
            }

            if (bookSearchDTO.getAuthor()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("author"), bookSearchDTO.getAuthor()));
            }

            if (bookSearchDTO.getPublishedYear()!=0) {
                predicates.add(criteriaBuilder.equal(root.get("publishedYear"), bookSearchDTO.getPublishedYear()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
