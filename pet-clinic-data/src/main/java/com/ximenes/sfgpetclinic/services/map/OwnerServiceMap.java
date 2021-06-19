package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.models.Pet;
import com.ximenes.sfgpetclinic.models.PetType;
import com.ximenes.sfgpetclinic.services.OwnerService;
import com.ximenes.sfgpetclinic.services.PetService;
import com.ximenes.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 21:02
 */
@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {

        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
//                      garantindo que tipo de pet vai ter um id
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
//                      garantindo que o pet vai ter um id
                        if (pet.getId() == null) {
                            Pet petSaved = petService.save(pet);
                            pet.setId(petSaved.getId());
                        }
                    } else {
                        throw new RuntimeException("Pet Type required");
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }

    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return (Owner) super.map.entrySet()
                .stream().filter(entry -> entry.getValue().getLastName().equals(lastName)).findFirst().get();
    }
}
