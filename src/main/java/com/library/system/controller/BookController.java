package com.library.system.controller;

import com.library.system.model.Book;
import com.library.system.model.EBook;
import com.library.system.model.PrintedBook;
import com.library.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) throws IOException {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author,
                         @RequestParam String genre, @RequestParam String isbn,
                         @RequestParam String type, @RequestParam(required = false) String rackLocation,
                         @RequestParam(required = false) Integer pages,
                         @RequestParam(required = false) String downloadUrl,
                         @RequestParam(required = false) Double fileSize) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        Book book;
        if (type.equals("PRINTED")) {
            book = new PrintedBook(id, title, author, genre, isbn, true, rackLocation, pages);
        } else {
            book = new EBook(id, title, author, genre, isbn, true, downloadUrl, fileSize);
        }
        
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) throws IOException {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
