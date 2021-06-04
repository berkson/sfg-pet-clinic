package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 21:02
 */
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }
}
