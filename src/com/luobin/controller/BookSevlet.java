package com.luobin.controller;

import com.luobin.entity.Book;
import com.luobin.entity.Reader;
import com.luobin.service.BookService;
import com.luobin.service.impl.BookServiceImpl;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session =req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if(reader==null){
            resp.sendRedirect("login.jsp");
            return ;
        }
        //流程控制
        String method=req.getParameter("method");
        if(method==null){
            method="findAll";
        }

        switch (method){
            case "findAll":
                String page = req.getParameter("page");
                int pageNum = Integer.parseInt(page);
                List<Book> list = bookService.findAll(pageNum);
                req.setAttribute("list", list);
                req.setAttribute("dataPrePage", LIMIT);
                req.setAttribute("currentPage", pageNum);
                req.setAttribute("pages", bookService.getPages());
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                break;
            case "addBorrow":
                Integer bookid = Integer.parseInt(req.getParameter("bookid"));
                bookService.addBorrow(bookid,reader.getId());
                break;
        }


    }
}
