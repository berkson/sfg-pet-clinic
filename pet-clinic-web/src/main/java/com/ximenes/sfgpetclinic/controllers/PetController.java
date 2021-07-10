package com.ximenes.sfgpetclinic.controllers;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.models.Pet;
import com.ximenes.sfgpetclinic.models.PetType;
import com.ximenes.sfgpetclinic.services.OwnerService;
import com.ximenes.sfgpetclinic.services.PetService;
import com.ximenes.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Berkson Ximenes
 * Date: 08/07/2021
 * Time: 20:24
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final OwnerService ownerService;

    public PetController(PetTypeService petTypeService, PetService petService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    // this annotation add all petTypes to the model types in the view
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    // this annotation get the owner that owns the id and attach it to the view model
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().owner(owner).build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName()) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else {
            owner.addPet(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
//    If we do not create method owner.addPet() we must do this way:
//    @GetMapping("/pets/{id}/edit")
//    public String initUpdateForm(@PathVariable Long ownerId, @PathVariable Long id, Model model) {
//        Owner owner = ownerService.findById(ownerId);
//        Pet pet = owner.getPets().stream().filter(pet1 -> pet1.getId().equals(id)).findFirst().orElse(null);
//        pet.setOwner(owner);
//        model.addAttribute("owner", owner);
//        model.addAttribute("pet", pet);
//        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//    }
//
//    @PostMapping("/pets/{id}/edit")
//    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner,
//                                    Model model) {
//        if (result.hasErrors()) {
//            pet.setOwner(owner);
//            model.addAttribute("pet", pet);
//            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
//        } else {
//            owner.getPets().removeIf(pet1 -> pet1.getId().equals(pet.getId()));
//            owner.getPets().add(pet);
//            pet.setOwner(owner);
//            ownerService.save(owner);
//            return "redirect:/owners/" + owner.getId();
//        }

    }
}
