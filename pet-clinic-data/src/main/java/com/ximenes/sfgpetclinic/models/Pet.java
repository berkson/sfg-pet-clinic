package com.ximenes.sfgpetclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:10
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    public Pet(String name, PetType petType, LocalDate birthDate) {
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
    }

    public Pet(String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits;


}
