package com.clone.deed1515.boot_with_gradle_project.web;

import com.clone.deed1515.boot_with_gradle_project.config.auth.LoginUser;
import com.clone.deed1515.boot_with_gradle_project.config.auth.dto.SessionUser;
import com.clone.deed1515.boot_with_gradle_project.service.posts.PostsService;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
//        log.debug("user is {}",user2);
        model.addAttribute("posts", postsService.findAllDesc());
//        @LoginUser로 주입됨
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("loginUserName", user.getName());
        }

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

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts",dto);
        return "posts-update";
    }
}
