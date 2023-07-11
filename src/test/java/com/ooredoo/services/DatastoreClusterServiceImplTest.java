package com.ooredoo.services;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.DatastoreClusterRepository;
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


class DatastoreClusterServiceImplTest {

    @Mock
    private DatastoreClusterRepository datastoreClusterRepository;

    @Mock
    private DatastoreService datastoreService;

    @Mock
    private VMService vmService;

    @InjectMocks
    private DatastoreClusterService datastoreClusterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Mock data
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        datastoreCluster.setName("Mock TestCluster 1");
        List<DatastoreCluster> datastoreClusters = Arrays.asList(datastoreCluster);

        Datastore datastore = new Datastore();
        datastore.setName("Mock TestDatastore 1");
        List<Datastore> datastores = Arrays.asList(datastore);

        VM vm = new VM();
        vm.setName("Mock TestVM 1");
        List<VM> vms = Arrays.asList(vm);

        // Mock repository methods
        when(datastoreClusterRepository.getAll()).thenReturn(datastoreClusters);
        when(datastoreService.getDatastoresByDatastoreClusterName("Mock TestCluster 1")).thenReturn(datastores);
        when(vmService.getVMsByDatastoreName("Mock TestDatastore 1")).thenReturn(vms);

        // Call the service method
        Collection<DatastoreCluster> result = datastoreClusterService.getAll();

        // Verify the interactions and assertions
        verify(datastoreClusterRepository, times(1)).getAll();
        verify(datastoreService, times(1)).getDatastoresByDatastoreClusterName("Mock TestCluster 1");
        verify(vmService, times(1)).getVMsByDatastoreName("Mock TestDatastore 1");

        assertEquals(datastoreClusters, result);
        assertEquals(datastores, datastoreClusters.iterator().next().getDatastores());
        assertEquals(vms, datastores.iterator().next().getVMS());
    }

    @Test
    void testGetAllDatastoreClustersandDatastores() {
        // Mock data
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        datastoreCluster.setName("Mock TestCluster 1");
        List<DatastoreCluster> datastoreClusters = Arrays.asList(datastoreCluster);

        Datastore datastore = new Datastore();
        datastore.setName("Mock TestDatastore 1");
        List<Datastore> datastores = Arrays.asList(datastore);

        // Mock repository methods
        when(datastoreClusterRepository.getAll()).thenReturn(datastoreClusters);
        when(datastoreService.getDatastoresByDatastoreClusterName("Mock TestCluster 1")).thenReturn(datastores);

        // Call the service method
        Collection<DatastoreCluster> result = datastoreClusterService.getAllDatastoreClustersandDatastores();

        // Verify the interactions and assertions
        verify(datastoreClusterRepository, times(1)).getAll();
        verify(datastoreService, times(1)).getDatastoresByDatastoreClusterName("Mock TestCluster 1");

        assertEquals(datastoreClusters, result);
        assertEquals(datastores, datastoreClusters.iterator().next().getDatastores());
    }

    @Test
    void testGetAllDatastoreClusters() {
        // Mock data
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        datastoreCluster.setName("Mock TestCluster 1");
        List<DatastoreCluster> datastoreClusters = Arrays.asList(datastoreCluster);

        // Mock repository methods
        when(datastoreClusterRepository.getAllDatastoreClusters()).thenReturn(datastoreClusters);

        // Call the service method
        Collection<DatastoreCluster> result = datastoreClusterService.getAllDatastoreClusters();

        // Verify the interactions and assertions
        verify(datastoreClusterRepository, times(1)).getAllDatastoreClusters();

        assertEquals(datastoreClusters, result);
    }

    @Test
    void testGetDatastoreClustersByDatacenterName() {
        // Mock data
        String datacenterName = "Mock TestDatacenter 1";
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        datastoreCluster.setName("Mock TestCluster 1");
        List<DatastoreCluster> datastoreClusters = Arrays.asList(datastoreCluster);

        // Mock repository methods
        when(datastoreClusterRepository.findDatastoreClustersByDatacenterName(datacenterName)).thenReturn(datastoreClusters);

        // Call the service method
        List<DatastoreCluster> result = datastoreClusterService.getDatastoreClustersByDatacenterName(datacenterName);

        // Verify the interactions and assertions
        verify(datastoreClusterRepository, times(1)).findDatastoreClustersByDatacenterName(datacenterName);

        assertEquals(datastoreClusters, result);
    }

    @Test
    void testAddSingleDatastoreCluster() {
        // Mock data
        DatastoreCluster datastoreCluster = new DatastoreCluster();
        datastoreCluster.setName("Mock TestCluster 1");

        // Mock repository method
        when(datastoreClusterRepository.save(datastoreCluster)).thenReturn(datastoreCluster);

        // Call the service method
        DatastoreCluster result = datastoreClusterService.addSingleDatastoreCluster(datastoreCluster);

        // Verify the interaction and assertion
        verify(datastoreClusterRepository, times(1)).save(datastoreCluster);

        assertEquals(datastoreCluster, result);
    }

    @Test
    void testAddMultipleDatastoreClusters() {
        // Mock data
        DatastoreCluster datastoreCluster1 = new DatastoreCluster();
        datastoreCluster1.setName("Mock TestCluster 1");
        DatastoreCluster datastoreCluster2 = new DatastoreCluster();
        datastoreCluster2.setName("Mock TestCluster 2");
        List<DatastoreCluster> datastoreClusters = Arrays.asList(datastoreCluster1, datastoreCluster2);

        // Mock repository method
        when(datastoreClusterRepository.saveAll(datastoreClusters)).thenReturn(datastoreClusters);

        // Call the service method
        List<DatastoreCluster> result = datastoreClusterService.addMultipleDatastoreClusters(datastoreClusters);

        // Verify the interaction and assertion
        verify(datastoreClusterRepository, times(1)).saveAll(datastoreClusters);

        assertEquals(datastoreClusters, result);
    }

    @Test
    void testUpdateDatastoreClusterByName() {
        // Mock data
        String name = "Mock TestCluster 1";
        DatastoreCluster existingDatastoreCluster = new DatastoreCluster();
        existingDatastoreCluster.setName(name);

        DatastoreCluster updatedDatastoreCluster = new DatastoreCluster();
        updatedDatastoreCluster.setFreeSpace(100.0);
        updatedDatastoreCluster.setTotalCapacity(200.0);

        // Mock repository method
        when(datastoreClusterRepository.findByName(name)).thenReturn(existingDatastoreCluster);
        when(datastoreClusterRepository.save(existingDatastoreCluster)).thenReturn(existingDatastoreCluster);

        // Call the service method
        DatastoreCluster result = datastoreClusterService.updateDatastoreClusterByName(name, updatedDatastoreCluster);

        // Verify the interaction and assertion
        verify(datastoreClusterRepository, times(1)).findByName(name);
        verify(datastoreClusterRepository, times(1)).save(existingDatastoreCluster);

        assertEquals(existingDatastoreCluster, result);
        assertEquals(updatedDatastoreCluster.getFreeSpace(), result.getFreeSpace());
        assertEquals(updatedDatastoreCluster.getTotalCapacity(), result.getTotalCapacity());
    }

    @Test
    void testDeleteSingleDatastoreClusterByName() {
        // Mock data
        String name = "Mock TestCluster 1";

        // Call the service method
        datastoreClusterService.deleteSingleDatastoreClusterByName(name);

        // Verify the interaction
        verify(datastoreClusterRepository, times(1)).deleteByName(name);
    }

    @Test
    void testDeleteMultipleDatastoreClustersByName() {
        // Mock data
        List<String> names = Arrays.asList("Mock TestCluster 1", "Mock TestCluster 2");

        // Call the service method
        datastoreClusterService.deleteMultipleDatastoreClustersByName(names);

        // Verify the interaction
        verify(datastoreClusterRepository, times(1)).deleteAllByNameIn(names);
    }
}
