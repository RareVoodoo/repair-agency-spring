package ua.testing.repairagency.controller;

import antlr.ASTNULLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.service.RepairRequestService;

import javax.validation.Valid;


@Controller
public class ManagerController {

    Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RepairRequestService repairRequestService;

    @Autowired
    RepairRequestRepository repairRequestRepository;


    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping("/admin")
    public String listManagerRepairRequests(Model model) {
        model.addAttribute("request", repairRequestRepository.findAll());
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("authorities", authorityRepository.findAll());
        return "admin";
    }

    @GetMapping("admin/accept/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestRepository.findById(id).get();
        model.addAttribute("request", repairRequest);
        return "adminAccept";
    }




    @PostMapping("/acceptRequest/id={id}")
    public String acceptRequest(@PathVariable Long id,Model model,@ModelAttribute("request") @Valid RepairRequestDTO repairDTO){
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(true);
        logger.warn("id: " + id);
        logger.warn("price: " + repairDTO.getRepairPrice());
        request.setRepairPrice(repairDTO.getRepairPrice());
        repairRequestRepository.save(request);
        model.addAttribute("request", repairRequestRepository.findAll());
        return "redirect:/admin";

    }


    @PostMapping("/admin/cancelRequest/id={id}")
    public String cancelRequest(@PathVariable Long id, Model model){
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(false);
        repairRequestRepository.save(request);
        model.addAttribute("request", repairRequestRepository.findAll());
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        repairRequestRepository.deleteById(id);
        model.addAttribute("request", repairRequestRepository.findAll());
        return "redirect:/admin";
    }



}
