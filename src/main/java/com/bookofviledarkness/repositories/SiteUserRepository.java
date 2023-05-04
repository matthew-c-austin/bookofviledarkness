package com.bookofviledarkness.repositories;

import com.bookofviledarkness.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    public SiteUser getSiteUserByUserName(String username);
}
