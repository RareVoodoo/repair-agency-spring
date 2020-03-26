package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping("/")
    public String goToStartPage(){
        return "redirect:/registration";
    }

    @GetMapping("/user")
    public String initializeRepairRequest(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RepairRequestDTO repairRequestDTO = new RepairRequestDTO();
        model.addAttribute("request",repairRequestDTO);
        model.addAttribute("requestRep",repairRequestRepository.
                findByUsernameEqualsAndAcceptedTrueAndPerformedTrue(authentication.getName()));
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("authorities", authorityRepository.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String loginUser(Model model){
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("user/userComment/{id}")
    public String redirectToUserCommentForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestRepository.findById(id).get();
        model.addAttribute("request", repairRequest);
        return "userComment";
    }


    @PostMapping("/addComment/id={id}")
    public String addUserComment(@PathVariable Long id,
                                 Model model,
                                 @ModelAttribute("requestRep") @Valid RepairRequestDTO repairDTO ){

        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setUserComment(repairDTO.getUserComment());
        repairRequestRepository.save(request);
        model.addAttribute("requestRep", repairRequestRepository.findAll());
        return "redirect:/user";
    }

}
