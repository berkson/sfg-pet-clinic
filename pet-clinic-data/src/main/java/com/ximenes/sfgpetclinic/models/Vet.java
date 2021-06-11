package com.ximenes.sfgpetclinic.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:09
 */
public class Vet extends Person {

    private Set<Speciality> specialties = new HashSet<>();

    public Set<Speciality> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Speciality> specialties) {
        this.specialties = specialties;
    }
}
