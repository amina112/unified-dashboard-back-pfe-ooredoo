package com.ooredoo.controllers;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.services.VMService;
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
class VMControllerImplTest {

    @Mock
    private VMService vmService;

    @InjectMocks
    private VMController vmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllVMs() {
        Collection<VM> vms = Arrays.asList(new VM(), new VM());
        when(vmService.getAllVMs()).thenReturn(vms);

        Collection<VM> result = vmController.getAllVMs();

        assertEquals(vms, result);
        verify(vmService, times(1)).getAllVMs();
    }

    @Test
    void testGetVMsByHypervisorName() {
        String hypervisorName = "hypervisorName";
        List<VM> vms = Arrays.asList(new VM(), new VM());
        when(vmService.getVMsByHypervisorName(hypervisorName)).thenReturn(vms);

        List<VM> result = vmController.getVMsByHypervisorName(hypervisorName);

        assertEquals(vms, result);
        verify(vmService, times(1)).getVMsByHypervisorName(hypervisorName);
    }

    @Test
    void testGetVMsByDatastoreName() {
        String datastoreName = "datastoreName";
        List<VM> vms = Arrays.asList(new VM(), new VM());
        when(vmService.getVMsByDatastoreName(datastoreName)).thenReturn(vms);

        List<VM> result = vmController.getVMsByDatastoreName(datastoreName);

        assertEquals(vms, result);
        verify(vmService, times(1)).getVMsByDatastoreName(datastoreName);
    }

    @Test
    void testAddSingleVM() {
        VM vm = new VM();
        when(vmService.addSingleVM(vm)).thenReturn(vm);

        ResponseEntity<VM> response = vmController.addSingleVM(vm);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vm, response.getBody());
        verify(vmService, times(1)).addSingleVM(vm);
    }

    @Test
    void testAddMultipleVMs() {
        List<VM> vms = Arrays.asList(new VM(), new VM());
        when(vmService.addMultipleVMs(vms)).thenReturn(vms);

        ResponseEntity<List<VM>> response = vmController.addMultipleVMs(vms);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vms, response.getBody());
        verify(vmService, times(1)).addMultipleVMs(vms);
    }

    @Test
    void testUpdateVMByName() {
        String name = "vmName";
        VM updatedVM = new VM();
        when(vmService.updateVMByName(name, updatedVM)).thenReturn(updatedVM);

        VM result = vmController.updateVMByName(name, updatedVM);

        assertEquals(updatedVM, result);
        verify(vmService, times(1)).updateVMByName(name, updatedVM);
    }

    @Test
    void testDeleteVMByName() {
        String name = "vmName";

        ResponseEntity<String> response = vmController.deleteVMByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("VM deleted successfully", response.getBody());
        verify(vmService, times(1)).deleteSingleVMByName(name);
    }

    @Test
    void testDeleteMultipleVMsByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = vmController.deleteMultipleVMsByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("VMs deleted successfully", response.getBody());
        verify(vmService, times(1)).deleteMultipleVMsByName(names);
    }
}
