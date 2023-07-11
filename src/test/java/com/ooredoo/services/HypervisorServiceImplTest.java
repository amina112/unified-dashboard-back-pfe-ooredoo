package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HypervisorServiceImplTest {

    @Mock
    private HypervisorRepository hypervisorRepository;

    @Mock
    private VMService vmService;

    private HypervisorService hypervisorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hypervisorService = new HypervisorService(hypervisorRepository, vmService);
    }

    @Test
    void testGetAll() {
        // Prepare test data
        Hypervisor hypervisor1 = new Hypervisor("Mock TestHypervisor 1");
        Hypervisor hypervisor2 = new Hypervisor("Mock TestHypervisor 2");
        List<Hypervisor> hypervisors = Arrays.asList(hypervisor1, hypervisor2);
        when(hypervisorRepository.getAll()).thenReturn(hypervisors);
        when(vmService.getVMsByHypervisorName("Mock TestHypervisor 1")).thenReturn(Arrays.asList(new VM("Mock TestVM 1")));
        when(vmService.getVMsByHypervisorName("Mock TestHypervisor 2")).thenReturn(Arrays.asList(new VM("Mock TestVM 2")));

        // Perform the service call
        Collection<Hypervisor> result = hypervisorService.getAll();

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).getAll();
        verify(vmService, times(1)).getVMsByHypervisorName("Mock TestHypervisor 1");
        verify(vmService, times(1)).getVMsByHypervisorName("Mock TestHypervisor 2");
        assertEquals(2, result.size());
        assertEquals("Mock TestHypervisor 1", result.iterator().next().getName());
        assertEquals(1, result.iterator().next().getVMS().size());
    }

    @Test
    void testGetAllHypervisors() {
        // Prepare test data
        Hypervisor hypervisor1 = new Hypervisor("Mock TestHypervisor 1");
        Hypervisor hypervisor2 = new Hypervisor("Mock TestHypervisor 2");
        List<Hypervisor> hypervisors = Arrays.asList(hypervisor1, hypervisor2);
        when(hypervisorRepository.getAllHypervisors()).thenReturn(hypervisors);

        // Perform the service call
        Collection<Hypervisor> result = hypervisorService.getAllHypervisors();

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).getAllHypervisors();
        assertEquals(2, result.size());
        assertEquals("Mock TestHypervisor 1", result.iterator().next().getName());
    }

    @Test
    void testGetHypervisorsByHypervisorClusterName() {
        // Prepare test data
        String hypervisorClusterName = "Mock TestCluster 1";
        List<Hypervisor> hypervisors = Arrays.asList(new Hypervisor("Mock TestHypervisor 1"), new Hypervisor("Mock TestHypervisor 2"));
        when(hypervisorRepository.findHypervisorsByHypervisorClusterName(hypervisorClusterName)).thenReturn(hypervisors);

        // Perform the service call
        List<Hypervisor> result = hypervisorService.getHypervisorsByHypervisorClusterName(hypervisorClusterName);

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).findHypervisorsByHypervisorClusterName(hypervisorClusterName);
        assertEquals(2, result.size());
        assertEquals("Mock TestHypervisor 1", result.get(0).getName());
    }

    @Test
    void testAddSingleHypervisor() {
        // Prepare test data
        Hypervisor hypervisor = new Hypervisor("Mock TestHypervisor 1");
        when(hypervisorRepository.save(hypervisor)).thenReturn(hypervisor);

        // Perform the service call
        Hypervisor result = hypervisorService.addSingleHypervisor(hypervisor);

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).save(hypervisor);
        assertEquals("Mock TestHypervisor 1", result.getName());
    }

    @Test
    void testAddMultipleHypervisors() {
        // Prepare test data
        List<Hypervisor> hypervisors = Arrays.asList(new Hypervisor("Mock TestHypervisor 1"), new Hypervisor("Mock TestHypervisor 2"));
        when(hypervisorRepository.saveAll(hypervisors)).thenReturn(hypervisors);

        // Perform the service call
        List<Hypervisor> result = hypervisorService.addMultipleHypervisors(hypervisors);

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).saveAll(hypervisors);
        assertEquals(2, result.size());
        assertEquals("Mock TestHypervisor 1", result.get(0).getName());
    }

    @Test
    void testUpdateHypervisorByName() {
        // Prepare test data
        Hypervisor existingHypervisor = new Hypervisor("Mock TestHypervisor 1");
        Hypervisor updatedHypervisor = new Hypervisor("Updated Mock TestHypervisor");
        when(hypervisorRepository.findByName("Mock TestHypervisor 1")).thenReturn(existingHypervisor);
        when(hypervisorRepository.save(existingHypervisor)).thenReturn(existingHypervisor);

        // Perform the service call
        Hypervisor result = hypervisorService.updateHypervisorByName("Mock TestHypervisor 1", updatedHypervisor);

        // Verify the interactions and the result
        verify(hypervisorRepository, times(1)).findByName("Mock TestHypervisor 1");
        verify(hypervisorRepository, times(1)).save(existingHypervisor);
        assertEquals("Updated Mock TestHypervisor", result.getName());
    }

    @Test
    void testDeleteSingleHypervisorByName() {
        // Perform the service call
        hypervisorService.deleteSingleHypervisorByName("Mock TestHypervisor 1");

        // Verify the interactions
        verify(hypervisorRepository, times(1)).deleteByName("Mock TestHypervisor 1");
    }

    @Test
    void testDeleteMultipleHypervisorsByName() {
        // Prepare test data
        List<String> names = Arrays.asList("Mock TestHypervisor 1", "Mock TestHypervisor 2");

        // Perform the service call
        hypervisorService.deleteMultipleHypervisorsByName(names);

        // Verify the interactions
        verify(hypervisorRepository, times(1)).deleteAllByNameIn(names);
    }
}
