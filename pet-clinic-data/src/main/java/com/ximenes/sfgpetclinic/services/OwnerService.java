package com.ximenes.sfgpetclinic.services;

import com.ximenes.sfgpetclinic.model.Owner;


/**
 * Created by Berkson Ximenes
 * Date: 31/05/2021
 * Time: 20:54
 */
public interface OwnerService extends CrudService<Owner, Long> {
    Owner findBylastName(String lastName);
}
