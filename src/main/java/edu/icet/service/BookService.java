package edu.icet.service;

import edu.icet.dto.BookDto;
import edu.icet.entity.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity post(BookDto dto);
    List<BookDto> getAllBooks();
    boolean delete(String isbn);
}
