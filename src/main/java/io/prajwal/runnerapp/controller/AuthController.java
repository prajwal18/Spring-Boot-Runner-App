package io.prajwal.runnerapp.controller;

import io.prajwal.runnerapp.dto.RegistrationDTO;
import io.prajwal.runnerapp.model.Role;
import io.prajwal.runnerapp.model.UserEntity;
import io.prajwal.runnerapp.repository.RoleRepository;
import io.prajwal.runnerapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.Encoder;

@Controller
public class AuthController {

    @Autowired
    RoleRepository rr;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute(value = "user") RegistrationDTO user, Model model, BindingResult result){
        UserEntity existingUserUN = userService.findByUsername(user.getUsername());
        if(existingUserUN != null && existingUserUN.getUsername() != null && !existingUserUN.getUsername().isEmpty()){
            result.rejectValue("username", "error.username", "User with this username/email already exists. Use another one");
        }
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            result.rejectValue("email", "error.email", "User with this username/email already exists. Use another one");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);


        return "redirect:/clubs?success";
    }

}