package com.example.kurs_project_3.buisnesslayer.service.security;


import com.example.kurs_project_3.buisnesslayer.domain.security.Role;
import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import com.example.kurs_project_3.persistence.security.RoleRepository;
import com.example.kurs_project_3.persistence.security.UserRepository;
import com.example.kurs_project_3.presentation.view.security.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleById(1L));
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), roles);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        User usr = userRepository.findUserById(id);
        usr.getRoles().clear();
        userRepository.save(usr);
        userRepository.deleteById(id);
    }

    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User addRoleToUser(Long usrID, Long roleId) {
        User user = userRepository.findUserById(usrID);
        User saveUser;
        if (roleRepository.findRoleById(roleId) != null) {
            user.getRoles().add(roleRepository.findRoleById(roleId));
        }
        saveUser = userRepository.save(user);
        return saveUser;
    }

    public User saveFromAdmin(User employeeToSave) {
        User employee;

        boolean b = false;
        for (User user2 : findAll()){
            if (user2.getEmail().equals(employeeToSave.getEmail())){
                b = true;
                break;
            }
        }

        if (employeeToSave.getPassword() == null || employeeToSave.getPassword().equals("") || employeeToSave.getEmail() == null || employeeToSave.getFirstName() == null
        || employeeToSave.getLastName() == null ||employeeToSave.getEmail().equals("") || employeeToSave.getFirstName().equals("")
                || employeeToSave.getLastName().equals("") || b) {
            return null;
        }
        employeeToSave.setPassword(passwordEncoder.encode(employeeToSave.getPassword()));
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleById(1L));
        employeeToSave.setRoles(roles);
        employee = userRepository.save(employeeToSave);

        return employee;
    }

    public void removeRole(Long id, Long roleId) {
        User usr = userRepository.findUserById(id);
        Collection<Role> roles = new ArrayList<>();
        for (Role role : usr.getRoles()) {
            if (!role.getId().equals(roleId)) {
                roles.add(role);
            }
        }
        usr.setRoles(roles);
        userRepository.save(usr);
    }

    public Collection<Role> getRolesById(Long id) {
        return userRepository.findUserById(id).getRoles();
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUser(User userFromClient){
        User foundUser = userRepository.findUserById(userFromClient.getId());

        foundUser.setPassword(passwordEncoder.encode(userFromClient.getPassword()));
        foundUser.setEmail(userFromClient.getEmail());
        foundUser.setFirstName(userFromClient.getFirstName());
        foundUser.setLastName(userFromClient.getLastName());
        Set<Role> roleSet = new HashSet<>();
        foundUser.getRoles().forEach(role -> {
            Role rol = roleRepository.findRoleById(role.getId());
            roleSet.add(rol);
        });

        foundUser.getRoles().clear();
        for (Role role : roleSet) {
            foundUser.getRoles().add(role);
        }

        User user = userRepository.save(foundUser);
        return user;
    }
}
