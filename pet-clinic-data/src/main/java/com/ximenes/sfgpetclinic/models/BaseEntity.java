package com.ximenes.sfgpetclinic.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Berkson Ximenes
 * Date: 31/05/2021
 * Time: 22:56
 */

/*
@MappedSuperclass - Serve para indicar à JPA que não deve ser criada uma tabela para essa entidade,
pois ela será herdada por outras entidades
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
