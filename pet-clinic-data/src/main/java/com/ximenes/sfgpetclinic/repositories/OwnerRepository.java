package com.ximenes.sfgpetclinic.repositories;

import com.ximenes.sfgpetclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by berkson
 * Date: 17/06/2021
 * Time: 23:00
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
