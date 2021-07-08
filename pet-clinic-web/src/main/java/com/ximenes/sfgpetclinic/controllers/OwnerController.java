package com.ximenes.sfgpetclinic.controllers;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.services.OwnerService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 22:44
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // important more grained control over a form post field/input
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        // without parameters we are asking for all owners
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        // teacher approach: List<Owner> results = ownerService.findByLastNameLike("%" + owner.getLastName() + "%");
        List<Owner> results = ownerService.findByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "notfound");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return String.format("redirect:/owners/%d", owner.getId());
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreation(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }

    }

    @GetMapping("/{id}/edit")
    public String initUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("{id}/edit")
    public String processUpdate(@Validated Owner owner, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(id);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }


}
