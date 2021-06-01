package com.ximenes.sfgpetclinic.model;

/**
 * Created by berkson
 * Date: 27/05/2021
 * Time: 22:11
 */
public class PetType extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
