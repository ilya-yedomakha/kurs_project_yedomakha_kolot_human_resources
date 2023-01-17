package com.example.kurs_project_3.presentation.REST.security;

import com.example.kurs_project_3.buisnesslayer.domain.security.Role;
import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import com.example.kurs_project_3.buisnesslayer.service.security.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
public class UserRESTController {

    @Autowired
    UserServiceImpl userService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/user")
    public User saveUser(@RequestBody User user){
        User user1 = userService.saveFromAdmin(user);
        if (user1 == null) {
            String wrongField = "";
            if(user.getPassword() == null || user.getPassword().equals("")){
                wrongField = "Password";
            }
            if(user.getLastName() == null || user.getLastName().equals("")){
                wrongField = "Last Name";
            }
            if(user.getFirstName() == null || user.getFirstName().equals("")){
                wrongField = "First Name";
            }
            boolean b = false;
            for (User user2 : userService.findAll()){
                if (user2.getEmail().equals(user.getEmail())){
                    b = true;
                    break;
                }
            }

            if(user.getEmail() == null || user.getEmail().equals("") || b){
                wrongField = "Email";
            }
            System.out.println(wrongField);
            throw new NullPointerException(wrongField);
        }else {
            return user1;
        }
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/user/getAllUsers")
    public Set<User> getUsers(){
        return userService.findAll();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/user/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @DeleteMapping("/cab/delete/{id}")
    public void deleteUserByYourself(@PathVariable Long id){
        userService.deleteById(id);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/user/{id}/role/{roleId}")
    public User addRoleToUser(@PathVariable Long id,@PathVariable Long roleId){
        return userService.addRoleToUser(id, roleId);
    }

    @PutMapping("/admin/user/{id}/role/remove/{roleId}")
    public void removeRole(@PathVariable Long id,@PathVariable Long roleId){
        userService.removeRole(id,roleId);
    }

    @GetMapping("/admin/user/{id}/role/getAllRoles")
    public Collection<Role> showUsersRoles(@PathVariable Long id){
        return userService.getRolesById(id);
    }
    @GetMapping("/cab/getUserByEmail/{email}")
    public User getByEmail(@PathVariable String email){
        return userService.getByEmail(email);
    }
    @PutMapping("/cab/update")
    public User updateUserFromCab(@RequestBody User user){
        return userService.updateUser(user);
    }
}
