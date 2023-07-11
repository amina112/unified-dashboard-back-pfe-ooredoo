package com.ooredoo.controllers;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.services.DatastoreClusterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;
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
class DatastoreClusterControllerImplTest {
    @Mock
    private DatastoreClusterService datastoreClusterService;

    @InjectMocks
    private DatastoreClusterController datastoreClusterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        Collection<DatastoreCluster> datastoreClusters = Arrays.asList(new DatastoreCluster(), new DatastoreCluster());
        when(datastoreClusterService.getAll()).thenReturn(datastoreClusters);

        Collection<DatastoreCluster> result = datastoreClusterController.getAll();

        assertEquals(datastoreClusters, result);
        verify(datastoreClusterService, times(1)).getAll();
    }

    @Test
    void testGetAllDatastoreClustersandDatastores() {
        Collection<DatastoreCluster> datastoreClusters = Arrays.asList(new DatastoreCluster(), new DatastoreCluster());
        when(datastoreClusterService.getAllDatastoreClustersandDatastores()).thenReturn(datastoreClusters);

        Collection<DatastoreCluster> result = datastoreClusterController.getAllDatastoreClustersandDatastores();

        assertEquals(datastoreClusters, result);
        verify(datastoreClusterService, times(1)).getAllDatastoreClustersandDatastores();
    }

    @Test
    void testGetAllDatastoreClusters() {
        Collection<DatastoreCluster> datastoreClusters = Arrays.asList(new DatastoreCluster(), new DatastoreCluster());
        when(datastoreClusterService.getAllDatastoreClusters()).thenReturn(datastoreClusters);

        Collection<DatastoreCluster> result = datastoreClusterController.getAllDatastoreClusters();

        assertEquals(datastoreClusters, result);
        verify(datastoreClusterService, times(1)).getAllDatastoreClusters();
    }

    @Test
    void testAddSingleDatastoreCluster() {
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        when(datastoreClusterService.addSingleDatastoreCluster(datastoreCluster)).thenReturn(datastoreCluster);

        ResponseEntity<DatastoreCluster> response = datastoreClusterController.addSingleDatastoreCluster(datastoreCluster);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datastoreCluster, response.getBody());
        verify(datastoreClusterService, times(1)).addSingleDatastoreCluster(datastoreCluster);
    }

    @Test
    void testAddMultipleDatastoreClusters() {
        List<DatastoreCluster> datastoreClusters = Arrays.asList(new DatastoreCluster(), new DatastoreCluster());
        when(datastoreClusterService.addMultipleDatastoreClusters(datastoreClusters)).thenReturn(datastoreClusters);

        ResponseEntity<List<DatastoreCluster>> response = datastoreClusterController.addMultipleDatastoreClusters(datastoreClusters);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datastoreClusters, response.getBody());
        verify(datastoreClusterService, times(1)).addMultipleDatastoreClusters(datastoreClusters);
    }

    @Test
    void testUpdateDatastoreClusterByName() {
        String name = "datastoreClusterName";
        DatastoreCluster updatedDatastoreCluster = new DatastoreCluster();
        when(datastoreClusterService.updateDatastoreClusterByName(name, updatedDatastoreCluster)).thenReturn(updatedDatastoreCluster);

        DatastoreCluster result = datastoreClusterController.updateDatastoreClusterByName(name, updatedDatastoreCluster);

        assertEquals(updatedDatastoreCluster, result);
        verify(datastoreClusterService, times(1)).updateDatastoreClusterByName(name, updatedDatastoreCluster);
    }

    @Test
    void testDeleteDatastoreClusterByName() {
        String name = "datastoreClusterName";

        ResponseEntity<String> response = datastoreClusterController.deleteDatastoreClusterByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DatastoreCluster deleted successfully", response.getBody());
        verify(datastoreClusterService, times(1)).deleteSingleDatastoreClusterByName(name);
    }

    @Test
    void testDeleteMultipleDatastoreClustersByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = datastoreClusterController.deleteMultipleDatastoreClustersByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DatastoreClusters deleted successfully", response.getBody());
        verify(datastoreClusterService, times(1)).deleteMultipleDatastoreClustersByName(names);
    }
}
