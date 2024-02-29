package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.BookDto;
import edu.icet.entity.BookEntity;
import edu.icet.repository.BookRepository;
import edu.icet.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    BookServiceImpl(BookRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
    BookRepository repository;
    ObjectMapper mapper;
    @Override
    public BookEntity post(BookDto dto) {
        return repository.save(mapper.convertValue(dto, BookEntity.class));
    }

    @Override
    public List<BookDto> getAllBooks() {
        Iterable<BookEntity> list =  repository.findAll();
        Iterator<BookEntity> iterator = list.iterator();
        ArrayList<BookDto> dtoList = new ArrayList<>();

        while (iterator.hasNext()){
            dtoList.add(mapper.convertValue(iterator.next(), BookDto.class));
        }
        log.info(list.toString());
        return dtoList;
    }

    @Override
    public boolean delete(String isbn) {

        Optional<BookEntity> optionalBookEntity = repository.findById(isbn);

        if(optionalBookEntity.isPresent()){
            repository.deleteById(isbn);
            return true;
        }
        return false;
    }
}
