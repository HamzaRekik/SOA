package com.springboot.reglement.Repositories;

import com.springboot.reglement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findById(long id);
}
