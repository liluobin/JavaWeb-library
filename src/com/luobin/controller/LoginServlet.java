package com.luobin.controller;

import com.luobin.entity.Admin;
import com.luobin.entity.Book;
import com.luobin.entity.Reader;
import com.luobin.service.BookService;
import com.luobin.service.LoginService;
import com.luobin.service.impl.BookServiceImpl;
import com.luobin.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService= new LoginServiceImpl();
    private BookService bookService =new BookServiceImpl();
    private final int LIMIT =6;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * 处理登录的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username =req.getParameter("username");
        String password=req.getParameter("password");
        String type = req.getParameter("type");
        Object object=loginService.login(username,password,type);
        if(object==null){
            resp.sendRedirect("/login.jsp");
        }
        else{
            System.out.println("yessssss");

            HttpSession session=req.getSession();
            switch (type){
                case "reader":
                    session.setAttribute("reader",(Reader)object);
                    resp.sendRedirect("/book?page=1");
                    break;

                case "admin":
                    session.setAttribute("admin",(Admin)object);
                    break;
            }
        }
        System.out.println(object);

    }
}
