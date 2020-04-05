package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;

import javax.validation.Valid;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    private final RepairRequestRepository repairRequestRepository;

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public UserController(RepairRequestRepository repairRequestRepository, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.repairRequestRepository = repairRequestRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

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

        logger.warn(String.valueOf(LocaleContextHolder.getLocale()));
        return "user/index";
    }

    @GetMapping("/login")
    public String loginUser(Model model){
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/newRequest")
    public String redirectToRequestForm(Model model){
        RepairRequestDTO repairRequestDTO = new RepairRequestDTO();
        model.addAttribute("request",repairRequestDTO);
        return "user/requestRegistration";
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
