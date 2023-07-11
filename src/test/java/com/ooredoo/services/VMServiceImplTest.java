package com.ooredoo.services;

import com.ooredoo.entities.VM;

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

class VMServiceImplTest {

    @Mock
    private com.ooredoo.repositories.VMRepository VMRepository;

    @InjectMocks
    private VMService VMService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVMs() {
        // Mock the behavior of the VMRepository
        List<VM> mockVMs = Arrays.asList(
                new VM("Mock TestVM 1"),
                new VM("Mock TestVM 2")
        );
        when(VMRepository.getAllVMs()).thenReturn(mockVMs);

        // Call the method being tested
        Collection<VM> result = VMService.getAllVMs();

        // Verify the result
        assertEquals(mockVMs.size(), result.size());
        assertEquals(mockVMs, result);
    }

    @Test
    void testGetVMsByHypervisorName() {
        // Mock the behavior of the VMRepository
        String hypervisorName = "Mock TestHypervisor1";
        List<VM> mockVMs = Arrays.asList(
                new VM("Mock TestVM 1"),
                new VM("Mock TestVM 2")
        );
        when(VMRepository.findVMsByHypervisorName(hypervisorName)).thenReturn(mockVMs);

        // Call the method being tested
        List<VM> result = VMService.getVMsByHypervisorName(hypervisorName);

        // Verify the result
        assertEquals(mockVMs.size(), result.size());
        assertEquals(mockVMs, result);
    }

    @Test
    void testGetVMsByDatastoreName() {
        // Mock the behavior of the VMRepository
        String datastoreName = "Mock TestDatastore1";
        List<VM> mockVMs = Arrays.asList(
                new VM("Mock TestVM 1"),
                new VM("Mock TestVM 2")
        );
        when(VMRepository.findVMsByDatastoreName(datastoreName)).thenReturn(mockVMs);

        // Call the method being tested
        List<VM> result = VMService.getVMsByDatastoreName(datastoreName);

        // Verify the result
        assertEquals(mockVMs.size(), result.size());
        assertEquals(mockVMs, result);
    }

    @Test
    void testAddSingleVM() {
        // Mock the behavior of the VMRepository
        VM mockVM = new VM("Mock TestVM 1");
        when(VMRepository.save(mockVM)).thenReturn(mockVM);

        // Call the method being tested
        VM result = VMService.addSingleVM(mockVM);

        // Verify the result
        assertEquals(mockVM, result);
    }

    @Test
    void testAddMultipleVMs() {
        // Mock the behavior of the VMRepository
        List<VM> mockVMs = Arrays.asList(
                new VM("Mock TestVM 1"),
                new VM("Mock TestVM 2")
        );
        when(VMRepository.saveAll(mockVMs)).thenReturn(mockVMs);

        // Call the method being tested
        List<VM> result = VMService.addMultipleVMs(mockVMs);

        // Verify the result
        assertEquals(mockVMs.size(), result.size());
        assertEquals(mockVMs, result);
    }

    @Test
    void testUpdateVMByName() {
        // Mock the behavior of the VMRepository
        String name = "Mock TestVM 1";
        VM existingVM = new VM("Mock TestVM 1");
        VM updatedVM = new VM("Updated Mock TestVM 1");

        when(VMRepository.findByName(name)).thenReturn(existingVM);
        when(VMRepository.save(existingVM)).thenReturn(existingVM);

        // Call the method being tested
        VM result = VMService.updateVMByName(name, updatedVM);

        // Verify the result
        assertEquals(existingVM, result);
        assertEquals(updatedVM.getName(), result.getName());
    }

    @Test
    void testDeleteSingleVMByName() {
        // Call the method being tested
        String name = "Updated Mock TestVM 1";
        VMService.deleteSingleVMByName(name);

        // Verify that the delete method was called with the correct argument
        verify(VMRepository, times(1)).deleteByName(name);
    }

    @Test
    void testDeleteMultipleVMsByName() {
        // Call the method being tested
        List<String> names = Arrays.asList("Mock TestVM 2", "Mock TestVM 3");
        VMService.deleteMultipleVMsByName(names);

        // Verify that the deleteAllByNameIn method was called with the correct argument
        verify(VMRepository, times(1)).deleteAllByNameIn(names);
    }

}
