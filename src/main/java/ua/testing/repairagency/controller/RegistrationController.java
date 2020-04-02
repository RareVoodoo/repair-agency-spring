package ua.testing.repairagency.controller;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.service.UserService;
import ua.testing.repairagency.validator.UserValidator;

import javax.validation.Valid;



@Controller
public class RegistrationController {

     Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO accountDto,
            BindingResult result, Errors errors) throws Exception {

        userValidator.validate(accountDto,result);

        User registered = new User();
        if (!result.hasErrors()) {
            registered = userService.registerNewUserAccount(accountDto);
        }else{
            logger.error(String.valueOf(result.getAllErrors()));
            return "registration";
        }

        return "redirect:/login";
    }

}
