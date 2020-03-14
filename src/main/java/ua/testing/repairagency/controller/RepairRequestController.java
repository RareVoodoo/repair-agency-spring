package ua.testing.repairagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.entity.User;
import ua.testing.repairagency.service.RepairRequestService;

import javax.validation.Valid;


@Controller
public class RepairRequestController {


    @Autowired
    RepairRequestService repairRequestService;

    @PostMapping("/newRequest")
    public ModelAndView createRepairRequest(@ModelAttribute("request") @Valid RepairRequestDTO repairDTO,
                                    BindingResult result) {

        RepairRequest repairRequest = new RepairRequest();
        if (!result.hasErrors()) {
           repairRequest =  repairRequestService.createNewRepairRequest(repairDTO);
        }else{
            System.out.println(result.getAllErrors());
        }

            return new ModelAndView("index", "request", repairDTO);
    }


}
