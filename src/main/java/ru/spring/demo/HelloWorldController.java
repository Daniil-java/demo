package ru.spring.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping
    public List<String> getHelloWorld() {
        return List.of("Hello", "World");
    }

    @GetMapping("/api")
    @ResponseBody
    public String getId(@RequestParam String id) {
        return "ID: " + id;
    }
}
