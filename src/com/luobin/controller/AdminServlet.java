package com.luobin.controller;

import com.luobin.entity.Admin;
import com.luobin.entity.Borrow;
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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService =new BookServiceImpl();
    private final int LIMIT =6;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method =req.getParameter("method");
        if(method==null){
            method="findAllBorrow";
        }
        int state=0;

        HttpSession session=req.getSession();

        switch (method){
            case "findAllBorrow" :
                state=0;
                String pageStr= req.getParameter("page");
                Integer page =Integer.parseInt(pageStr);
                List<Borrow> borrowList=bookService.findAllByState(state,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("currentPage",page);
                req.setAttribute("dataPrePage",LIMIT);
                req.setAttribute("pages",bookService.getBorrowPagesByState(state));
                req.getRequestDispatcher("/admin.jsp").forward(req,resp);
                break;
            case "getBorrowed":
                state=1;
                pageStr= req.getParameter("page");
                page =Integer.parseInt(pageStr);
                List<Borrow> borrowedList =bookService.findAllByState(state,page);
                req.setAttribute("list",borrowedList);
                req.setAttribute("currentPage",page);
                req.setAttribute("dataPrePage",LIMIT);
                req.setAttribute("pages",bookService.getBorrowPagesByState(state));
                req.getRequestDispatcher("/return.jsp").forward(req,resp);
                break;
            case "handle" :
                String stateStr=req.getParameter("state");
                state =Integer.parseInt(stateStr);
                String idStr =req.getParameter("id");
                Integer id= Integer.parseInt(idStr);
                Admin admin = (Admin) session.getAttribute("admin");
                Integer adminId =admin.getId();
                bookService.setBorrowById(id,state,adminId);
                if(state==3){
                    resp.sendRedirect("/admin?method=getBorrowed&page=1");
                }
                else if(state==1||state==2){
                    resp.sendRedirect("/admin?page=1");
                }

                break;

        }

    }
}
