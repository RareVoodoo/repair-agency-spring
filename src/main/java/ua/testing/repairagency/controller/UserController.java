package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.service.UserService;
import ua.testing.repairagency.util.Constants;

import javax.validation.Valid;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    final UserService userService;
    final RepairRequestService repairRequestService;

    public UserController(RepairRequestService repairRequestService, UserService userService) {
        this.repairRequestService = repairRequestService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToRegisterPage() {
        return Constants.REGISTRATION_PAGE_PATH_REDIRECT;
    }

    @GetMapping("/user")
    public String initializeRepairRequest(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RepairRequestDto repairRequestDTO = new RepairRequestDto();
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestDTO);
        logger.info("Username = " + authentication.getName());

        model.addAttribute(Constants.REQUESTS_REP_ATTRIBUTE,
                repairRequestService.findExecutedRequestsByUsername(authentication.getName()));

        model.addAttribute(Constants.USERS_ATTRIBUTE, userService.findAll());
        logger.warn(String.valueOf(LocaleContextHolder.getLocale()));
        return Constants.USER_VIEW;
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(Constants.USER_ATTRIBUTE, userDto);
        return Constants.LOGIN_VIEW;
    }

    @GetMapping("/newRequest")
    public String redirectToRequestForm(Model model) {
        RepairRequestDto repairRequestDTO = new RepairRequestDto();
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestDTO);
        return Constants.REQUEST_REGISTRATION_VIEW;
    }


    @GetMapping("user/userComment/username={username}")
    public String redirectToUserCommentForm(@PathVariable(Constants.USERNAME_FIELD_NAME) String username,
                                            @RequestParam Long id,
                                            Model model) {
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequest);
        return Constants.USER_COMMENT_VIEW;
    }


    @PostMapping("/addComment/username={username}")
    public ModelAndView addUserComment(@PathVariable String username,
                                       @RequestParam Long id,
                                       Model model,
                                       @ModelAttribute(Constants.REQUESTS_REP_ATTRIBUTE) @Valid RepairRequestDto repairDto,
                                       BindingResult result) {

        if (!result.hasErrors()) {
            RepairRequest request = repairRequestService.findById(id);
            request.setUserComment(repairDto.getUserComment());
            repairRequestService.save(request);

            return new ModelAndView("redirect:/user",
                    Constants.REQUESTS_REP_ATTRIBUTE, repairRequestService.findAll());
        }

        return new ModelAndView(Constants.USER_COMMENT_VIEW,Constants.REQUEST_ATTRIBUTE,repairDto);
    }

}
