package com.khryniewicki.projectX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectXApplication.class, args);
       new Game().start();
//        ConcurrentLinkedDeque<HeroDTO> objects = new ConcurrentLinkedDeque<>();
//        HeroDTO fireman = new HeroDTO("Fireman", 0.2f, 0.2f);
//        HeroDTO Iceman = new HeroDTO("Iceman", 0.2f, 0.2f);
//        HeroDTO thunder = new HeroDTO("thunder", 0.2f, 0.2f);
//
//        objects.offerLast(fireman);
//        objects.offerLast(Iceman);
//        objects.offerLast(thunder);
//        System.out.println(objects.size());
//        objects.pop();
//        System.out.println(objects.toString());
//        objects.pop();
//        System.out.println(objects.toString());
//
//        objects.pop();
//        System.out.println(objects.toString());

    }
}
