package com.clone.deed1515.boot_with_gradle_project.config.auth.dto;

import com.clone.deed1515.boot_with_gradle_project.domain.user.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
