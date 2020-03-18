package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepairRequestRepository repairRequestRepository;


    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public String initializeRepairRequest(Model model){
        model.addAttribute("request", repairRequestRepository.findAll());
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("authorities", authorityRepository.findAll());
        return "index";
    }

}
