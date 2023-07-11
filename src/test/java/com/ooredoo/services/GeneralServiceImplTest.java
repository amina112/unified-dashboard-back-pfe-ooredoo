package com.ooredoo.services;

import com.ooredoo.entities.*;
import com.ooredoo.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.neo4j.driver.types.Relationship;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;

class GeneralServiceImplTest {
    @Mock
    private DatacenterRepository datacenterRepository;
    @Mock
    private DatastoreClusterRepository datastoreClusterRepository;
    @Mock
    private DatastoreRepository datastoreRepository;
    @Mock
    private HypervisorClusterRepository hypervisorClusterRepository;
    @Mock
    private HypervisorRepository hypervisorRepository;
    @Mock
    private DatacenterService datacenterService;
    @Mock
    private DatastoreClusterService datastoreClusterService;
    @Mock
    private DatastoreService datastoreService;
    @Mock
    private HypervisorClusterService hypervisorClusterService;
    @Mock
    private HypervisorService hypervisorService;
    @Mock
    private VMService vmService;

    @InjectMocks
    private GeneralService generalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ############################## Relationships Update ########################################################

    //-------------------- Update Datacenter DatastoreCluster  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenDatacenterAndDatastoreCluster() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String datastoreClusterName = "Test DatastoreCluster";

        when(datacenterRepository.createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);

        // Assert
        assertNotNull(relationship);
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);
    }
    @Test
    public void testDeleteRelationshipBetweenDatacenterAndDatastoreCluster() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String datastoreClusterName = "Test DatastoreCluster";

        // Act
        generalService.deleteRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);

        // Assert
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, datastoreClusterName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneDatacenterAndDatastoreClusters() {
        // Arrange
        String datacenterName = "Test Datacenter";
        List<String> datastoreClusterList = Arrays.asList("Test DatastoreCluster 1", "Test DatastoreCluster 2");
        Map<String, Object> datacenterDatastoreClusterList = new HashMap<>();
        datacenterDatastoreClusterList.put("Datacenter", datacenterName);
        datacenterDatastoreClusterList.put("DatastoreClusters", datastoreClusterList);

        List<DatastoreCluster> currentDatastoreClusters = Arrays.asList(
                new DatastoreCluster("Test DatastoreCluster 1"),
                new DatastoreCluster("Test DatastoreCluster 3")
        );

        when(datastoreClusterService.getDatastoreClustersByDatacenterName(datacenterName))
                .thenReturn(currentDatastoreClusters);

        // Act
        generalService.updateRelationshipBetweenOneDatacenterAndDatastoreClusters(datacenterDatastoreClusterList);

        // Assert
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, "Test DatastoreCluster 2");
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndDatastoreCluster(datacenterName, "Test DatastoreCluster 3");
    }

    //-------------------- Update Datacenter Datastore  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenDatacenterAndDatastore() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String datastoreName = "Test Datastore";

        when(datacenterRepository.createRelationshipBetweenDatacenterAndDatastore(datacenterName, datastoreName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenDatacenterAndDatastore(datacenterName, datastoreName);

        // Assert
        assertNotNull(relationship);
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndDatastore(datacenterName, datastoreName);
    }
    @Test
    public void testDeleteRelationshipBetweenDatacenterAndDatastore() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String datastoreName = "Test Datastore";

        // Act
        generalService.deleteRelationshipBetweenDatacenterAndDatastore(datacenterName, datastoreName);

        // Assert
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndDatastore(datacenterName, datastoreName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneDatacenterAndDatastores() {
        // Arrange
        String datacenterName = "DatacenterTest1";
        List<String> datastoreList = Arrays.asList("TestDatastore1", "TestDatastore2");
        Map<String, Object> datacenterDatastoreList = new HashMap<>();
        datacenterDatastoreList.put("Datacenter", datacenterName);
        datacenterDatastoreList.put("Datastores", datastoreList);

        List<Datastore> currentDatastores = Arrays.asList(
                new Datastore("TestDatastore1"),
                new Datastore("TestDatastore3")
        );

        when(datastoreService.getDatastoresByDatacenterName(datacenterName))
                .thenReturn(currentDatastores);

        // Act
        generalService.updateRelationshipBetweenOneDatacenterAndDatastores(datacenterDatastoreList);

        // Assert
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndDatastore(datacenterName, "TestDatastore2");
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndDatastore(datacenterName, "TestDatastore3");
    }

    //-------------------- Update DatastoreCluster Datastore  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenDatastoreClusterAndDatastore() {
        // Arrange
        String datastoreClusterName = "DatastoreClusterTest1";
        String datastoreName = "TestDatastore";

        when(datastoreClusterRepository.createRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, datastoreName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, datastoreName);

        // Assert
        assertNotNull(relationship);
        verify(datastoreClusterRepository, times(1)).createRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, datastoreName);
    }
    @Test
    public void testDeleteRelationshipBetweenDatastoreClusterAndDatastore() {
        // Arrange
        String datastoreClusterName = "DatastoreClusterTest1";
        String datastoreName = "TestDatastore";

        // Act
        generalService.deleteRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, datastoreName);

        // Assert
        verify(datastoreClusterRepository, times(1)).deleteRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, datastoreName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneDatastoreClusterAndDatastores() {
        // Arrange
        String datastoreClusterName = "DatastoreClusterTest1";
        List<String> datastoreList = Arrays.asList("TestDatastore1", "TestDatastore2");
        Map<String, Object> datastoreClusterDatastoreList = new HashMap<>();
        datastoreClusterDatastoreList.put("DatastoreCluster", datastoreClusterName);
        datastoreClusterDatastoreList.put("Datastores", datastoreList);

        List<Datastore> currentDatastores = Arrays.asList(
                new Datastore("TestDatastore1"),
                new Datastore("TestDatastore3")
        );

        when(datastoreService.getDatastoresByDatastoreClusterName(datastoreClusterName))
                .thenReturn(currentDatastores);

        // Act
        generalService.updateRelationshipBetweenOneDatastoreClusterAndDatastores(datastoreClusterDatastoreList);

        // Assert
        verify(datastoreClusterRepository, times(1)).createRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, "TestDatastore2");
        verify(datastoreClusterRepository, times(1)).deleteRelationshipBetweenDatastoreClusterAndDatastore(datastoreClusterName, "TestDatastore3");
    }

    //-------------------- Update Datastore VM  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenDatastoreAndVM() {
        // Arrange
        String datastoreName = "Test Datastore";
        String vmName = "Test VM";

        when(datastoreRepository.createRelationshipBetweenDatastoreAndVM(datastoreName, vmName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenDatastoreAndVM(datastoreName, vmName);

        // Assert
        assertNotNull(relationship);
        verify(datastoreRepository, times(1)).createRelationshipBetweenDatastoreAndVM(datastoreName, vmName);
    }
    @Test
    public void testDeleteRelationshipBetweenDatastoreAndVM() {
        // Arrange
        String datastoreName = "Test Datastore";
        String vmName = "Test VM";

        // Act
        generalService.deleteRelationshipBetweenDatastoreAndVM(datastoreName, vmName);

        // Assert
        verify(datastoreRepository, times(1)).deleteRelationshipBetweenDatastoreAndVM(datastoreName, vmName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneDatastoreAndVMs() {
        // Arrange
        String datastoreName = "Test Datastore";
        List<String> vmList = Arrays.asList("Test VM1", "Test VM2");
        Map<String, Object> datastoreVMList = new HashMap<>();
        datastoreVMList.put("Datastore", datastoreName);
        datastoreVMList.put("VMs", vmList);

        List<VM> currentVMs = Arrays.asList(
                new VM("Test VM1"),
                new VM("Test VM3")
        );

        when(vmService.getVMsByDatastoreName(datastoreName))
                .thenReturn(currentVMs);

        // Act
        generalService.updateRelationshipBetweenOneDatastoreAndVMs(datastoreVMList);

        // Assert
        verify(datastoreRepository, times(1)).createRelationshipBetweenDatastoreAndVM(datastoreName, "Test VM2");
        verify(datastoreRepository, times(1)).deleteRelationshipBetweenDatastoreAndVM(datastoreName, "Test VM3");
    }

    //-------------------- Update Datacenter HypervisorCluster  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenDatacenterAndHypervisorCluster() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String hypervisorClusterName = "Test HypervisorCluster";

        when(datacenterRepository.createRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, hypervisorClusterName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, hypervisorClusterName);

        // Assert
        assertNotNull(relationship);
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, hypervisorClusterName);
    }
    @Test
    public void testDeleteRelationshipBetweenDatacenterAndHypervisorCluster() {
        // Arrange
        String datacenterName = "Test Datacenter";
        String hypervisorClusterName = "Test HypervisorCluster";

        // Act
        generalService.deleteRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, hypervisorClusterName);

        // Assert
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, hypervisorClusterName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneDatacenterAndHypervisorClusters() {
        // Arrange
        String datacenterName = "DatacenterTest1";
        List<String> hypervisorClusterList = Arrays.asList("TestHypervisorCluster1", "TestHypervisorCluster2");
        Map<String, Object> datacenterHypervisorClusterList = new HashMap<>();
        datacenterHypervisorClusterList.put("Datacenter", datacenterName);
        datacenterHypervisorClusterList.put("HypervisorClusters", hypervisorClusterList);

        List<HypervisorCluster> currentHypervisorClusters = Arrays.asList(
                new HypervisorCluster("TestHypervisorCluster1"),
                new HypervisorCluster("TestHypervisorCluster3")
        );

        when(hypervisorClusterService.getHypervisorClustersByDatacenterName(datacenterName))
                .thenReturn(currentHypervisorClusters);

        // Act
        generalService.updateRelationshipBetweenOneDatacenterAndHypervisorClusters(datacenterHypervisorClusterList);

        // Assert
        verify(datacenterRepository, times(1)).createRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, "TestHypervisorCluster2");
        verify(datacenterRepository, times(1)).deleteRelationshipBetweenDatacenterAndHypervisorCluster(datacenterName, "TestHypervisorCluster3");
    }

    //-------------------- Update HypervisorCluster Hypervisor  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenHypervisorClusterAndHypervisor() {
        // Arrange
        String hypervisorClusterName = "HypervisorClusterTest1";
        String hypervisorName = "TestHypervisor1";

        when(hypervisorClusterRepository.createRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, hypervisorName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, hypervisorName);

        // Assert
        assertNotNull(relationship);
        verify(hypervisorClusterRepository, times(1)).createRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, hypervisorName);
    }
    @Test
    public void testDeleteRelationshipBetweenHypervisorClusterAndHypervisor() {
        // Arrange
        String hypervisorClusterName = "HypervisorClusterTest1";
        String hypervisorName = "TestHypervisor1";

        // Act
        generalService.deleteRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, hypervisorName);

        // Assert
        verify(hypervisorClusterRepository, times(1)).deleteRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, hypervisorName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneHypervisorClusterAndHypervisors() {
        // Arrange
        String hypervisorClusterName = "HypervisorClusterTest1";
        List<String> hypervisorList = Arrays.asList("TestHypervisor1", "TestHypervisor2");
        Map<String, Object> hypervisorClusterHypervisorList = new HashMap<>();
        hypervisorClusterHypervisorList.put("HypervisorCluster", hypervisorClusterName);
        hypervisorClusterHypervisorList.put("Hypervisors", hypervisorList);

        List<Hypervisor> currentHypervisors = Arrays.asList(
                new Hypervisor("TestHypervisor1"),
                new Hypervisor("TestHypervisor3")
        );

        when(hypervisorService.getHypervisorsByHypervisorClusterName(hypervisorClusterName))
                .thenReturn(currentHypervisors);

        // Act
        generalService.updateRelationshipBetweenOneHypervisorClusterAndHypervisors(hypervisorClusterHypervisorList);

        // Assert
        verify(hypervisorService, times(1)).getHypervisorsByHypervisorClusterName(hypervisorClusterName);
        verify(hypervisorClusterRepository, times(1)).createRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, "TestHypervisor2");
        verify(hypervisorClusterRepository, times(1)).deleteRelationshipBetweenHypervisorClusterAndHypervisor(hypervisorClusterName, "TestHypervisor3");
    }

    //-------------------- Update Hypervisor VM  relationship database ------------------
    @Test
    public void testCreateRelationshipBetweenHypervisorAndVM() {
        // Arrange
        String hypervisorName = "HypervisorTest1";
        String vmName = "TestVM1";

        when(hypervisorRepository.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName))
                .thenReturn(mock(Relationship.class));

        // Act
        Relationship relationship = generalService.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);

        // Assert
        assertNotNull(relationship);
        verify(hypervisorRepository, times(1)).createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    @Test
    public void testDeleteRelationshipBetweenHypervisorAndVM() {
        // Arrange
        String hypervisorName = "HypervisorTest1";
        String vmName = "TestVM1";

        // Act
        generalService.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);

        // Assert
        verify(hypervisorRepository, times(1)).deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    @Test
    public void testUpdateRelationshipBetweenOneHypervisorAndVMs() {
        // Arrange
        String hypervisorName = "HypervisorTest1";
        List<String> vmList = Arrays.asList("TestVM1", "TestVM2");
        Map<String, Object> hypervisorVMList = new HashMap<>();
        hypervisorVMList.put("Hypervisor", hypervisorName);
        hypervisorVMList.put("VMs", vmList);

        List<VM> currentVMs = Arrays.asList(
                new VM("TestVM1"),
                new VM("TestVM3")
        );

        when(vmService.getVMsByHypervisorName(hypervisorName))
                .thenReturn(currentVMs);

        // Act
        generalService.updateRelationshipBetweenOneHypervisorAndVMs(hypervisorVMList);

        // Assert
        verify(vmService, times(1)).getVMsByHypervisorName(hypervisorName);
        verify(hypervisorRepository, times(1)).createRelationshipBetweenHypervisorAndVM(hypervisorName, "TestVM2");
        verify(hypervisorRepository, times(1)).deleteRelationshipBetweenHypervisorAndVM(hypervisorName, "TestVM3");
    }

// ############################## Components Update ########################################################

    //-------------------- Update Datacenter database ------------------
    @Test
    public void testUpdateDatacenterDatabase_ExistingDatacenter() {
        // Arrange
        List<Datacenter> datacenters = Arrays.asList(
                new Datacenter("Test Datacenter", 4, 87, 19, 546)
        );

        Collection<Datacenter> existingDatacenters = Arrays.asList(
                new Datacenter("Test Datacenter", 4, 87, 19, 546)
        );

        when(datacenterService.getAllDatacenters())
                .thenReturn(existingDatacenters);

        // Act
        generalService.updateDatacenterDatabase(datacenters);

        // Assert
        verify(datacenterService, times(1)).updateDatacenterByName("Test Datacenter", datacenters.get(0));
        verify(datacenterService, never()).addMultipleDatacenters(Collections.singletonList(any(Datacenter.class)));
        verify(datacenterService, never()).deleteMultipleDatacentersByName(Arrays.asList("Test Datacenter"));
    }
    @Test
    public void testUpdateDatacenterDatabase_NewDatacenter() {
        // Arrange
        List<Datacenter> datacenters = Arrays.asList(
                new Datacenter("New Datacenter", 9, 120, 26, 377)
        );

        Collection<Datacenter> existingDatacenters = Arrays.asList(
                new Datacenter("Test Datacenter", 4, 87, 19, 546)
        );

        when(datacenterService.getAllDatacenters())
                .thenReturn(existingDatacenters);

        // Act
        generalService.updateDatacenterDatabase(datacenters);

        // Assert
        verify(datacenterService, never()).updateDatacenterByName(anyString(), any(Datacenter.class));
        verify(datacenterService, times(1)).addMultipleDatacenters(Collections.singletonList(datacenters.get(0)));
        verify(datacenterService, never()).deleteMultipleDatacentersByName(Arrays.asList("Test Datacenter", "New Datacenter"));
    }
    @Test
    public void testUpdateDatacenterDatabase_DeleteDatacenter() {
        // Arrange
        List<Datacenter> datacenters = Collections.emptyList();

        Collection<Datacenter> existingDatacenters = Arrays.asList(
                new Datacenter("Test Datacenter", 4, 87, 19, 546),
                new Datacenter("Datacenter", 0, 80, 11, 246)
        );

        when(datacenterService.getAllDatacenters())
                .thenReturn(existingDatacenters);

        // Act
        generalService.updateDatacenterDatabase(datacenters);

        // Assert
        verify(datacenterService, never()).updateDatacenterByName(anyString(), any(Datacenter.class));
        verify(datacenterService, never()).addMultipleDatacenters(Collections.singletonList(any(Datacenter.class)));
        verify(datacenterService, times(1)).deleteMultipleDatacentersByName(
                Arrays.asList("Test Datacenter", "Datacenter")
        );
    }

    //-------------------- Update DatastoreCluster database ------------------
    @Test
    public void testUpdateDatastoreClusterDatabase_ExistingDatastoreCluster() {
        // Arrange
        List<DatastoreCluster> datastoreClusters = Arrays.asList(
                new DatastoreCluster("Test DatastoreCluster", 7262.44, 8191.5)
        );

        Collection<DatastoreCluster> existingDatastoreClusters = Arrays.asList(
                new DatastoreCluster("Test DatastoreCluster", 7262.44, 8191.5)
        );

        when(datastoreClusterService.getAllDatastoreClusters())
                .thenReturn(existingDatastoreClusters);

        // Act
        generalService.updateDatastoreClusterDatabase(datastoreClusters);

        // Assert
        verify(datastoreClusterService, times(1))
                .updateDatastoreClusterByName("Test DatastoreCluster", datastoreClusters.get(0));
        verify(datastoreClusterService, never())
                .addMultipleDatastoreClusters(Collections.singletonList(any(DatastoreCluster.class)));
        verify(datastoreClusterService, never())
                .deleteMultipleDatastoreClustersByName(Arrays.asList("Test DatastoreCluster"));
    }
    @Test
    public void testUpdateDatastoreClusterDatabase_NewDatastoreCluster() {
        // Arrange
        List<DatastoreCluster> datastoreClusters = Arrays.asList(
                new DatastoreCluster("New DatastoreCluster", 8220.82, 12286.5)
        );

        Collection<DatastoreCluster> existingDatastoreClusters = Arrays.asList(
                new DatastoreCluster("Test DatastoreCluster", 7262.44, 8191.5)
        );

        when(datastoreClusterService.getAllDatastoreClusters())
                .thenReturn(existingDatastoreClusters);

        // Act
        generalService.updateDatastoreClusterDatabase(datastoreClusters);

        // Assert
        verify(datastoreClusterService, never())
                .updateDatastoreClusterByName(anyString(), any(DatastoreCluster.class));
        verify(datastoreClusterService, times(1))
                .addMultipleDatastoreClusters(Collections.singletonList(datastoreClusters.get(0)));
        verify(datastoreClusterService, never())
                .deleteMultipleDatastoreClustersByName(Arrays.asList("Test DatastoreCluster", "New DatastoreCluster"));
    }
    @Test
    public void testUpdateDatastoreClusterDatabase_DeleteDatastoreCluster() {
        // Arrange
        List<DatastoreCluster> datastoreClusters = Collections.emptyList();

        Collection<DatastoreCluster> existingDatastoreClusters = Arrays.asList(
                new DatastoreCluster("Test DatastoreCluster", 7262.44, 8191.5),
                new DatastoreCluster("DatastoreCluster",11, 246)
        );

        when(datastoreClusterService.getAllDatastoreClusters())
                .thenReturn(existingDatastoreClusters);

        // Act
        generalService.updateDatastoreClusterDatabase(datastoreClusters);

        // Assert
        verify(datastoreClusterService, never())
                .updateDatastoreClusterByName(anyString(), any(DatastoreCluster.class));
        verify(datastoreClusterService, never())
                .addMultipleDatastoreClusters(Collections.singletonList(any(DatastoreCluster.class)));
        verify(datastoreClusterService, times(1))
                .deleteMultipleDatastoreClustersByName(
                        Arrays.asList("Test DatastoreCluster", "DatastoreCluster")
                );
    }

    //-------------------- Update Datastore database ------------------
    @Test
    public void testUpdateDatastoreDatabase_ExistingDatastore() {
        // Arrange
        List<Datastore> datastores = Arrays.asList(
                new Datastore("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", "VMFS", 3399.68, 744.27, 0.0, 696.07, 0.0, 0.0, 0, 4095.75)
        );

        Collection<Datastore> existingDatastores = Arrays.asList(
                new Datastore("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", "VMFS", 3399.68, 744.27, 0.0, 696.07, 0.0, 0.0, 0, 4095.75)
        );

        when(datastoreService.getAllDatastores())
                .thenReturn(existingDatastores);

        // Act
        generalService.updateDatastoreDatabase(datastores);

        // Assert
        verify(datastoreService, times(1)).updateDatastoreByName("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", datastores.get(0));
        verify(datastoreService, never()).addMultipleDatastores(Collections.singletonList(any(Datastore.class)));
        verify(datastoreService, never()).deleteMultipleDatastoresByName(Arrays.asList("ExtP_Unify_ITAAS_U550F_LOCAL_DS09"));
    }
    @Test
    public void testUpdateDatastoreDatabase_NewDatastore() {
        // Arrange
        List<Datastore> datastores = Arrays.asList(
                new Datastore("New Datastore", "VMFS", 2000.0, 1000.0, 0.0, 500.0, 0.0, 0.0, 0, 4095.75)
        );

        Collection<Datastore> existingDatastores = Collections.emptyList();

        when(datastoreService.getAllDatastores())
                .thenReturn(existingDatastores);

        // Act
        generalService.updateDatastoreDatabase(datastores);

        // Assert
        verify(datastoreService, never()).updateDatastoreByName(anyString(), any(Datastore.class));
        verify(datastoreService, times(1)).addMultipleDatastores(Collections.singletonList(datastores.get(0)));
        verify(datastoreService, never()).deleteMultipleDatastoresByName(Arrays.asList("New Datastore"));
    }
    @Test
    public void testUpdateDatastoreDatabase_DeleteDatastore() {
        // Arrange
        List<Datastore> datastores = Collections.emptyList();

        Collection<Datastore> existingDatastores = Arrays.asList(
                new Datastore("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", "VMFS", 3399.68, 744.27, 0.0, 696.07, 0.0, 0.0, 0, 4095.75),
                new Datastore("Datastore", "VMFS", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0)
        );

        when(datastoreService.getAllDatastores())
                .thenReturn(existingDatastores);

        // Act
        generalService.updateDatastoreDatabase(datastores);

        // Assert
        verify(datastoreService, never()).updateDatastoreByName(anyString(), any(Datastore.class));
        verify(datastoreService, never()).addMultipleDatastores(Collections.singletonList(any(Datastore.class)));
        verify(datastoreService, times(1)).deleteMultipleDatastoresByName(Arrays.asList("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", "Datastore"));
    }

    //-------------------- Update HypervisorCluster database ------------------
    @Test
    public void testUpdateHypervisorClusterDatabase_ExistingHypervisorCluster() {
        // Arrange
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(
                new HypervisorCluster("Test Hypervisor-Cluster", 0, 10, 757, 6653)
        );

        Collection<HypervisorCluster> existingHypervisorClusters = Arrays.asList(
                new HypervisorCluster("Test Hypervisor-Cluster", 0, 10, 757, 6653)
        );

        when(hypervisorClusterService.getAllHypervisorClusters())
                .thenReturn(existingHypervisorClusters);

        // Act
        generalService.updateHypervisorClusterDatabase(hypervisorClusters);

        // Assert
        verify(hypervisorClusterService, times(1))
                .updateHypervisorClusterByName("Test Hypervisor-Cluster", hypervisorClusters.get(0));
        verify(hypervisorClusterService, never())
                .addMultipleHypervisorClusters(Collections.singletonList(any(HypervisorCluster.class)));
        verify(hypervisorClusterService, never())
                .deleteMultipleHypervisorClustersByName(Arrays.asList("Test Hypervisor-Cluster"));
    }
    @Test
    public void testUpdateHypervisorClusterDatabase_NewHypervisorCluster() {
        // Arrange
        List<HypervisorCluster> hypervisorClusters = Arrays.asList(
                new HypervisorCluster("New Hypervisor-Cluster", 0, 10, 757, 6653)
        );

        Collection<HypervisorCluster> existingHypervisorClusters = Arrays.asList(
                new HypervisorCluster("Test Hypervisor-Cluster", 0, 10, 757, 6653)
        );

        when(hypervisorClusterService.getAllHypervisorClusters())
                .thenReturn(existingHypervisorClusters);

        // Act
        generalService.updateHypervisorClusterDatabase(hypervisorClusters);

        // Assert
        verify(hypervisorClusterService, never())
                .updateHypervisorClusterByName(anyString(), any(HypervisorCluster.class));
        verify(hypervisorClusterService, times(1))
                .addMultipleHypervisorClusters(Collections.singletonList(hypervisorClusters.get(0)));
        verify(hypervisorClusterService, never())
                .deleteMultipleHypervisorClustersByName(Arrays.asList("Test Hypervisor-Cluster", "New Hypervisor-Cluster"));
    }
    @Test
    public void testUpdateHypervisorClusterDatabase_DeleteHypervisorCluster() {
        // Arrange
        List<HypervisorCluster> hypervisorClusters = Collections.emptyList();

        Collection<HypervisorCluster> existingHypervisorClusters = Arrays.asList(
                new HypervisorCluster("Test Hypervisor-Cluster", 0, 10, 757, 6653),
                new HypervisorCluster("Hypervisor-Cluster", 0, 80, 11, 246)
        );

        when(hypervisorClusterService.getAllHypervisorClusters())
                .thenReturn(existingHypervisorClusters);

        // Act
        generalService.updateHypervisorClusterDatabase(hypervisorClusters);

        // Assert
        verify(hypervisorClusterService, never())
                .updateHypervisorClusterByName(anyString(), any(HypervisorCluster.class));
        verify(hypervisorClusterService, never())
                .addMultipleHypervisorClusters(Collections.singletonList(any(HypervisorCluster.class)));
        verify(hypervisorClusterService, times(1))
                .deleteMultipleHypervisorClustersByName(
                        Arrays.asList("Test Hypervisor-Cluster", "Hypervisor-Cluster")
                );
    }

    //-------------------- Update Hypervisor database ------------------
    @Test
    public void testUpdateHypervisorDatabase_ExistingHypervisor() {
        // Arrange
        List<Hypervisor> hypervisors = Arrays.asList(
                new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353")
        );

        Collection<Hypervisor> existingHypervisors = Arrays.asList(
                new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353")
        );

        when(hypervisorService.getAllHypervisors())
                .thenReturn(existingHypervisors);

        // Act
        generalService.updateHypervisorDatabase(hypervisors);

        // Assert
        verify(hypervisorService, times(1))
                .updateHypervisorByName("HypervisorTest", hypervisors.get(0));
        verify(hypervisorService, never())
                .addMultipleHypervisors(Collections.singletonList(any(Hypervisor.class)));
        verify(hypervisorService, never())
                .deleteMultipleHypervisorsByName(Arrays.asList("HypervisorTest"));
    }
    @Test
    public void testUpdateHypervisorDatabase_NewHypervisor() {
        // Arrange
        List<Hypervisor> hypervisors = Arrays.asList(
                new Hypervisor("New Hypervisor", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353")
        );

        Collection<Hypervisor> existingHypervisors = Arrays.asList(
                new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353")
        );

        when(hypervisorService.getAllHypervisors())
                .thenReturn(existingHypervisors);

        // Act
        generalService.updateHypervisorDatabase(hypervisors);

        // Assert
        verify(hypervisorService, never())
                .updateHypervisorByName(anyString(), any(Hypervisor.class));
        verify(hypervisorService, times(1))
                .addMultipleHypervisors(Collections.singletonList(hypervisors.get(0)));
        verify(hypervisorService, never())
                .deleteMultipleHypervisorsByName(Arrays.asList("HypervisorTest", "New Hypervisor"));
    }
    @Test
    public void testUpdateHypervisorDatabase_DeleteHypervisor() {
        // Arrange
        List<Hypervisor> hypervisors = Collections.emptyList();

        Collection<Hypervisor> existingHypervisors = Arrays.asList(
                new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353"),
                new Hypervisor("Hypervisor", 45.14, 254.57, 88.26, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589")
        );

        when(hypervisorService.getAllHypervisors()).thenReturn(existingHypervisors);

        // Act
        generalService.updateHypervisorDatabase(hypervisors);

        // Assert
        verify(hypervisorService, never()).updateHypervisorByName(anyString(), any(Hypervisor.class));
        verify(hypervisorService, never()).addMultipleHypervisors(Collections.singletonList(any(Hypervisor.class)));
        verify(hypervisorService, times(1)).deleteMultipleHypervisorsByName(Arrays.asList("HypervisorTest", "Hypervisor"));
    }

    //-------------------- Update VM database ------------------
    @Test
    public void testUpdateVMDatabase_ExistingVM() {
        // Arrange
        List<VM> vms = Arrays.asList(
                new VM("Test VM", 4, "Powered On", 0.8, "Ubuntu 20.04", "192.168.1.100", "Resources", new ArrayList<>(), 80, 40, 8192, "Normal", 200, 0.7, 50, 30, 0.4, 100)
        );

        Collection<VM> existingVMs = Arrays.asList(
                new VM("Test VM", 2, "Powered On", 0.6, "Ubuntu 18.04", "192.168.1.200", "Resources", new ArrayList<>(), 80, 20, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100)
        );

        when(vmService.getAllVMs())
                .thenReturn(existingVMs);

        // Act
        generalService.updateVMDatabase(vms);

        // Assert
        verify(vmService, times(1))
                .updateVMByName("Test VM", vms.get(0));
        verify(vmService, never())
                .addMultipleVMs(Collections.singletonList(any(VM.class)));
        verify(vmService, never())
                .deleteMultipleVMsByName(Arrays.asList("Test VM"));
    }
    @Test
    public void testUpdateVMDatabase_NewVM() {
        // Arrange
        List<VM> vms = Arrays.asList(
                new VM("New VM", 8, "Powered On", 1.2, "Windows Server 2019", "192.168.1.150", "Resources", new ArrayList<>(), 80, 60, 16384, "Normal", 200, 0.7, 50, 30, 0.4, 100)
        );

        Collection<VM> existingVMs = Arrays.asList(
                new VM("Test VM", 2, "Powered On", 0.6, "Ubuntu 18.04", "192.168.1.200", "Resources", new ArrayList<>(), 80, 20, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100)
        );

        when(vmService.getAllVMs())
                .thenReturn(existingVMs);

        // Act
        generalService.updateVMDatabase(vms);

        // Assert
        verify(vmService, never())
                .updateVMByName(anyString(), any(VM.class));
        verify(vmService, times(1))
                .addMultipleVMs(Collections.singletonList(vms.get(0)));
        verify(vmService, never())
                .deleteMultipleVMsByName(Arrays.asList("New VM"));
    }
    @Test
    public void testUpdateVMDatabase_DeleteVM() {
        // Arrange
        List<VM> vms = Collections.emptyList();

        Collection<VM> existingVMs = Arrays.asList(
                new VM("TestVM", 2, "Powered On", 0.6, "Ubuntu 18.04", "192.168.1.200", "Resources", new ArrayList<>(), 80, 20, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100),
                new VM("OldVM", 4, "Powered On", 0.8, "Ubuntu 20.04", "192.168.1.100", "Resources", new ArrayList<>(), 80, 40, 8192, "Normal", 200, 0.7, 50, 30, 0.4, 100)
        );

        when(vmService.getAllVMs())
                .thenReturn(existingVMs);

        // Act
        generalService.updateVMDatabase(vms);

        // Assert
        verify(vmService, never()).updateVMByName(anyString(), any(VM.class));
        verify(vmService, never()).addMultipleVMs(Collections.singletonList(any(VM.class)));
        verify(vmService, times(1)).deleteMultipleVMsByName(Arrays.asList("TestVM", "OldVM"));
    }
}