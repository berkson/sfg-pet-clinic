package com.ximenes.sfgpetclinic.models;

import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:10
 */
public class Owner extends Person {

    private Set<Pet> pets;


    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
