//package kek.project.antia.first.test.controller;
//
//import kek.project.antia.first.test.model.Greeting;
//import kek.project.antia.first.test.model.HelloMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.util.HtmlUtils;
//
//@Controller
//public class GreetingController {
//
//    @MessageMapping("/hello")
//    @SendTo("/topictest")
//    public Greeting greeting(HelloMessage message) throws InterruptedException {
////        Thread.sleep(1000);
//        System.out.println(message.getName());
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//
//}
