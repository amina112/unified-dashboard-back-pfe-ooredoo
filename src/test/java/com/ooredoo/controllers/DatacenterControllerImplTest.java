package com.ooredoo.controllers;

import com.ooredoo.entities.Datacenter;

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


class DatacenterControllerImplTest {
    @Mock
    private DatacenterService datacenterService;

    @InjectMocks
    private DatacenterController datacenterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        Collection<Datacenter> datacenters = Arrays.asList(new Datacenter(), new Datacenter());
        when(datacenterService.getAll()).thenReturn(datacenters);

        Collection<Datacenter> result = datacenterController.getAll();

        assertEquals(datacenters, result);
        verify(datacenterService, times(1)).getAll();
    }

    @Test
    void testGetAllDatacentersandHypervisorClusters() {
        Collection<Datacenter> datacenters = Arrays.asList(new Datacenter(), new Datacenter());
        when(datacenterService.getAllDatacentersandHypervisorClusters()).thenReturn(datacenters);

        Collection<Datacenter> result = datacenterController.getAllDatacentersandHypervisorClusters();

        assertEquals(datacenters, result);
        verify(datacenterService, times(1)).getAllDatacentersandHypervisorClusters();
    }

    @Test
    void testGetAllDatacentersandDatastores() {
        Collection<Datacenter> datacenters = Arrays.asList(new Datacenter(), new Datacenter());
        when(datacenterService.getAllDatacentersandDatastores()).thenReturn(datacenters);

        Collection<Datacenter> result = datacenterController.getAllDatacentersandDatastores();

        assertEquals(datacenters, result);
        verify(datacenterService, times(1)).getAllDatacentersandDatastores();
    }

    @Test
    void testGetAllDatacenters() {
        Collection<Datacenter> datacenters = Arrays.asList(new Datacenter(), new Datacenter());
        when(datacenterService.getAllDatacenters()).thenReturn(datacenters);

        Collection<Datacenter> result = datacenterController.getAllDatacenters();

        assertEquals(datacenters, result);
        verify(datacenterService, times(1)).getAllDatacenters();
    }

    @Test
    void testAddSingleDatacenter() {
        Datacenter datacenter = new Datacenter();
        when(datacenterService.addSingleDatacenter(datacenter)).thenReturn(datacenter);

        ResponseEntity<Datacenter> response = datacenterController.addSingleDatacenter(datacenter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datacenter, response.getBody());
        verify(datacenterService, times(1)).addSingleDatacenter(datacenter);
    }

    @Test
    void testAddMultipleDatacenters() {
        List<Datacenter> datacenters = Arrays.asList(new Datacenter(), new Datacenter());
        when(datacenterService.addMultipleDatacenters(datacenters)).thenReturn(datacenters);

        ResponseEntity<List<Datacenter>> response = datacenterController.addMultipleDatacenters(datacenters);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datacenters, response.getBody());
        verify(datacenterService, times(1)).addMultipleDatacenters(datacenters);
    }

    @Test
    void testUpdateDatacenterByName() {
        String name = "datacenterName";
        Datacenter updatedDatacenter = new Datacenter();
        when(datacenterService.updateDatacenterByName(name, updatedDatacenter)).thenReturn(updatedDatacenter);

        Datacenter result = datacenterController.updateDatacenterByName(name, updatedDatacenter);

        assertEquals(updatedDatacenter, result);
        verify(datacenterService, times(1)).updateDatacenterByName(name, updatedDatacenter);
    }

    @Test
    void testDeleteDatacenterByName() {
        String name = "datacenterName";

        ResponseEntity<String> response = datacenterController.deleteDatacenterByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datacenter deleted successfully", response.getBody());
        verify(datacenterService, times(1)).deleteSingleDatacenterByName(name);
    }

    @Test
    void testDeleteMultipleDatacentersByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = datacenterController.deleteMultipleDatacentersByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datacenters deleted successfully", response.getBody());
        verify(datacenterService, times(1)).deleteMultipleDatacentersByName(names);
    }
}
