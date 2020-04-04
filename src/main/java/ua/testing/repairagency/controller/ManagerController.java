package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private static final int DEFAULT_PAGE_SIZE = 3;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RepairRequestService repairRequestService;

    @Autowired
    RepairRequestRepository repairRequestRepository;


    @Autowired
    private AuthorityRepository authorityRepository;


    @GetMapping("/admin")
    public String listManagerRepairRequests(Model model, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("request", repairRequestRepository.findAll(PageRequest.of(page,DEFAULT_PAGE_SIZE)));
        model.addAttribute("currentPage",page);
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("authorities", authorityRepository.findAll());
        return "admin/adminTables";
    }

    @GetMapping("admin/accept/{id}")
    public String redirectToAcceptForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestRepository.findById(id).get();
        model.addAttribute("request", repairRequest);
        return "admin/adminAccept";
    }

    @GetMapping("admin/cancel/{id}")
    public String redirectToCancelForm(@PathVariable("id") long id, Model model) {
        RepairRequest repairRequest = repairRequestRepository.findById(id).get();
        model.addAttribute("request", repairRequest);
        return "admin/adminCancel";
    }




    @PostMapping("/acceptRequest/id={id}")
    public String acceptRequest(@PathVariable Long id,Model model,@ModelAttribute("request") @Valid RepairRequestDTO repairDTO){
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(true);
        repairRequestService.setRepairPriceWithExchangeRate(repairDTO,request);
        repairRequestRepository.save(request);
        model.addAttribute("request", repairRequestRepository.findAll());
        return "redirect:/admin";

    }


    @PostMapping("/cancelRequest/id={id}")
    public String cancelRequest(@PathVariable Long id,Model model,@ModelAttribute("request") @Valid RepairRequestDTO repairDTO){
        RepairRequest request = repairRequestRepository.findById(id).get();

        request.setAccepted(false);
        request.setPerformed(false);
        request.setRepairPriceUah(RepairRequestService.REPAIR_PRICE_DEFAULT_VALUE);
        request.setRepairPriceUsd(RepairRequestService.REPAIR_PRICE_DEFAULT_VALUE);
        request.setCancellationReason(repairDTO.getCancellationReason());
        logger.warn("Cancel message: " + repairDTO.getCancellationReason());
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
