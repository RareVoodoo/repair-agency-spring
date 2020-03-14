package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.repository.RepairRequestRepository;

@Controller
public class MasterController {

    @Autowired
    RepairRequestRepository repairRequestRepository;

    @GetMapping("/master")
    public String listMasterRepairRequests(Model model) {
        model.addAttribute("request", repairRequestRepository.findAll());
        return "master";
    }

    @PostMapping("/master/performRequest/{id}")
    public String performRequest(@PathVariable Long id, Model model) {
        RepairRequest request = repairRequestRepository.findById(id).get();

        if (!request.isPerformed()) {
            request.setPerformed(true);
        } else {
            request.setPerformed(false);
        }
        repairRequestRepository.save(request);
        model.addAttribute("request");

        return "redirect:/master";
    }

}
