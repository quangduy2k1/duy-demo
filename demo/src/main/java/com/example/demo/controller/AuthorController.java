package com.example.demo.controller;

import com.example.demo.exception.ModelException;
import com.example.demo.models.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    public AuthorService authorService;

    @RequestMapping("/author")
    public String show(Model model) {
        List<Author> list = authorService.listAll();
        model.addAttribute("list", list);
        return "Author";
    }

    @RequestMapping("/author/new")
    public String newAuthor(Author author, Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle","Add new Author");
        return "newAuthor";
    }

    @PostMapping("/author/save")
    public String save(Author author) {
        authorService.save(author);
        return "redirect:/author";
    }

    @GetMapping("/author/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id,Model model,RedirectAttributes ra ){
        try {
            Author author=authorService.update(id);
            model.addAttribute("author",author);
            model.addAttribute("pageTitle","Edit Author(ID :" +id+")");
            return "newAuthor";
        }catch (ModelException e){
            return "redirect:/author";
        }
    }

    @RequestMapping("/author/delete/{id}")
    public String delete(@PathVariable("id") Integer integer, RedirectAttributes ra) {
        authorService.delete(integer);
        ra.addFlashAttribute("r", " The Author Id " + integer + " has been delete");
        return "redirect:/author";
    }
}
