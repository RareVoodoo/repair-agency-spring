package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.UserRepository;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public String initializeRepairRequest(Model model){
        RepairRequestDTO repairRequestDTO = new RepairRequestDTO();
        model.addAttribute("request", repairRequestDTO);
        return "index";
    }

}
