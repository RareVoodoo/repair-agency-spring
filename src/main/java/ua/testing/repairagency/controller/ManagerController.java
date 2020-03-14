package ua.testing.repairagency.controller;

import antlr.ASTNULLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.repository.AuthorityRepository;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.repository.UserRepository;
import ua.testing.repairagency.service.RepairRequestService;

@Controller
public class ManagerController {
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



    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        repairRequestRepository.deleteById(id);
        model.addAttribute("request", repairRequestRepository.findAll());
        return "redirect:/admin";
    }

    @PostMapping("/admin/acceptRequest/{id}")
    public String acceptRequest(@PathVariable Long id, Model model){
        RepairRequest request = repairRequestRepository.findById(id).get();

        if(!request.isAccepted()){
            request.setAccepted(true);
        }
        else{
            request.setAccepted(false);
        }
        repairRequestRepository.save(request);
        model.addAttribute("request", repairRequestRepository.findAll());

        return "redirect:/admin";

    }




}
