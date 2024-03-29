package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 22/06/2021
 * Time: 21:21
 */
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Soares";
    final Long ownerId2 = 2L;
    final String lastName2 = "Soar";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
        ownerMapService.save(Owner.builder().id(ownerId2).lastName(lastName2).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(2, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long ownerId2 = 2L;
        Owner owner2 = Owner.builder().id(ownerId2).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner3 = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner3);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }


    @Test
    void findByLastName() {
        Owner owner4 = ownerMapService.findByLastName(lastName);

        assertNotNull(owner4);
        assertEquals(lastName, owner4.getLastName());
        assertEquals(ownerId, owner4.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner4 = ownerMapService.findByLastName("foo");

        assertNull(owner4);
    }

    @Test
    void testFindByLastNameLike() {
        List<Owner> ownerList = ownerMapService.findByLastNameLike("soar");

        assertNotNull(ownerList);
        assertEquals(2, ownerList.size());
    }

    @Test
    void testFindByLastNameLikeNotFound() {
        List<Owner> ownerList = ownerMapService.findByLastNameLike("teste");

        assertNotNull(ownerList);
        assertEquals(0, ownerList.size());
    }
}