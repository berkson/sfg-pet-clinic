package com.ximenes.sfgpetclinic.services;

import com.ximenes.sfgpetclinic.model.Owner;

import java.util.Set;

/**
 * Created by Berkson Ximenes
 * Date: 31/05/2021
 * Time: 20:54
 */
public interface OwnerService {
    Owner findBylastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
