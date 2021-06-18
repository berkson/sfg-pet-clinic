package com.ximenes.sfgpetclinic.repositories;

import com.ximenes.sfgpetclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by berkson
 * Date: 17/06/2021
 * Time: 23:13
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
