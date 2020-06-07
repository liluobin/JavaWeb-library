package com.luobin.service.impl;

import com.luobin.entity.Admin;
import com.luobin.entity.Reader;
import com.luobin.repository.AdminRepository;
import com.luobin.repository.ReaderRepository;
import com.luobin.repository.impl.AdminRepositoryImpl;
import com.luobin.repository.impl.ReaderRepositoryImpl;
import com.luobin.service.LoginService;


public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository=new ReaderRepositoryImpl();
    private AdminRepository adminRepository =new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object=null;
        switch (type){
            case "reader":
                //跳转到读者的首页
                object= readerRepository.login(username,password);

                break;

            case "admin":
                //跳转到管理员的首页
                object=adminRepository.login(username,password);
                break;
        }
        return object;
    }
}
