package com.example.four_aces.controller.commands;

import com.example.servlet.commands.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    @Override
    public void processGet() throws ServletException, IOException {
        forward("/unknown.jsp");
    }

    @Override
    public void processPost() throws ServletException, IOException {

    }
}