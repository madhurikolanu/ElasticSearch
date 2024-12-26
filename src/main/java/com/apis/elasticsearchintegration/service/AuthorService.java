package com.apis.elasticsearchintegration.service;

import com.apis.elasticsearchintegration.converter.AuthorConverter;
import com.apis.elasticsearchintegration.document.AuthorDocument;
import com.apis.elasticsearchintegration.dto.Author;
import com.apis.elasticsearchintegration.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorConverter converter;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorConverter converter, AuthorRepository authorRepository) {
        this.converter = converter;
        this.authorRepository = authorRepository;
    }

    public void create(final Author dto){
        final AuthorDocument document = converter.toDocument(dto);
        if(dto.id() == null){
            document.setId(UUID.randomUUID().toString());
        }
        authorRepository.save(document);
    }

    public void delete(final String id){
        authorRepository.deleteById(id);
    }

    public Author findById(final String id){
        final AuthorDocument document = authorRepository.findById(id).orElse(null);
        return converter.fromDocument(document);
    }

    public List<Author> findAll(){
        final List<Author> dtos = new ArrayList<>();
        for (final AuthorDocument document : authorRepository.findAll())
            dtos.add(converter.fromDocument(document));
        return dtos;
    }


}
