package com.clone.deed1515.boot_with_gradle_project.domain.posts;

import lombok.Builder;
import com.clone.deed1515.boot_with_gradle_project.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity                 // table 과 링크될 클래스
                        // default naming : camelCase -> under_score_naming
@NoArgsConstructor      // ==public Posts(){}
@Getter                 // setter 까지 동시 생성 시 코드상에서 해당 코드동작 규약 힘듬
public class Posts extends BaseTimeEntity {
    @Id                 // is PK!
    @GeneratedValue     // 생성규칙 GenerationType -> auto_increment
            (strategy =  GenerationType.IDENTITY)
    private long id;

    @Column             // 테이블 컬럼임을 나타냄, Default Varchar(255)
            (length = 500, nullable = false)
    private String title;

    @Column
            (columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;  //column 생략 시 default로 지정된 값으로 저장

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title,String content){
        this.title= title;
        this.content= content;

    }
}
