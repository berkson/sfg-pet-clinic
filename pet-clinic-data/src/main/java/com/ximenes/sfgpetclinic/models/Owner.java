package com.ximenes.sfgpetclinic.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    // It's created this way for the lombok builder annotation works for all the properties
    // @Single - Creates a method pet witch we can use to add one pet at a time
    @Builder
    public Owner(Long id, String firstName, String lastName,
                 String address, String city, Set<Pet> pets, String telephone) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    // If I delete a Owner, cascade, deletes the pets too.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "telephone")
    private String telephone;

    public Pet getPet(String name) {
        final String lowerCaseName = name.toLowerCase(Locale.ROOT);
        return pets.stream()
                .filter(pet -> pet.getName().toLowerCase(Locale.ROOT)
                        .equals(lowerCaseName)).findFirst().orElse(null);
    }

    // this method must exists to save the Pet entity with the petService. we must attach the pet to the Owner ever
    public void addPet(Pet pet) {
        if (pet.isNew()) {
            getPetsInternal().add(pet);
        }
        pet.setOwner(this);
    }

    protected Set<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        return this.pets;
    }
}

