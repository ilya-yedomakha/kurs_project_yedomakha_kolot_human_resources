package com.example.kurs_project_3.buisnesslayer.service.security;

import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import com.example.kurs_project_3.presentation.view.security.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
