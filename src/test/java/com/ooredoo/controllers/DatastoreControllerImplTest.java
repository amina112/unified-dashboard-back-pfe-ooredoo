package com.ooredoo.controllers;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.services.DatastoreService;
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
class DatastoreControllerImplTest {
    @Mock
    private DatastoreService datastoreService;

    @InjectMocks
    private DatastoreController datastoreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        Collection<Datastore> datastores = Arrays.asList(new Datastore(), new Datastore());
        when(datastoreService.getAll()).thenReturn(datastores);

        Collection<Datastore> result = datastoreController.getAll();

        assertEquals(datastores, result);
        verify(datastoreService, times(1)).getAll();
    }

    @Test
    void testGetAllDatastores() {
        Collection<Datastore> datastores = Arrays.asList(new Datastore(), new Datastore());
        when(datastoreService.getAllDatastores()).thenReturn(datastores);

        Collection<Datastore> result = datastoreController.getAllDatastores();

        assertEquals(datastores, result);
        verify(datastoreService, times(1)).getAllDatastores();
    }

    @Test
    void testGetDatastoresByVMName() {
        String VMName = "vmName";
        List<Datastore> datastores = Arrays.asList(new Datastore(), new Datastore());
        when(datastoreService.getDatastoresByVMName(VMName)).thenReturn(datastores);

        List<Datastore> result = datastoreController.getDatastoresByVMName(VMName);

        assertEquals(datastores, result);
        verify(datastoreService, times(1)).getDatastoresByVMName(VMName);
    }

    @Test
    void testAddSingleDatastore() {
        Datastore datastore = new Datastore();
        when(datastoreService.addSingleDatastore(datastore)).thenReturn(datastore);

        ResponseEntity<Datastore> response = datastoreController.addSingleDatastore(datastore);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datastore, response.getBody());
        verify(datastoreService, times(1)).addSingleDatastore(datastore);
    }

    @Test
    void testAddMultipleDatastores() {
        List<Datastore> datastores = Arrays.asList(new Datastore(), new Datastore());
        when(datastoreService.addMultipleDatastores(datastores)).thenReturn(datastores);

        ResponseEntity<List<Datastore>> response = datastoreController.addMultipleDatastores(datastores);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(datastores, response.getBody());
        verify(datastoreService, times(1)).addMultipleDatastores(datastores);
    }

    @Test
    void testUpdateDatastoreByName() {
        String name = "datastoreName";
        Datastore updatedDatastore = new Datastore();
        when(datastoreService.updateDatastoreByName(name, updatedDatastore)).thenReturn(updatedDatastore);

        Datastore result = datastoreController.updateDatastoreByName(name, updatedDatastore);

        assertEquals(updatedDatastore, result);
        verify(datastoreService, times(1)).updateDatastoreByName(name, updatedDatastore);
    }

    @Test
    void testDeleteDatastoreByName() {
        String name = "datastoreName";

        ResponseEntity<String> response = datastoreController.deleteDatastoreByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datastore deleted successfully", response.getBody());
        verify(datastoreService, times(1)).deleteSingleDatastoreByName(name);
    }

    @Test
    void testDeleteMultipleDatastoresByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = datastoreController.deleteMultipleDatastoresByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datastores deleted successfully", response.getBody());
        verify(datastoreService, times(1)).deleteMultipleDatastoresByName(names);
    }
}
