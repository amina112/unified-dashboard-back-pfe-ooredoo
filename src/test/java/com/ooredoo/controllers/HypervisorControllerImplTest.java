package com.ooredoo.controllers;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.services.HypervisorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import com.ooredoo.services.DatacenterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class HypervisorControllerImplTest {
    @Mock
    private HypervisorService hypervisorService;

    @InjectMocks
    private HypervisorController hypervisorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        Collection<Hypervisor> hypervisors = Arrays.asList(new Hypervisor(), new Hypervisor());
        when(hypervisorService.getAll()).thenReturn(hypervisors);

        Collection<Hypervisor> result = hypervisorController.getAll();

        assertEquals(hypervisors, result);
        verify(hypervisorService, times(1)).getAll();
    }

    @Test
    void testGetAllHypervisors() {
        Collection<Hypervisor> hypervisors = Arrays.asList(new Hypervisor(), new Hypervisor());
        when(hypervisorService.getAllHypervisors()).thenReturn(hypervisors);

        Collection<Hypervisor> result = hypervisorController.getAllHypervisors();

        assertEquals(hypervisors, result);
        verify(hypervisorService, times(1)).getAllHypervisors();
    }

    @Test
    void testAddSingleHypervisor() {
        Hypervisor hypervisor = new Hypervisor();
        when(hypervisorService.addSingleHypervisor(hypervisor)).thenReturn(hypervisor);

        ResponseEntity<Hypervisor> response = hypervisorController.addSingleHypervisor(hypervisor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hypervisor, response.getBody());
        verify(hypervisorService, times(1)).addSingleHypervisor(hypervisor);
    }

    @Test
    void testAddMultipleHypervisors() {
        List<Hypervisor> hypervisors = Arrays.asList(new Hypervisor(), new Hypervisor());
        when(hypervisorService.addMultipleHypervisors(hypervisors)).thenReturn(hypervisors);

        ResponseEntity<List<Hypervisor>> response = hypervisorController.addMultipleHypervisors(hypervisors);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hypervisors, response.getBody());
        verify(hypervisorService, times(1)).addMultipleHypervisors(hypervisors);
    }

    @Test
    void testUpdateHypervisorByName() {
        String name = "hypervisorName";
        Hypervisor updatedHypervisor = new Hypervisor();
        when(hypervisorService.updateHypervisorByName(name, updatedHypervisor)).thenReturn(updatedHypervisor);

        Hypervisor result = hypervisorController.updateHypervisorByName(name, updatedHypervisor);

        assertEquals(updatedHypervisor, result);
        verify(hypervisorService, times(1)).updateHypervisorByName(name, updatedHypervisor);
    }

    @Test
    void testDeleteHypervisorByName() {
        String name = "hypervisorName";

        ResponseEntity<String> response = hypervisorController.deleteHypervisorByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hypervisor deleted successfully", response.getBody());
        verify(hypervisorService, times(1)).deleteSingleHypervisorByName(name);
    }

    @Test
    void testDeleteMultipleHypervisorsByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = hypervisorController.deleteMultipleHypervisorsByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hypervisors deleted successfully", response.getBody());
        verify(hypervisorService, times(1)).deleteMultipleHypervisorsByName(names);
    }
}
