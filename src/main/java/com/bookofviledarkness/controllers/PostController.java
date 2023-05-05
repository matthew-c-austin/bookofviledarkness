package com.bookofviledarkness.controllers;

import com.bookofviledarkness.models.Post;
import com.bookofviledarkness.models.SiteUser;
import com.bookofviledarkness.repositories.PostRepository;
import com.bookofviledarkness.repositories.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/forbidden-secrets/{username}")
    public String getForbiddenSecrets(HttpServletRequest request, Model m, @PathVariable String username) {
        try {
            HttpSession session = request.getSession();
            String sessionUsername = (String) session.getAttribute("username");
            if (sessionUsername == null || !sessionUsername.equals(username)) {
                return "redirect:/login";
            } else {
                List<Post> allPosts = postRepository.findAllByOrderByDateDesc();
                m.addAttribute("allPosts", allPosts);
                SiteUser siteUser = siteUserRepository.getSiteUserByUsername(username);
                m.addAttribute("siteUser", siteUser);
                return "forbidden-secrets";
            }
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/forbidden-secrets/{username}/add-post")
    public RedirectView addNewPost(@PathVariable String username, String postContent) {
        Date date = new Date();
        Post post = new Post(postContent, date);
        SiteUser siteUser = siteUserRepository.getSiteUserByUsername(username);
        siteUser.addPost(post);
        postRepository.save(post);
        siteUserRepository.save(siteUser);
        return new RedirectView("/forbidden-secrets/" + siteUser.getUsername());
    }
}
