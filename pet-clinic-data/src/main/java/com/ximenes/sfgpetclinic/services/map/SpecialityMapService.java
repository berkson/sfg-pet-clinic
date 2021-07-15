package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Specialty;
import com.ximenes.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by berkson
 * Date: 11/06/2021
 * Time: 19:54
 */
@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Specialty, Long> implements SpecialityService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public Specialty save(Specialty object) {
        return super.save(object);
    }

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }
}
