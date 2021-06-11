package com.ximenes.sfgpetclinic.models;

import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:09
 */
public class Vet extends Person {

    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
