package ua.testing.repairagency.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.service.UserService;
import ua.testing.repairagency.util.Constants;

import javax.validation.Valid;


@Controller
public class ManagerController {


    final RepairRequestService repairRequestService;
    final UserService userService;


    public ManagerController(RepairRequestService repairRequestService, UserService userService) {
        this.repairRequestService = repairRequestService;
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String listManagerRepairRequests(@PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
                                            Model model) {
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestService.findAll(pageable));
        model.addAttribute(Constants.USERS_ATTRIBUTE, userService.findAll());
        return Constants.ADMIN_TABLES_VIEW;
    }


    @GetMapping("admin/accept/username={username}")
    public String redirectToAcceptForm(@RequestParam Long id, Model model) {
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequest);
        return Constants.ADMIN_ACCEPT_VIEW;
    }


    @PostMapping("/acceptRequest/username={username}")
    public ModelAndView acceptRequest(@PathVariable String username,
                                @RequestParam Long id,
                                Model model,
                                @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
                                @ModelAttribute(Constants.REQUEST_ATTRIBUTE) @Valid RepairRequestDto repairDTO,
                                BindingResult result) {

        if (!result.hasErrors()) {
            repairRequestService.acceptUserRequest(id, repairDTO);
            model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestService.findAll());
            return new ModelAndView("redirect:/admin",
                    Constants.REQUEST_ATTRIBUTE,
                    repairRequestService.findAll(pageable));

        }
        return new ModelAndView(Constants.ADMIN_ACCEPT_VIEW, Constants.REQUEST_ATTRIBUTE, repairDTO);
    }


    @GetMapping("admin/cancel/username={username}")
    public String redirectToCancelForm(@RequestParam Long id,
                                       Model model) {
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequest);
        return Constants.ADMIN_CANCEL_VIEW;
    }

    @PostMapping("/cancelRequest/username={username}")
    public ModelAndView cancelRequest(@PathVariable String username,
                                      @RequestParam Long id,
                                      Model model,
                                      @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
                                      @ModelAttribute(Constants.REQUEST_ATTRIBUTE) @Valid RepairRequestDto repairDTO,
                                      BindingResult result) {
        if (!result.hasErrors()) {
            repairRequestService.cancelUserRequest(id, repairDTO);
            return new ModelAndView("redirect:/admin",
                    Constants.REQUEST_ATTRIBUTE,
                    repairRequestService.findAll(pageable));
        }

        return new ModelAndView(Constants.ADMIN_CANCEL_VIEW, Constants.REQUEST_ATTRIBUTE, repairDTO);
    }


    @Transactional
    @PostMapping("/admin/delete/username={username}")
    public String delete(@RequestParam Long id,
                         Model model) {
        repairRequestService.deleteUserRequestById(id);
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestService.findAll());
        return Constants.ADMIN_PAGE_PATH_REDIRECT;
    }


}
