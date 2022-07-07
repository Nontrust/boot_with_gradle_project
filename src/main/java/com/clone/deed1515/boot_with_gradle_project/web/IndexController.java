package com.clone.deed1515.boot_with_gradle_project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
