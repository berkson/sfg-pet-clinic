package com.ximenes.sfgpetclinic.services.map;

import com.ximenes.sfgpetclinic.models.Pet;
import com.ximenes.sfgpetclinic.models.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 14/07/2021
 * Time: 22:04
 */
class PetMapServiceTest {

    PetMapService petMapService;
    private static final Long PET_ID1 = 1L;
    private static final Long PET_ID2 = 2L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(PET_ID1).petType(PetType.builder().name("Dog").build()).build());
        petMapService.save(Pet.builder().id(PET_ID2).petType(PetType.builder().name("Cat").build()).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertNotNull(petSet);
        assertEquals(2, petSet.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(PET_ID1);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(PET_ID2));

        assertEquals(1, petMapService.findAll().size());
        assertEquals(PET_ID1, petMapService.findAll().stream().findFirst().get().getId());
    }

    @Test
    void deleteByWrongId() {
        petMapService.deleteById(5L);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Pet pet = Pet.builder().name("Loro").petType(PetType.builder().name("Jegue").build()).build();
        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertEquals(3L, savedPet.getId());
        assertEquals(3, petMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Pet pet = Pet.builder().id(PET_ID1).name("Loro").petType(PetType.builder().name("Jegue").build()).build();
        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertEquals(PET_ID1, savedPet.getId());
        assertEquals("Loro", savedPet.getName());
        assertEquals("Jegue", savedPet.getPetType().getName());
    }

    @Test
    void saveNull() {

        assertThrows(RuntimeException.class, ()->{
            petMapService.save(null);
        });
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(PET_ID2);

        assertNotNull(pet);
        assertEquals(PET_ID2, pet.getId());
    }
}