package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.testing.repairagency.entity.Authority;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.UserRepository;


@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value ="/admin")
    public String listUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("authorities", authorityRepository.findAll());
        return "admin";
    }

}
