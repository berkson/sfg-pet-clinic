package com.ximenes.sfgpetclinic.services;

import com.ximenes.sfgpetclinic.model.Vet;

import java.util.Set;

/**
 * Created by berkson
 * Date: 31/05/2021
 * Time: 20:59
 */
public interface VetService {
    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
