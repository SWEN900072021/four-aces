package com.example.util;

public class HTMLFormatter {

    public static String tag(String tag, String content){
        return String.format("<%s>%s</%s>", tag, content, tag);
    }
}
