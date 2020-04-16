package ua.testing.repairagency.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.service.UserService;

import javax.validation.Valid;


@Controller
public class ManagerController {


    private static final int DEFAULT_PAGE_SIZE = 3;

    final RepairRequestService repairRequestService;
    final UserService userService;



    public ManagerController( RepairRequestService repairRequestService, UserService userService) {
        this.repairRequestService = repairRequestService;
        this.userService = userService;
    }



    @GetMapping("/admin")
    public String listManagerRepairRequests(@PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
                                            Model model) {
        model.addAttribute("request", repairRequestService.findAll(pageable));
        model.addAttribute("users", userService.findAll());
        return "admin/adminTables";
    }


    @GetMapping("admin/accept/{id}")
    public String redirectToAcceptForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute("request", repairRequest);
        return "admin/adminAccept";
    }

    @GetMapping("admin/cancel/{id}")
    public String redirectToCancelForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestService.findById(id);
        model.addAttribute("request", repairRequest);
        return "admin/adminCancel";
    }


    @PostMapping("/acceptRequest/id={id}")
    public String acceptRequest(@PathVariable Long id, Model model, @ModelAttribute("request") @Valid RepairRequestDTO repairDTO) {
        repairRequestService.acceptUserRequestById(id,repairDTO);
        model.addAttribute("request", repairRequestService.findAll());
        return "redirect:/admin";

    }


    @PostMapping("/cancelRequest/id={id}")
    public String cancelRequest(@PathVariable Long id, Model model, @ModelAttribute("request") @Valid RepairRequestDTO repairDTO) {
        repairRequestService.cancelUserRequest(id, repairDTO);
        model.addAttribute("request", repairRequestService.findAll());
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        repairRequestService.deleteUserRequestById(id);
        model.addAttribute("request", repairRequestService.findAll());
        return "redirect:/admin";
    }


}
