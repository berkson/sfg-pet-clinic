package com.ximenes.sfgpetclinic.model;

import java.io.Serializable;

/**
 * Created by Berkson Ximenes
 * Date: 31/05/2021
 * Time: 22:56
 */
public class BaseEntity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
