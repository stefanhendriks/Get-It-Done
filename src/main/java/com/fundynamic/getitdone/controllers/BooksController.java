package com.fundynamic.getitdone.controllers;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fundynamic.getitdone.services.BookService;

@Controller
public class BooksController {

    protected final Logger logger = LoggerFactory.getLogger(BooksController.class);
    
    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/hello.htm")
    public String handleRequest(ModelMap modelMap)
            throws ServletException, IOException {
    	modelMap.addAttribute("books", bookService.getAllBooks());
        return "hello";
    }

}