package com.ximenes.sfgpetclinic.models;

/**
 * Created by Berkson Ximenes
 * Date: 11/06/2021
 * Time: 09:40
 */
public class Speciality extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
