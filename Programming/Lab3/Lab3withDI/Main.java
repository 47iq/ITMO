package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /*
    Скуперфильд, однако, продолжал спать.
    Проводник забыл его разбудить и вспомнил об этом, лишь когда поезд уже отошел от станции.
    Чтоб избежать неприятных объяснений, он решил пока не будить Скуперфильда,
    а принялся тормошить его, как только поезд остановился на следующей станции,
    которая имела какое-то странное название -- "Паноптикум".
    */
    public static void main(String[] args) {
        //Conductor conductor = new Conductor("Conductor", 1, 1);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        Route route = ctx.getBean(Route.class);
        route.runFullRoute();
    }
}
