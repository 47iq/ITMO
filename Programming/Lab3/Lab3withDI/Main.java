package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {
    public static ArrayList<String> out = new ArrayList<>();

    private static void print() {
        for(String string: out) {
            System.out.print(string);
        }
    }

    /*
    Скуперфильд, однако, продолжал спать.
    Проводник забыл его разбудить и вспомнил об этом, лишь когда поезд уже отошел от станции.
    Чтоб избежать неприятных объяснений, он решил пока не будить Скуперфильда,
    а принялся тормошить его, как только поезд остановился на следующей станции,
    которая имела какое-то странное название -- "Паноптикум".
    */

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        Route route = ctx.getBean(Route.class);
        route.runFullRoute();
        print();
    }
}
