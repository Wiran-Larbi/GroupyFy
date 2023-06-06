package com.groupyfy.groupyfy.controller;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;


    @GetMapping()
    public String groups(Model model){
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups",groups);
        model.addAttribute("groups_count",groups.size());
        return "groups-ui";
    }


    @GetMapping("/sort/date_creation")
    public String sortDateCreation(Model model){
        List<Group> groups = groupService.getAllGroupssSortByCreationDate();
        model.addAttribute("groups",groups);
        model.addAttribute("groups_count",groups.size());
        return "groups-ui";
    }

    @GetMapping("/sort/nom")
    public String sortNom(Model model){
        List<Group> groups = groupService.getAllGroupsSortByNomAsc();
        model.addAttribute("groups",groups);
        model.addAttribute("groups_count",groups.size());
        return "groups-ui";
    }

    @GetMapping("/search/nom")
    public String searchPhonetique(Model model,@RequestParam("nom") String nom) {
        if(nom.equals("all")) {
            List<Group> groups = groupService.getAllGroups();
            model.addAttribute("groups",groups);
            model.addAttribute("groups_count",groups.size());
            return "groups-ui";
        }else{
            List<Group> groups = groupService.getGroupByPhonetique(nom);
            model.addAttribute("groups",groups);
            model.addAttribute("groups_count",groups.size());

            return "groups-ui";
        }

    }


    @GetMapping("/delete")
    public String deleteGroup(Model model,@RequestParam(name="Id") Long id){
        // * Deleting the Contact with the specified id

        groupService.deleteGroup(id);
        return "redirect:/groups?message=deleted_successfully";

    }
}
