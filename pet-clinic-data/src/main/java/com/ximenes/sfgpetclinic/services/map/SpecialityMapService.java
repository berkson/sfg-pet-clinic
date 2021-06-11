package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Speciality;
import com.ximenes.sfgpetclinic.services.SpecialtiesService;

import java.util.Set;

/**
 * Created by berkson
 * Date: 11/06/2021
 * Time: 19:54
 */
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialtiesService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
