package com.ximenes.sfgpetclinic.repositories;

import com.ximenes.sfgpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by berkson
 * Date: 17/06/2021
 * Time: 23:12
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
