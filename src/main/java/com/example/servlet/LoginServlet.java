package com.example.servlet;

import com.example.controller.AuthenticationController;
import com.example.exception.KeyNotMatchException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name="LoginServlet", value="/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", request.getParameter("email"));
        params.put("password", request.getParameter("password"));
        params.put("type", request.getParameter("type"));
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            int result = new AuthenticationController().login(params);
            if( result > 0 ){
                writer.println(String.format("<h2>%s id %d Login successfully.</h2>",request.getParameter("type"), result));
            }
            else if( result == AuthenticationController.LOGIN_FAIL_NO_USER_FOUND) writer.println("<h2>Register first</h2>");
            else writer.println("<h2>Login Failed</h2>");
        } catch (KeyNotMatchException e) {
            e.printStackTrace();
        }
    }
}
