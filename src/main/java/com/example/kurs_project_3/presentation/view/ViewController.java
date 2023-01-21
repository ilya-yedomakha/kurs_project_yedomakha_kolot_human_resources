package com.example.kurs_project_3.presentation.view;

import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.buisnesslayer.service.DepartmentService;
import com.example.kurs_project_3.buisnesslayer.service.EmployeeService;
import com.example.kurs_project_3.buisnesslayer.service.PostService;
import com.example.kurs_project_3.buisnesslayer.service.SpecialityService;
import com.example.kurs_project_3.buisnesslayer.service.security.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.TreeSet;

@Controller
public class ViewController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final SpecialityService specialityService;
    private final PostService postService;
    private final RoleService roleService;

    public ViewController(DepartmentService departmentService, EmployeeService employeeService, SpecialityService specialityService, PostService postService, RoleService roleService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.specialityService = specialityService;
        this.postService = postService;
        this.roleService = roleService;
    }

    @RequestMapping("/department/show")
    public String showDep(){
        return "department";
    }

    @RequestMapping("/post/show")
    public String showPost(Model model){
        model.addAttribute("departments", departmentService.findAll());
        return "post";
    }

    @RequestMapping("/")
    public String showMain(Model model){
        TreeSet<Post> nicePosts = new TreeSet<>();
        for (Post post : postService.findAll()){
            boolean b = true;
            for (Post post1 : nicePosts){
                if (post.getName().equals(post1.getName())){
                    b = false;
                    break;
                }
            }

            if(b && (long) post.getEmployees().size() > 0){
                nicePosts.add(post);
            }
        }
        model.addAttribute("posts", nicePosts);
        return "index";
    }

    @RequestMapping("/employee/show")
    public String showEmployee(Model model){
        model.addAttribute("posts",postService.findAll());
        return "employee";
    }

    @RequestMapping("/speciality/show")
    public String showSpeciality(){
        return "speciality";
    }

    @RequestMapping("qualification/show")
    public String showQualification(Model model){
        model.addAttribute("employees",employeeService.findAll());
        return "qualification";
    }

    @RequestMapping("promotion/show")
    public String showPromotion(Model model){
        model.addAttribute("employees",employeeService.findAll());
        return "promotion";
    }

    @RequestMapping("employee_speciality/show")
    public String showEmplSpec(Model model){
        model.addAttribute("employees",employeeService.findAll());
        model.addAttribute("specialities",specialityService.findAll());
        return "emplspec";
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @RequestMapping("admin/user/show")
    public String showUsersForAdmin(){

        return "security/adminusers";
    }

    @RequestMapping("/admin/user/{id}/role/show")
    public String showRolesOfUser(Model model,@PathVariable Long id){
        model.addAttribute("user",id);
        model.addAttribute("roles",roleService.findAllRoles());
        return "security/adminuserroles";
    }

    @RequestMapping("/admin/rights/show")
    public String showRoles(){

        return "security/adminroles";
    }

    @RequestMapping("/admin/test/show")
    public String showUserTest(){

        return "security/adminusers";
    }
    @RequestMapping("/accessdenied")
    public String accessDenied(){
        return "security/accessdenied";
    }

    @RequestMapping("/cab")
    public String showProfile(){
        return "security/cab";
    }

}
