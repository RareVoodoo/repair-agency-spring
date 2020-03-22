package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.service.RepairRequestService;

import javax.validation.Valid;


@Controller
public class RepairRequestController {
    Logger logger = LoggerFactory.getLogger(RepairRequestController.class);

    @Autowired
    RepairRequestService repairRequestService;

    @Autowired
    RepairRequestRepository repairRequestRepository;


    @PostMapping("/newRequest")
    public ModelAndView createRepairRequest(@ModelAttribute("request") @Valid RepairRequestDTO repairDTO, BindingResult result) {
        RepairRequest repairRequest = new RepairRequest();
        if (!result.hasErrors()) {
           repairRequest =  repairRequestService.createNewRepairRequest(repairDTO);
        }else{
            logger.error(String.valueOf(result.getAllErrors()));
        }
            return new ModelAndView("successfulRequestCreation", "request", repairDTO);
    }

    @GetMapping("/findOne")
    @ResponseBody
    public RepairRequest findOne(long id) {
        return repairRequestRepository.findById(id).get();
    }

    @PostMapping("/saveRequest")
    public String saveRequest(RepairRequest repairRequest){
        repairRequestRepository.save(repairRequest);
        return "redirect:/admin";
    }

}
