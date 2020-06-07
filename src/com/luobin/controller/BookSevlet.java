package com.luobin.controller;

import com.luobin.entity.Book;
import com.luobin.service.BookService;
import com.luobin.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookSevlet extends HttpServlet {

    /**
     * 没有添加bookservelt对用户身份信息的验证
     */
    private  BookService bookService =new BookServiceImpl();
    private final  int LIMIT=6;
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        int pageNum =Integer.parseInt(page);
        List<Book> list=bookService.findAll(pageNum);
        req.setAttribute("list",list);
        req.setAttribute("dataPrePage",LIMIT);
        req.setAttribute("currentPage",pageNum);
        req.setAttribute("pages",bookService.getPages());
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
