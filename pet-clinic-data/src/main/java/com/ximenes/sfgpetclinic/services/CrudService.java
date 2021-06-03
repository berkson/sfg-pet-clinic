package com.ximenes.sfgpetclinic.services;

import java.util.Set;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 20:28
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
