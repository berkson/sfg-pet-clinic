package com.ximenes.sfgpetclinic.repositories;

import com.ximenes.sfgpetclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by berkson
 * Date: 17/06/2021
 * Time: 23:15
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
