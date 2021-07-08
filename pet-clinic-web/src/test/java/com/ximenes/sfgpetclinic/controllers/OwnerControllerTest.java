package com.ximenes.sfgpetclinic.controllers;

import com.ximenes.sfgpetclinic.models.Owner;
import com.ximenes.sfgpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Berkson Ximenes
 * Date: 23/06/2021
 * Time: 21:12
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;
    final Long ownerId = 1L;
    final Long ownerId2 = 2L;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(ownerId).build());
        owners.add(Owner.builder().id(ownerId2).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }


    @Test
    void testOwnerById() throws Exception {
        Owner owner = Owner.builder().id(1L).build();

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("owners/findOwners"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findByLastNameLike(anyString())).thenReturn(new ArrayList<>(owners));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeDoesNotExist("selections"));
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processeUpdateOwnerForm() throws Exception{
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }
}