package ua.testing.repairagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;

@Controller
public class MasterController {


    final RepairRequestService repairRequestService;

    public MasterController( RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @GetMapping("/master")
    public String listMasterRepairRequests(Model model) {
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestService.findAcceptedRequests());
        return Constants.MASTER_VIEW;
    }

    @GetMapping("/masterArchive")
    public String listMasterArchive(Model model){
        model.addAttribute(Constants.REQUEST_ATTRIBUTE, repairRequestService.findAll());
        return Constants.MASTER_ARCHIVE_VIEW;
    }


    @PostMapping("/master/performRequest/username={username}")
    public String performRequest(@RequestParam Long id,
                                 Model model) {

        repairRequestService.performRequest(id);
        model.addAttribute(Constants.REQUEST_ATTRIBUTE);

        return Constants.MASTER_PAGE_PATH_REDIRECT;
    }

}
