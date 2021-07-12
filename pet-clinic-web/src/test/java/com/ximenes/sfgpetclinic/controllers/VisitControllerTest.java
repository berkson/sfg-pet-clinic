package com.ximenes.sfgpetclinic.controllers;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.models.Pet;
import com.ximenes.sfgpetclinic.models.Visit;
import com.ximenes.sfgpetclinic.services.PetService;
import com.ximenes.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Berkson Ximenes
 * Date: 12/07/2021
 * Time: 08:51
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    Pet pet;
    Visit visit;
    Owner owner;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).build();
        visit = Visit.builder().id(1L).build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }


    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findVisitByPetId(anyLong())).thenReturn(Arrays.asList(visit));

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit", "pet"));

        verify(petService, times(1)).findById(anyLong());
        verify(visitService, times(1)).findVisitByPetId(anyLong());

    }

    @Test
    void testProcessNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findVisitByPetId(anyLong())).thenReturn(Arrays.asList(visit));

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andExpect(model().attributeExists("visit", "pet"));

        ArgumentCaptor<Visit> visitArgumentCaptor = ArgumentCaptor.forClass(Visit.class);

        verify(visitService, times(1)).save(visitArgumentCaptor.capture());
    }
}