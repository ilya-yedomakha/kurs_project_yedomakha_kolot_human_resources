package com.example.kurs_project_3.presentation.REST.security;

import com.example.kurs_project_3.buisnesslayer.domain.security.Role;
import com.example.kurs_project_3.buisnesslayer.domain.security.User;
import com.example.kurs_project_3.buisnesslayer.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoleRESTController {

    @Autowired
    RoleService roleService;

    @PostMapping("/admin/rights")
    public Role saveUser(@RequestBody Role user){
        return roleService.save(user);
    }

    @GetMapping("/admin/rights/getAllRights")
    public Set<Role> getUsers(){
        return roleService.findAllRoles();
    }

    @DeleteMapping("/admin/rights/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        roleService.deleteById(id);
    }

    @PutMapping("/admin/rights/update")
    public Role updateRole(@RequestBody Role role){
        return roleService.save(role);
    }
}
