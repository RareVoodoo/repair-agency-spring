package ua.testing.repairagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;

@Controller
public class MasterController {


    final RepairRequestService repairRequestService;

    public MasterController( RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @GetMapping("/master")
    public String listMasterRepairRequests(Model model) {
        model.addAttribute("request", repairRequestService.findAcceptedRequests());
        return "master/master";
    }

    @GetMapping("/masterArchive")
    public String listMasterArchive(Model model){
        model.addAttribute("request", repairRequestService.findAll());
        return "master/masterArchive";
    }


        @PostMapping("/master/performRequest/{id}")
    public String performRequest(@PathVariable Long id, Model model) {
        RepairRequest request = repairRequestService.findById(id);

        if (!request.isPerformed()) {
            request.setPerformed(true);
        } else {
            request.setPerformed(false);
        }
        repairRequestService.save(request);
        model.addAttribute("request");

        return "redirect:/master";
    }

}
