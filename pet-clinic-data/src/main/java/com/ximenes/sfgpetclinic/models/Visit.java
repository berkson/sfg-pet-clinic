package com.ximenes.sfgpetclinic.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Berkson Ximenes
 * Date: 11/06/2021
 * Time: 09:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
