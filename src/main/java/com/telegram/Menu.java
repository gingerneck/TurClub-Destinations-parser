package com.telegram;

public class Menu {



    public static String getMenu(){
        StringBuilder menu = new StringBuilder();
        menu.append("1. ")
                .append("Пик")
                .append("2 .");
        return menu.toString();
    }
}
