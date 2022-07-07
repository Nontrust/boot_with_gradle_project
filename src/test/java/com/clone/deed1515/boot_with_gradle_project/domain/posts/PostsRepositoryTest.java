package com.clone.deed1515.boot_with_gradle_project.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "test_Mail@test.com";

        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given

        String title = "title";
        String content = "content";
        String author = "author";

        LocalDateTime now = LocalDateTime.now();

        postsRepository.save(Posts.builder().title(title).content(content).author(content).build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        LocalDateTime createdDate = posts.getCreatedDate();
        LocalDateTime modifiedDate = posts.getModifiedDate();

        System.out.println(">>>>>>>> crate Date =  / "+createdDate+"ModifiedDate = "+modifiedDate);

        assertThat(createdDate).isAfter(now);
        assertThat(modifiedDate).isAfter(now);
    }
}
