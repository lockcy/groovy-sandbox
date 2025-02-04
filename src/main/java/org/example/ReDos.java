package org.example;

import java.util.regex.Pattern;

public class ReDos {
    public static void main(String[] args) {
        Pattern.compile("(.*a){10000}").
                matcher("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!").matches();
    }
}
