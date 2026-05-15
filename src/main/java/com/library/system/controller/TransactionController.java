package com.library.system.controller;

import com.library.system.model.Book;
import com.library.system.model.RegularUser;
import com.library.system.model.Transaction;
import com.library.system.model.User;
import com.library.system.service.BookService;
import com.library.system.service.TransactionService;
import com.library.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String listTransactions(Model model) throws IOException {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transactions/list";
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) throws IOException {
        List<Book> availableBooks = bookService.getAllBooks().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
        List<User> regularUsers = userService.getAllUsers().stream()
                .filter(u -> u.getUserType().equals("REGULAR"))
                .collect(Collectors.toList());
        
        model.addAttribute("books", availableBooks);
        model.addAttribute("users", regularUsers);
        return "transactions/borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam String userId, @RequestParam String bookId,
                             @RequestParam String dueDate) throws IOException {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        Transaction t = new Transaction(id, userId, bookId, LocalDate.now(), LocalDate.parse(dueDate));
        
        transactionService.addTransaction(t);
        
        // Update book availability
        bookService.getBookById(bookId).ifPresent(b -> {
            b.setAvailable(false);
            try { bookService.updateBook(b); } catch (IOException e) {}
        });
        
        return "redirect:/transactions";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable String id) throws IOException {
        transactionService.getAllTransactions().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> {
                    t.setReturnDate(LocalDate.now());
                    t.setCompleted(true);
                    
                    // Calculate fine polymorphism
                    try {
                        User user = userService.getAllUsers().stream().filter(u -> u.getId().equals(t.getUserId())).findFirst().orElse(null);
                        String membership = (user instanceof RegularUser) ? ((RegularUser)user).getMembershipType() : "Basic";
                        t.calculateFine(membership);
                        
                        transactionService.updateTransaction(t);
                        
                        // Make book available again
                        bookService.getBookById(t.getBookId()).ifPresent(b -> {
                            b.setAvailable(true);
                            try { bookService.updateBook(b); } catch (IOException e) {}
                        });
                    } catch (IOException e) {}
                });
        
        return "redirect:/transactions";
    }
}
