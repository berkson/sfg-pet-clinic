package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.model.Vet;
import com.ximenes.sfgpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 21:07
 */
public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
