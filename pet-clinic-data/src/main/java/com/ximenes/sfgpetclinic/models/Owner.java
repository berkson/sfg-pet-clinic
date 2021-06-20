package com.ximenes.sfgpetclinic.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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
                 String address, String city, @Singular Set<Pet> pets, String telephone) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.pets = pets;
        this.telephone = telephone;
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

}

