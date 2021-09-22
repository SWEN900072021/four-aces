package com.example.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    public void process() throws ServletException, IOException {
        forward("/unknown.jsp");
    }

    @Override
    public void processGet() throws ServletException, IOException {
        process();
    }

    @Override
    public void processPost() throws ServletException, IOException {
        process();
    }
}