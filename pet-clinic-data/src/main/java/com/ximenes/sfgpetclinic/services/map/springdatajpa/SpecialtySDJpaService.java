package com.ximenes.sfgpetclinic.services.map.springdatajpa;

import com.ximenes.sfgpetclinic.models.Speciality;
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
    public Set<Speciality> findAll() {
        Set<Speciality> specialties = new HashSet<>();
        specialityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
