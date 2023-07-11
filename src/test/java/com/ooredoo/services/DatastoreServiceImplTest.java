package com.ooredoo.services;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.DatastoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DatastoreServiceImplTest {

    @Mock
    private DatastoreRepository datastoreRepository;

    @Mock
    private VMService vmService;

    @InjectMocks
    private DatastoreService datastoreService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Mock data
        Datastore datastore1 = new Datastore("Mock TestDatastore 1");
        Datastore datastore2 = new Datastore("Mock TestDatastore 2");
        List<Datastore> datastores = Arrays.asList(datastore1, datastore2);

        VM vm1 = new VM("Mock TestVM 1");
        VM vm2 = new VM("Mock TestVM 2");
        List<VM> vms = Arrays.asList(vm1, vm2);

        when(datastoreRepository.getAll()).thenReturn(datastores);
        when(vmService.getVMsByDatastoreName(anyString())).thenReturn(vms);

        // Call the method
        Collection<Datastore> result = datastoreService.getAll();

        // Verify the interactions and assertions
        verify(datastoreRepository).getAll();
        verify(vmService, times(2)).getVMsByDatastoreName(anyString());

        assertEquals(datastores.size(), result.size());
        // Verify that the retrieved datastores have the VMs set
        for (Datastore datastore : result) {
            assertEquals(vms, datastore.getVMS());
        }
    }

    @Test
    void testGetAllDatastores() {
        // Mock data
        List<Datastore> datastores = Arrays.asList(new Datastore("Mock TestDatastore 1"), new Datastore("Mock TestDatastore 2"));

        when(datastoreRepository.getAllDatastores()).thenReturn(datastores);

        // Call the method
        Collection<Datastore> result = datastoreService.getAllDatastores();

        // Verify the interactions and assertions
        verify(datastoreRepository).getAllDatastores();

        assertEquals(datastores.size(), result.size());
    }

    @Test
    void testGetDatastoresByDatastoreClusterName() {
        // Mock data
        String datastoreClusterName = "Mock TestCluster";
        List<Datastore> datastores = Arrays.asList(new Datastore("Mock TestDatastore 1"), new Datastore("Mock TestDatastore 2"));

        when(datastoreRepository.findDatastoresByDatastoreClusterName(datastoreClusterName)).thenReturn(datastores);

        // Call the method
        List<Datastore> result = datastoreService.getDatastoresByDatastoreClusterName(datastoreClusterName);

        // Verify the interactions and assertions
        verify(datastoreRepository).findDatastoresByDatastoreClusterName(datastoreClusterName);

        assertEquals(datastores, result);
    }

    @Test
    void testGetDatastoresByVMName() {
        // Mock data
        String vmName = "Mock TestVM 1";
        List<Datastore> datastores = Arrays.asList(new Datastore("Mock TestDatastore 1"), new Datastore("Mock TestDatastore 2"));

        when(datastoreRepository.findDatastoresByVMName(vmName)).thenReturn(datastores);

        // Call the method
        List<Datastore> result = datastoreService.getDatastoresByVMName(vmName);

        // Verify the interactions and assertions
        verify(datastoreRepository).findDatastoresByVMName(vmName);

        assertEquals(datastores, result);
    }

    @Test
    void testGetDatastoresByDatacenterName() {
        // Mock data
        String datacenterName = "Mock TestDatacenter";
        List<Datastore> datastores = Arrays.asList(new Datastore("Mock TestDatastore 1"), new Datastore("Mock TestDatastore 2"));

        when(datastoreRepository.findDatastoresByDatacenterName(datacenterName)).thenReturn(datastores);

        // Call the method
        List<Datastore> result = datastoreService.getDatastoresByDatacenterName(datacenterName);

        // Verify the interactions and assertions
        verify(datastoreRepository).findDatastoresByDatacenterName(datacenterName);

        assertEquals(datastores, result);
    }

    @Test
    void testAddSingleDatastore() {
        // Mock data
        Datastore datastore = new Datastore();

        when(datastoreRepository.save(datastore)).thenReturn(datastore);

        // Call the method
        Datastore result = datastoreService.addSingleDatastore(datastore);

        // Verify the interactions and assertions
        verify(datastoreRepository).save(datastore);

        assertEquals(datastore, result);
    }

    @Test
    void testAddMultipleDatastores() {
        // Mock data
        List<Datastore> datastores = Arrays.asList(new Datastore("Mock TestDatastore 1"), new Datastore("Mock TestDatastore 2"));

        when(datastoreRepository.saveAll(datastores)).thenReturn(datastores);

        // Call the method
        List<Datastore> result = datastoreService.addMultipleDatastores(datastores);

        // Verify the interactions and assertions
        verify(datastoreRepository).saveAll(datastores);

        assertEquals(datastores, result);
    }

    @Test
    void testUpdateDatastoreByName() {
        // Mock data
        String name = "Mock TestDatastore";
        Datastore updatedDatastore = new Datastore("Updated Mock TestDatastore");
        Datastore existingDatastore = new Datastore(name);

        when(datastoreRepository.findByName(name)).thenReturn(existingDatastore);
        when(datastoreRepository.save(existingDatastore)).thenReturn(existingDatastore);

        // Call the method
        Datastore result = datastoreService.updateDatastoreByName(name, updatedDatastore);

        // Verify the interactions and assertions
        verify(datastoreRepository).findByName(name);
        verify(datastoreRepository).save(existingDatastore);

        assertEquals(existingDatastore, result);
        // Verify that the fields are updated correctly
        // (you can add assertions for each field if necessary)
        assertEquals(updatedDatastore.getName(), existingDatastore.getName());
        assertEquals(updatedDatastore.getType(), existingDatastore.getType());
    }

    @Test
    void testDeleteSingleDatastoreByName() {
        // Mock data
        String name = "Mock TestDatastore";

        // Call the method
        datastoreService.deleteSingleDatastoreByName(name);

        // Verify the interactions
        verify(datastoreRepository).deleteByName(name);
    }

    @Test
    void testDeleteMultipleDatastoresByName() {
        // Mock data
        List<String> names = Arrays.asList("Mock Testdatastore1", "Mock Testdatastore2");

        // Call the method
        datastoreService.deleteMultipleDatastoresByName(names);

        // Verify the interactions
        verify(datastoreRepository).deleteAllByNameIn(names);
    }

}
