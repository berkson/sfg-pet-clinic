package com.ximenes.sfgpetclinic.services.map.springdatajpa;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.repositories.OwnerRepository;
import com.ximenes.sfgpetclinic.repositories.PetRepository;
import com.ximenes.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Berkson Ximenes
 * Date: 22/06/2021
 * Time: 22:27
 */
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    final static String LAST_NAME = "Soares";
    final static String LAST_NAME1 = "Soar";
    Set<Owner> returnOwnerSet;

    @BeforeEach
    void setUp() {
        returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
        returnOwnerSet.add(Owner.builder().id(2L).lastName(LAST_NAME1).build());
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(LAST_NAME, savedOwner.getLastName());

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner soares = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, soares.getLastName());

        verify(ownerRepository).findByLastName(any());

    }

}