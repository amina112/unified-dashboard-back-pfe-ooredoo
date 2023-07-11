package com.ooredoo.services;

import com.ooredoo.entities.*;
import com.ooredoo.repositories.DatacenterRepository;
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

class DatacenterServiceImplTest {
    @Mock
    private DatacenterRepository datacenterRepository;

    @Mock
    private HypervisorService hypervisorService;

    @Mock
    private DatastoreService datastoreService;

    @Mock
    private HypervisorClusterService hypervisorClusterService;

    @Mock
    private VMService vmService;

    @InjectMocks
    private DatacenterService datacenterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Mock data
        Datacenter datacenter = new Datacenter();
        datacenter.setName("Datacenter 1");
        when(datacenterRepository.getAllDatacenters()).thenReturn(Arrays.asList(datacenter));

        HypervisorCluster hypervisorCluster = new HypervisorCluster();
        hypervisorCluster.setName("Cluster 1");
        when(hypervisorClusterService.getHypervisorClustersByDatacenterName("Datacenter 1")).thenReturn(Arrays.asList(hypervisorCluster));

        Hypervisor hypervisor = new Hypervisor();
        hypervisor.setName("Hypervisor 1");
        when(hypervisorService.getHypervisorsByHypervisorClusterName("Cluster 1")).thenReturn(Arrays.asList(hypervisor));

        VM vm = new VM();
        vm.setName("VM 1");
        when(vmService.getVMsByHypervisorName("Hypervisor 1")).thenReturn(Arrays.asList(vm));

        // Invoke the method
        Collection<Datacenter> result = datacenterService.getAll();

        // Verify the results
        assertEquals(1, result.size());
        Datacenter resultDatacenter = result.iterator().next();
        assertEquals("Datacenter 1", resultDatacenter.getName());
        List<HypervisorCluster> resultClusters = resultDatacenter.getHypervisorClustersList();
        assertEquals(1, resultClusters.size());
        HypervisorCluster resultCluster = resultClusters.get(0);
        assertEquals("Cluster 1", resultCluster.getName());
        List<Hypervisor> resultHypervisors = resultCluster.getHypervisors();
        assertEquals(1, resultHypervisors.size());
        Hypervisor resultHypervisor = resultHypervisors.get(0);
        assertEquals("Hypervisor 1", resultHypervisor.getName());
        List<VM> resultVMs = resultHypervisor.getVMS();
        assertEquals(1, resultVMs.size());
        VM resultVM = resultVMs.get(0);
        assertEquals("VM 1", resultVM.getName());

        // Verify that the mock methods were called
        verify(datacenterRepository, times(1)).getAllDatacenters();
        verify(hypervisorClusterService, times(1)).getHypervisorClustersByDatacenterName("Datacenter 1");
        verify(hypervisorService, times(1)).getHypervisorsByHypervisorClusterName("Cluster 1");
        verify(vmService, times(1)).getVMsByHypervisorName("Hypervisor 1");
    }

    @Test
    void testGetAllDatacentersandDatastores() {
        // Mock data
        Datacenter datacenter = new Datacenter();
        datacenter.setName("Datacenter 1");
        when(datacenterRepository.getAllDatacenters()).thenReturn(Arrays.asList(datacenter));

        String datacenterName = "Datacenter 1";
        Datastore datastore = new Datastore();
        datastore.setName("Datastore 1");
        when(datastoreService.getDatastoresByDatacenterName(datacenterName)).thenReturn(Arrays.asList(datastore));

        // Invoke the method
        Collection<Datacenter> result = datacenterService.getAllDatacentersandDatastores();

        // Verify the results
        assertEquals(1, result.size());
        Datacenter resultDatacenter = result.iterator().next();
        assertEquals("Datacenter 1", resultDatacenter.getName());
        List<Datastore> resultDatastores = resultDatacenter.getDatastoresList();
        assertEquals(1, resultDatastores.size());
        Datastore resultDatastore = resultDatastores.get(0);
        assertEquals("Datastore 1", resultDatastore.getName());

        // Verify that the mock methods were called
        verify(datacenterRepository, times(1)).getAllDatacenters();
        verify(datastoreService, times(1)).getDatastoresByDatacenterName(datacenterName);
    }

    @Test
    void testGetAllDatacentersandHypervisorClusters() {
        // Mock data
        Datacenter datacenter = new Datacenter();
        datacenter.setName("Datacenter 1");
        when(datacenterRepository.getAllDatacenters()).thenReturn(Arrays.asList(datacenter));

        String datacenterName = "Datacenter 1";
        HypervisorCluster hypervisorCluster = new HypervisorCluster();
        hypervisorCluster.setName("Cluster 1");
        when(hypervisorClusterService.getHypervisorClustersByDatacenterName(datacenterName)).thenReturn(Arrays.asList(hypervisorCluster));

        // Invoke the method
        Collection<Datacenter> result = datacenterService.getAllDatacentersandHypervisorClusters();

        // Verify the results
        assertEquals(1, result.size());
        Datacenter resultDatacenter = result.iterator().next();
        assertEquals("Datacenter 1", resultDatacenter.getName());
        List<HypervisorCluster> resultClusters = resultDatacenter.getHypervisorClustersList();
        assertEquals(1, resultClusters.size());
        HypervisorCluster resultCluster = resultClusters.get(0);
        assertEquals("Cluster 1", resultCluster.getName());

        // Verify that the mock methods were called
        verify(datacenterRepository, times(1)).getAllDatacenters();
        verify(hypervisorClusterService, times(1)).getHypervisorClustersByDatacenterName(datacenterName);
    }

    @Test
    void testGetAllDatacenters() {
        // Mock data
        Datacenter datacenter = new Datacenter();
        datacenter.setName("Datacenter 1");
        when(datacenterRepository.getAllDatacenters()).thenReturn(Arrays.asList(datacenter));

        // Invoke the method
        Collection<Datacenter> result = datacenterService.getAllDatacenters();

        // Verify the results
        assertEquals(1, result.size());
        Datacenter resultDatacenter = result.iterator().next();
        assertEquals("Datacenter 1", resultDatacenter.getName());

        // Verify that the mock method was called
        verify(datacenterRepository, times(1)).getAllDatacenters();
    }

    @Test
    void testAddSingleDatacenter() {
        // Mock data
        Datacenter datacenter = new Datacenter();
        datacenter.setName("Datacenter 1");
        when(datacenterRepository.save(datacenter)).thenReturn(datacenter);

        // Invoke the method
        Datacenter result = datacenterService.addSingleDatacenter(datacenter);

        // Verify the result
        assertEquals("Datacenter 1", result.getName());

        // Verify that the mock method was called
        verify(datacenterRepository, times(1)).save(datacenter);
    }

    @Test
    void testAddMultipleDatacenters() {
        // Mock data
        Datacenter datacenter1 = new Datacenter();
        datacenter1.setName("Datacenter 1");
        Datacenter datacenter2 = new Datacenter();
        datacenter2.setName("Datacenter 2");
        List<Datacenter> datacenters = Arrays.asList(datacenter1, datacenter2);
        when(datacenterRepository.saveAll(datacenters)).thenReturn(datacenters);

        // Invoke the method
        List<Datacenter> result = datacenterService.addMultipleDatacenters(datacenters);

        // Verify the results
        assertEquals(2, result.size());
        assertEquals("Datacenter 1", result.get(0).getName());
        assertEquals("Datacenter 2", result.get(1).getName());

        // Verify that the mock method was called
        verify(datacenterRepository, times(1)).saveAll(datacenters);
    }

    @Test
    void testUpdateDatacenterByName() {
        // Mock data
        String name = "Datacenter 1";
        Datacenter existingDatacenter = new Datacenter(name);
        Datacenter updatedDatacenter = new Datacenter("Updated Datacenter");
        when(datacenterRepository.findByName(name)).thenReturn(existingDatacenter);
        when(datacenterRepository.save(existingDatacenter)).thenReturn(existingDatacenter);


        // Invoke the method
        Datacenter result = datacenterService.updateDatacenterByName("Datacenter 1", updatedDatacenter);

        // Verify the interactions and assertions
        verify(datacenterRepository).findByName(name);
        verify(datacenterRepository).save(existingDatacenter);
        assertEquals(existingDatacenter, result);

        // Verify that the specific fields were updated
        verify(datacenterRepository, times(1)).findByName("Datacenter 1");
        verify(datacenterRepository, times(1)).save(existingDatacenter);

    }

    @Test
    void testDeleteSingleDatacenterByName() {
        // Invoke the method
        datacenterService.deleteSingleDatacenterByName("Datacenter 1");

        // Verify that the mock method was called
        verify(datacenterRepository, times(1)).deleteByName("Datacenter 1");
    }

    @Test
    void testDeleteMultipleDatacentersByName() {
        // Invoke the method
        List<String> names = Arrays.asList("Datacenter 1", "Datacenter 2");
        datacenterService.deleteMultipleDatacentersByName(names);

        // Verify that the mock method was called
        verify(datacenterRepository, times(1)).deleteAllByNameIn(names);
    }

}
