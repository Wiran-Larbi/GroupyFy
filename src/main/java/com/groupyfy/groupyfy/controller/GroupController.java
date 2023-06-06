package com.groupyfy.groupyfy.controller;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.service.ContactService;
import com.groupyfy.groupyfy.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;
    private ContactService contactService;


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

    @GetMapping("/add")
    public String addGroup(Model model){
            List<Contact> contact_no_group = contactService.getContactsWithoutGroup();
            model.addAttribute("group",new Group());
            model.addAttribute("contacts",contact_no_group);
        System.out.println(contact_no_group);

            return "add-group";
    }

    @PostMapping("/add")
    public String saveGroup(Model model, @ModelAttribute("group") Group group,@RequestParam(value = "contact_ids", required = false) List<Long> contactIds){


        // ? Adding a new Row in the group assign table

        if(contactIds != null)
            groupService.addGroupAssign(group.getNom(),contactIds);
        else if(contactIds == null) {
            groupService.addGroup(group.getNom());
        }



        //contactService.saveContact(contact);
        return "redirect:/groups?message=added-successfully";
    }


    @GetMapping("/delete")
    public String deleteGroup(Model model,@RequestParam(name="Id") Long id){
        // * Deleting the Contact with the specified id

        groupService.deleteGroup(id);
        return "redirect:/groups?message=deleted_successfully";

    }
}
