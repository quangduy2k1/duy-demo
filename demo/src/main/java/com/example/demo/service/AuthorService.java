package com.example.demo.service;

import com.example.demo.exception.ModelException;
import com.example.demo.models.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public List<Author> listAll() {
        return repository.findAll();
    }

    public void save(Author author) {
        repository.save(author);
    }

    public void delete(Integer integer) {
        repository.deleteById(integer);
    }

    public Author update(Integer id) throws ModelException {
        Optional<Author> result=repository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ModelException("could not find any Author with Id" +id);
    }
}
