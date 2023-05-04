package com.bookofviledarkness.controllers;

import com.bookofviledarkness.models.SiteUser;
import com.bookofviledarkness.repositories.PostRepository;
import com.bookofviledarkness.repositories.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

public class AuthController {
    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String splash() {
        return "splash";
    }

    @PostMapping("/signup")
    public RedirectView signUp(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
        SiteUser siteUser = new SiteUser(username, hashedPassword);

        siteUserRepository.save(siteUser);
        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView loginToBookOfVileDarkness(HttpServletRequest request, String username, String password) {
        SiteUser siteUser = siteUserRepository.getSiteUserByUsername(username);

        if (siteUser == null) {
            return new RedirectView("/?message=Bad login");
        } else if (BCrypt.checkpw(password, siteUser.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return new RedirectView("/forbidden-secrets/" + siteUser.getUsername());
        } else {
            return new RedirectView("/?message=Bad login");
        }
    }

}
