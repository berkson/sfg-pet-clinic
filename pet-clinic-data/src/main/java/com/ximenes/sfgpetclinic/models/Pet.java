package com.ximenes.sfgpetclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:53
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits;

    protected Set<Visit> getVisitsInternal(){
        if (this.visits == null){
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    public void setVisitsInternal(Collection<Visit> visits){
        this.visits = new LinkedHashSet<>(visits);
    }

    public void addVisit(Visit visit){
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }
}
