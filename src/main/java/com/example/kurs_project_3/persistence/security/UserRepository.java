package com.example.kurs_project_3.persistence.security;

import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findUserById(Long id);
}
