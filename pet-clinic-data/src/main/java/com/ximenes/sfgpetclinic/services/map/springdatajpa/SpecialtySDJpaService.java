package com.ximenes.sfgpetclinic.services.map.springdatajpa;

import com.ximenes.sfgpetclinic.models.Specialty;
import com.ximenes.sfgpetclinic.repositories.SpecialityRepository;
import com.ximenes.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Berkson Ximenes
 * Date: 18/06/2021
 * Time: 21:29
 */
@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialtySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
