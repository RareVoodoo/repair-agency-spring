package ua.testing.repairagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;

import javax.validation.Valid;


@Controller
public class RepairRequestController {
    Logger logger = LoggerFactory.getLogger(RepairRequestController.class);

    final RepairRequestService repairRequestService;


    public RepairRequestController(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }


    @PostMapping("/newRequest")
    public ModelAndView createRepairRequest(
            @ModelAttribute(Constants.REQUEST_ATTRIBUTE) @Valid RepairRequestDto repairDTO,
            BindingResult result) {
        if (!result.hasErrors()) {
            repairRequestService.createNewRepairRequest(repairDTO);
           return new ModelAndView(Constants.SUCCESSFUL_REQUEST_CREATION_VIEW, Constants.REQUEST_ATTRIBUTE, repairDTO);
        }else{
            logger.error(String.valueOf(result.getAllErrors()));
        }

        return new ModelAndView(Constants.REQUEST_REGISTRATION_VIEW,Constants.REQUEST_ATTRIBUTE,repairDTO);
    }


    @PostMapping("/saveRequest")
    public String saveRequest(RepairRequest repairRequest){
        repairRequestService.save(repairRequest);
        return Constants.ADMIN_PAGE_PATH_REDIRECT;
    }

}
