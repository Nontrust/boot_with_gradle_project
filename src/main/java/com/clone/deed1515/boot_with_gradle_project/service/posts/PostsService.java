package com.clone.deed1515.boot_with_gradle_project.service.posts;

import com.clone.deed1515.boot_with_gradle_project.domain.posts.Posts;
import com.clone.deed1515.boot_with_gradle_project.domain.posts.PostsRepository;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsListResponseDto;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsResponseDto;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsSaveRequestDto;
import com.clone.deed1515.boot_with_gradle_project.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostsService {
    private  final PostsRepository postsRepository;
    public long save(PostsSaveRequestDto requestDto) {
        log.debug("requestDto:::{}",requestDto);
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id :"+ id ));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){
        Posts entity  = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
