package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorClusterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HypervisorClusterServiceImplTest {

    @Mock
    private HypervisorClusterRepository hypervisorClusterRepository;
    @Mock
    private VMService vmService;
    @Mock
    private HypervisorService hypervisorService;

    private HypervisorClusterService hypervisorClusterService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        hypervisorClusterService = new HypervisorClusterService(hypervisorClusterRepository, vmService, hypervisorService);
    }

    @Test
    void testGetAll() {
        // Mocking data
        HypervisorCluster hypervisorCluster1 = new HypervisorCluster();
        hypervisorCluster1.setName("Mock TestHypervisorCluster1");
        HypervisorCluster hypervisorCluster2 = new HypervisorCluster();
        hypervisorCluster2.setName("Mock TestHypervisorCluster2");
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(hypervisorCluster1, hypervisorCluster2);

        Hypervisor hypervisor1 = new Hypervisor();
        hypervisor1.setName("Mock TestHypervisor1");
        Hypervisor hypervisor2 = new Hypervisor();
        hypervisor2.setName("Mock TestHypervisor2");
        List<Hypervisor> hypervisors = Arrays.asList(hypervisor1, hypervisor2);

        VM vm1 = new VM();
        vm1.setName("Mock TestVM1");
        VM vm2 = new VM();
        vm2.setName("Mock TestVM2");
        List<VM> vms = Arrays.asList(vm1, vm2);

        // Mocking repository and services
        when(hypervisorClusterRepository.getAll()).thenReturn(hypervisorClusters);
        when(hypervisorService.getHypervisorsByHypervisorClusterName(anyString())).thenReturn(hypervisors);
        when(vmService.getVMsByHypervisorName(anyString())).thenReturn(vms);

        // Perform the test
        Collection<HypervisorCluster> result = hypervisorClusterService.getAll();

        // Verify the result
        assertEquals(2, result.size());
        HypervisorCluster resultCluster1 = result.stream().filter(c -> c.getName().equals("Mock TestHypervisorCluster1")).findFirst().orElse(null);
        HypervisorCluster resultCluster2 = result.stream().filter(c -> c.getName().equals("Mock TestHypervisorCluster2")).findFirst().orElse(null);
        assertEquals(2, resultCluster1.getHypervisors().size());
        assertEquals(2, resultCluster2.getHypervisors().size());
        assertEquals(2, resultCluster1.getHypervisors().get(0).getVMS().size());
        assertEquals(2, resultCluster2.getHypervisors().get(0).getVMS().size());

        // Verify that the repository and services were called as expected
        verify(hypervisorClusterRepository, times(1)).getAll();
        verify(hypervisorService, times(2)).getHypervisorsByHypervisorClusterName(anyString());
        verify(vmService, times(4)).getVMsByHypervisorName(anyString());
    }

    @Test
    void testGetAllHypervisorClustersandHypervisors() {
        // Mocking data
        HypervisorCluster hypervisorCluster1 = new HypervisorCluster();
        hypervisorCluster1.setName("Mock TestHypervisorCluster1");
        HypervisorCluster hypervisorCluster2 = new HypervisorCluster();
        hypervisorCluster2.setName("Mock TestHypervisorCluster2");
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(hypervisorCluster1, hypervisorCluster2);

        Hypervisor hypervisor1 = new Hypervisor();
        hypervisor1.setName("Mock TestHypervisor1");
        Hypervisor hypervisor2 = new Hypervisor();
        hypervisor2.setName("Mock TestHypervisor2");
        List<Hypervisor> hypervisors = Arrays.asList(hypervisor1, hypervisor2);

        // Mocking repository and services
        when(hypervisorClusterRepository.getAll()).thenReturn(hypervisorClusters);
        when(hypervisorService.getHypervisorsByHypervisorClusterName(anyString())).thenReturn(hypervisors);

        // Perform the test
        Collection<HypervisorCluster> result = hypervisorClusterService.getAllHypervisorClustersandHypervisors();

        // Verify the result
        assertEquals(2, result.size());
        HypervisorCluster resultCluster1 = result.stream().filter(c -> c.getName().equals("Mock TestHypervisorCluster1")).findFirst().orElse(null);
        HypervisorCluster resultCluster2 = result.stream().filter(c -> c.getName().equals("Mock TestHypervisorCluster2")).findFirst().orElse(null);
        assertEquals(2, resultCluster1.getHypervisors().size());
        assertEquals(2, resultCluster2.getHypervisors().size());

        // Verify that the repository and services were called as expected
        verify(hypervisorClusterRepository, times(1)).getAll();
        verify(hypervisorService, times(2)).getHypervisorsByHypervisorClusterName(anyString());
    }

    @Test
    void testGetAllHypervisorClusters() {
        // Mocking data
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(
                new HypervisorCluster("Mock TestHypervisorCluster1"),
                new HypervisorCluster("Mock TestHypervisorCluster2")
        );

        // Mocking repository
        when(hypervisorClusterRepository.getAllHypervisorClusters()).thenReturn(hypervisorClusters);

        // Perform the test
        Collection<HypervisorCluster> result = hypervisorClusterService.getAllHypervisorClusters();

        // Verify the result
        assertEquals(2, result.size());

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).getAllHypervisorClusters();
    }

    @Test
    void testGetHypervisorClustersByDatacenterName() {
        // Mocking data
        String datacenterName = "Datacenter1";
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(
                new HypervisorCluster("Mock TestHypervisorCluster1"),
                new HypervisorCluster("Mock TestHypervisorCluster2")
        );

        // Mocking repository
        when(hypervisorClusterRepository.findHypervisorClustersByDatacenterName(datacenterName)).thenReturn(hypervisorClusters);

        // Perform the test
        List<HypervisorCluster> result = hypervisorClusterService.getHypervisorClustersByDatacenterName(datacenterName);

        // Verify the result
        assertEquals(2, result.size());

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).findHypervisorClustersByDatacenterName(datacenterName);
    }

    @Test
    void testAddSingleHypervisorCluster() {
        // Mocking data
        HypervisorCluster hypervisorCluster = new HypervisorCluster("Mock TestHypervisorCluster1");

        // Mocking repository
        when(hypervisorClusterRepository.save(hypervisorCluster)).thenReturn(hypervisorCluster);

        // Perform the test
        HypervisorCluster result = hypervisorClusterService.addSingleHypervisorCluster(hypervisorCluster);

        // Verify the result
        assertEquals(hypervisorCluster, result);

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).save(hypervisorCluster);
    }

    @Test
    void testAddMultipleHypervisorClusters() {
        // Mocking data
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(
                new HypervisorCluster("Mock TestHypervisorCluster1"),
                new HypervisorCluster("Mock TestHypervisorCluster2")
        );

        // Mocking repository
        when(hypervisorClusterRepository.saveAll(hypervisorClusters)).thenReturn(hypervisorClusters);

        // Perform the test
        List<HypervisorCluster> result = hypervisorClusterService.addMultipleHypervisorClusters(hypervisorClusters);

        // Verify the result
        assertEquals(hypervisorClusters, result);

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).saveAll(hypervisorClusters);
    }

    @Test
    void testUpdateHypervisorClusterByName() {
        // Mocking data
        String name = "Mock TestHypervisorCluster1";
        HypervisorCluster existingHypervisorCluster = new HypervisorCluster();
        existingHypervisorCluster.setName(name);

        HypervisorCluster updatedHypervisorCluster = new HypervisorCluster();
        updatedHypervisorCluster.setName("UpdatedCluster1");
        updatedHypervisorCluster.setNumberOfESX(2);
        updatedHypervisorCluster.setTotalMemory(4096);

        // Mocking repository
        when(hypervisorClusterRepository.findByName(name)).thenReturn(existingHypervisorCluster);
        when(hypervisorClusterRepository.save(existingHypervisorCluster)).thenReturn(existingHypervisorCluster);

        // Perform the test
        HypervisorCluster result = hypervisorClusterService.updateHypervisorClusterByName(name, updatedHypervisorCluster);

        // Verify the result
        assertEquals(existingHypervisorCluster, result);
        assertEquals("UpdatedCluster1", result.getName());
        assertEquals(2, result.getNumberOfESX());
        assertEquals(4096, result.getTotalMemory());

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).findByName(name);
        verify(hypervisorClusterRepository, times(1)).save(existingHypervisorCluster);
    }

    @Test
    void testDeleteSingleHypervisorClusterByName() {
        // Mocking data
        String name = "Mock TestHypervisorCluster1";

        // Perform the test
        hypervisorClusterService.deleteSingleHypervisorClusterByName(name);

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).deleteByName(name);
    }

    @Test
    void testDeleteMultipleHypervisorClustersByName() {
        // Mocking data
        List<String> names = Arrays.asList("Mock TestHypervisorCluster1", "Mock TestHypervisorCluster2");

        // Perform the test
        hypervisorClusterService.deleteMultipleHypervisorClustersByName(names);

        // Verify that the repository was called as expected
        verify(hypervisorClusterRepository, times(1)).deleteAllByNameIn(names);
    }
}
