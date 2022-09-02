package com.clone.deed1515.boot_with_gradle_project.config.auth;

import com.clone.deed1515.boot_with_gradle_project.config.auth.dto.OAuthAttributes;
import com.clone.deed1515.boot_with_gradle_project.config.auth.dto.SessionUser;
import com.clone.deed1515.boot_with_gradle_project.domain.user.User;
import com.clone.deed1515.boot_with_gradle_project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행중인 서비스를 구분하는 코드(return google?)
        String registrationId = userRequest.getClientRegistration()
                                            .getRegistrationId();
        // 로그인 키 필드값(default = sub)
        String userNameAttributeName  = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                                            .getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);


        //세션 사용자 정보  DTO
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User
                (Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                        , attributes.getAttributes()
                        , attributes.getNameAttributeKey());

    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity-> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
