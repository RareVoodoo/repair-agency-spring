package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    final UserService userService;

    final RepairRequestService repairRequestService;

    public UserController( RepairRequestService repairRequestService, UserService userService) {
        this.repairRequestService = repairRequestService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToRegisterPage(){
        return "redirect:/registration";
    }

    @GetMapping("/user")
    public String initializeRepairRequest(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RepairRequestDTO repairRequestDTO = new RepairRequestDTO();
        model.addAttribute("request",repairRequestDTO);
        model.addAttribute("requestRep",repairRequestService.findExecutedRequestsByUsername(authentication.getName()));
        model.addAttribute("users",userService.findAll());
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
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute("request", repairRequest);
        return "userComment";
    }


    @PostMapping("/addComment/id={id}")
    public String addUserComment(@PathVariable Long id,
                                 Model model,
                                 @ModelAttribute("requestRep") @Valid RepairRequestDTO repairDTO ){

        RepairRequest request = repairRequestService.findById(id);
        request.setUserComment(repairDTO.getUserComment());
        repairRequestService.save(request);
        model.addAttribute("requestRep", repairRequestService.findAll());
        return "redirect:/user";
    }

}
