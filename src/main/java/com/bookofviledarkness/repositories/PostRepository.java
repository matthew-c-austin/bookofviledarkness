package com.bookofviledarkness.repositories;

import com.bookofviledarkness.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
