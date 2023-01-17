package com.example.kurs_project_3.buisnesslayer.service.security;

import com.example.kurs_project_3.buisnesslayer.domain.security.Role;
import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import com.example.kurs_project_3.persistence.security.RoleRepository;
import com.example.kurs_project_3.persistence.security.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    public final RoleRepository roleRepository;

    public final UserRepository userRepository;

    public final UserServiceImpl userService;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Role save(Role roleToSave){
        Role role = roleRepository.save(roleToSave);
        return role;
    }

    public void deleteById(Long id){
        for (User user: userService.findAll()){
            for (Role role : user.getRoles()){
                if (role.getId().equals(id)){
                    user.getRoles().remove(role);
                    userRepository.save(user);
                }
            }
        }
        roleRepository.deleteById(id);
    }

    public Role findById(Long id){
       return roleRepository.findRoleById(id);
    }

    public Set<Role> findAllRoles(){
        Set<Role> users = new HashSet<>();
        roleRepository.findAll().forEach(users::add);
        return users;
    }
}
