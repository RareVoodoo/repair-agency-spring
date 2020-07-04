package ua.testing.repairagency.controller;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.service.UserService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.validator.UserRegistrationValidator;

import javax.validation.Valid;


@Controller
public class RegistrationController {

     Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;
    private final UserRegistrationValidator userRegistrationValidator;

    public RegistrationController(UserService userService, UserRegistrationValidator userRegistrationValidator) {
        this.userService = userService;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(Constants.USER_ATTRIBUTE, userDto);
        return Constants.REGISTRATION_VIEW;
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute(Constants.USER_ATTRIBUTE) @Valid UserDto accountDto,
            BindingResult result) {

        userRegistrationValidator.validate(accountDto,result);

        if (!result.hasErrors()) {
            userService.registerNewUserAccount(accountDto);
        }else{
            logger.error(String.valueOf(result.getAllErrors()));
            return Constants.REGISTRATION_VIEW;
        }

        return Constants.LOGIN_PAGE_PATH_REDIRECT;
    }

}
