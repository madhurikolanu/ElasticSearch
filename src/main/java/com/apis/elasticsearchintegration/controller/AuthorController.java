package com.apis.elasticsearchintegration.controller;

import com.apis.elasticsearchintegration.dto.Author;
import com.apis.elasticsearchintegration.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    public Author findById(@PathVariable String id){
        return authorService.findById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody Author author){
        authorService.create(author);
    }

    @GetMapping
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        authorService.delete(id);
    }
}
