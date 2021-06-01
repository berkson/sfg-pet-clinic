package com.ximenes.sfgpetclinic.services;

import com.ximenes.sfgpetclinic.model.Pet;

import java.util.Set;

/**
 * Created by berkson
 * Date: 31/05/2021
 * Time: 20:58
 */
public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
