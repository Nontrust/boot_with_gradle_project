package com.clone.deed1515.boot_with_gradle_project.web;

import com.clone.deed1515.boot_with_gradle_project.service.posts.PostsService;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsResponseDto;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsSaveRequestDto;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PostsApiController {
    private final PostsService postsService;
    
    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        log.debug("id is :{}",id);
        return postsService.findById(id);
    }
}
