package com.ooredoo.controllers;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.services.HypervisorClusterService;
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
class HypervisorClusterControllerImplTest {
    @Mock
    private HypervisorClusterService hypervisorClusterService;

    @InjectMocks
    private HypervisorClusterController hypervisorClusterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        Collection<HypervisorCluster> hypervisorClusters = Arrays.asList(new HypervisorCluster(), new HypervisorCluster());
        when(hypervisorClusterService.getAll()).thenReturn(hypervisorClusters);

        Collection<HypervisorCluster> result = hypervisorClusterController.getAll();

        assertEquals(hypervisorClusters, result);
        verify(hypervisorClusterService, times(1)).getAll();
    }

    @Test
    void testGetAllHypervisorClustersandHypervisors() {
        Collection<HypervisorCluster> hypervisorClusters = Arrays.asList(new HypervisorCluster(), new HypervisorCluster());
        when(hypervisorClusterService.getAllHypervisorClustersandHypervisors()).thenReturn(hypervisorClusters);

        Collection<HypervisorCluster> result = hypervisorClusterController.getAllHypervisorClustersandHypervisors();

        assertEquals(hypervisorClusters, result);
        verify(hypervisorClusterService, times(1)).getAllHypervisorClustersandHypervisors();
    }

    @Test
    void testGetAllHypervisorClusters() {
        Collection<HypervisorCluster> hypervisorClusters = Arrays.asList(new HypervisorCluster(), new HypervisorCluster());
        when(hypervisorClusterService.getAllHypervisorClusters()).thenReturn(hypervisorClusters);

        Collection<HypervisorCluster> result = hypervisorClusterController.getAllHypervisorClusters();

        assertEquals(hypervisorClusters, result);
        verify(hypervisorClusterService, times(1)).getAllHypervisorClusters();
    }

    @Test
    void testAddSingleHypervisorCluster() {
        HypervisorCluster hypervisorCluster = new HypervisorCluster();
        when(hypervisorClusterService.addSingleHypervisorCluster(hypervisorCluster)).thenReturn(hypervisorCluster);

        ResponseEntity<HypervisorCluster> response = hypervisorClusterController.addSingleHypervisorCluster(hypervisorCluster);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hypervisorCluster, response.getBody());
        verify(hypervisorClusterService, times(1)).addSingleHypervisorCluster(hypervisorCluster);
    }

    @Test
    void testAddMultipleHypervisorClusters() {
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(new HypervisorCluster(), new HypervisorCluster());
        when(hypervisorClusterService.addMultipleHypervisorClusters(hypervisorClusters)).thenReturn(hypervisorClusters);

        ResponseEntity<List<HypervisorCluster>> response = hypervisorClusterController.addMultipleHypervisorClusters(hypervisorClusters);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hypervisorClusters, response.getBody());
        verify(hypervisorClusterService, times(1)).addMultipleHypervisorClusters(hypervisorClusters);
    }

    @Test
    void testUpdateHypervisorClusterByName() {
        String name = "hypervisorClusterName";
        HypervisorCluster updatedHypervisorCluster = new HypervisorCluster();
        when(hypervisorClusterService.updateHypervisorClusterByName(name, updatedHypervisorCluster)).thenReturn(updatedHypervisorCluster);

        HypervisorCluster result = hypervisorClusterController.updateHypervisorClusterByName(name, updatedHypervisorCluster);

        assertEquals(updatedHypervisorCluster, result);
        verify(hypervisorClusterService, times(1)).updateHypervisorClusterByName(name, updatedHypervisorCluster);
    }

    @Test
    void testDeleteHypervisorClusterByName() {
        String name = "hypervisorClusterName";

        ResponseEntity<String> response = hypervisorClusterController.deleteHypervisorClusterByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("HypervisorCluster deleted successfully", response.getBody());
        verify(hypervisorClusterService, times(1)).deleteSingleHypervisorClusterByName(name);
    }

    @Test
    void testDeleteMultipleHypervisorClustersByName() {
        List<String> names = Arrays.asList("name1", "name2");

        ResponseEntity<String> response = hypervisorClusterController.deleteMultipleHypervisorClustersByName(names);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("HypervisorClusters deleted successfully", response.getBody());
        verify(hypervisorClusterService, times(1)).deleteMultipleHypervisorClustersByName(names);
    }
}
