package com.ximenes.sfgpetclinic.repositories;

import com.ximenes.sfgpetclinic.models.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by berkson
 * Date: 17/06/2021
 * Time: 23:00
 */
@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    // my approach - see OwnerController method call
    @Query(value = "SELECT * FROM owners WHERE last_name ILIKE %?1%", nativeQuery = true)
    List<Owner> findAllByLastNameLike(String lastName);
}
