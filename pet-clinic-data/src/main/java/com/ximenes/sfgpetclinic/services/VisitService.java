package com.ximenes.sfgpetclinic.services;

import com.ximenes.sfgpetclinic.models.Visit;

import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 18/06/2021
 * Time: 22:04
 */
public interface VisitService extends CrudService<Visit, Long> {

    List<Visit> findVisitByPetId(Long petId);

}
