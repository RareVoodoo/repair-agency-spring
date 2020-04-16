package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;

import javax.validation.Valid;


@Controller
public class RepairRequestController {
    Logger logger = LoggerFactory.getLogger(RepairRequestController.class);

    final RepairRequestService repairRequestService;


    public RepairRequestController(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }


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


    @PostMapping("/saveRequest")
    public String saveRequest(RepairRequest repairRequest){
        repairRequestService.save(repairRequest);
        return "redirect:/admin";
    }

}
