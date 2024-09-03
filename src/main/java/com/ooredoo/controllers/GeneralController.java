package com.ooredoo.controllers;

import com.ooredoo.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.neo4j.core.Neo4jTemplate;



import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/General")
public class GeneralController {

    final Neo4jTemplate neo4jTemplate;
    final com.ooredoo.services.GeneralService GeneralService;



    public GeneralController( GeneralService GeneralService, Neo4jTemplate neo4jTemplate) {
        this.GeneralService = GeneralService;
        this.neo4jTemplate = neo4jTemplate;
    }

// ############################## Relationships Update ########################################################

    //--------------------------------- Datacenter DatastoreCluster --------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-SingleDatastoreCluster
    @PostMapping("/createRelationshipDatacenter-SingleDatastoreCluster")
    public void createRelationshipBetweenDatacenterAndDatastoreCluster(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String DatastoreClusterName = request.get("DatastoreCluster");
        GeneralService.createRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-SingleDatastoreCluster
    @DeleteMapping("/deleteRelationshipDatacenter-SingleDatastoreCluster")
    public void deleteRelationshipBetweenDatacenterAndDatastoreCluster(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String DatastoreClusterName = request.get("DatastoreCluster");
        GeneralService.deleteRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-MultipleDatastoreClusters
    @PostMapping("/createRelationshipDatacenter-MultipleDatastoreClusters")
    public void createRelationshipBetweenDatacenterAndDatastoreClusters(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> DatastoreClusterNames = (List<String>) request.get("DatastoreClusters");
        for (String DatastoreClusterName : DatastoreClusterNames) {
            GeneralService.createRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-MultipleDatastoreClusters
    @DeleteMapping("/deleteRelationshipDatacenter-MultipleDatastoreClusters")
    public void deleteRelationshipBetweenDatacenterAndDatastoreClusters(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> DatastoreClusterNames = (List<String>) request.get("DatastoreClusters");
        for (String DatastoreClusterName : DatastoreClusterNames) {
            GeneralService.deleteRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Datacenter-DatastoreClusterList
    @PostMapping("/update-Datacenter-DatastoreClusterList")
    public ResponseEntity<String> updateOneDatacenterDatastoreClusterList() {
        GeneralService.linkDatacenterDatastoreClusters();
        GeneralService.updateRelationshipBetweenOneDatacenterAndDatastoreClusters(GeneralService.DatacenterDatastoreClusterList);
        return ResponseEntity.ok("Datacenter DatastoreCluster List updated successfully");
    }


    //--------------------------------- Datacenter Datastore --------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-SingleDatastore
    @PostMapping("/createRelationshipDatacenter-SingleDatastore")
    public void createRelationshipBetweenDatacenterAndDatastore(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String DatastoreName = request.get("Datastore");
        GeneralService.createRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-SingleDatastore
    @DeleteMapping("/deleteRelationshipDatacenter-SingleDatastore")
    public void deleteRelationshipBetweenDatacenterAndDatastore(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String DatastoreName = request.get("Datastore");
        GeneralService.deleteRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-MultipleDatastores
    @PostMapping("/createRelationshipDatacenter-MultipleDatastores")
    public void createRelationshipBetweenDatacenterAndDatastores(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> DatastoreNames = (List<String>) request.get("Datastores");
        for (String DatastoreName : DatastoreNames) {
            GeneralService.createRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-MultipleDatastores
    @DeleteMapping("/deleteRelationshipDatacenter-MultipleDatastores")
    public void deleteRelationshipBetweenDatacenterAndDatastores(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> DatastoreNames = (List<String>) request.get("Datastores");
        for (String DatastoreName : DatastoreNames) {
            GeneralService.deleteRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Datacenter-DatastoreList
    @PostMapping("/update-Datacenter-DatastoreList")
    public ResponseEntity<String> updateOneDatacenterDatastoreList() {
        GeneralService.linkDatacenterDatastores();
        GeneralService.updateRelationshipBetweenOneDatacenterAndDatastores(GeneralService.DatacenterDatastoreList);
        return ResponseEntity.ok("Datacenter Datastore List updated successfully");
    }


    //--------------------------------- DatastoreCluster Datastore --------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipDatastoreCluster-SingleDatastore
    @PostMapping("/createRelationshipDatastoreCluster-SingleDatastore")
    public void createRelationshipBetweenDatastoreClusterAndDatastore(@RequestBody Map<String, String> request) {
        String DatastoreClusterName = request.get("DatastoreCluster");
        String DatastoreName = request.get("Datastore");
        GeneralService.createRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatastoreCluster-SingleDatastore
    @DeleteMapping("/deleteRelationshipDatastoreCluster-SingleDatastore")
    public void deleteRelationshipBetweenDatastoreClusterAndDatastore(@RequestBody Map<String, String> request) {
        String DatastoreClusterName = request.get("DatastoreCluster");
        String DatastoreName = request.get("Datastore");
        GeneralService.deleteRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipDatastoreCluster-MultipleDatastores
    @PostMapping("/createRelationshipDatastoreCluster-MultipleDatastores")
    public void createRelationshipBetweenDatastoreClusterAndDatastores(@RequestBody Map<String, Object> request) {
        String DatastoreClusterName = (String) request.get("DatastoreCluster");
        List<String> DatastoreNames = (List<String>) request.get("Datastores");
        for (String DatastoreName : DatastoreNames) {
            GeneralService.createRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatastoreCluster-MultipleDatastores
    @DeleteMapping("/deleteRelationshipDatastoreCluster-MultipleDatastores")
    public void deleteRelationshipBetweenDatastoreClusterAndDatastores(@RequestBody Map<String, Object> request) {
        String DatastoreClusterName = (String) request.get("DatastoreCluster");
        List<String> DatastoreNames = (List<String>) request.get("Datastores");
        for (String DatastoreName : DatastoreNames) {
            GeneralService.deleteRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-DatastoreCluster-DatastoreList
    @PostMapping("/update-DatastoreCluster-DatastoreList")
    public ResponseEntity<String> updateOneDatastoreClusterDatastoreList() {
        GeneralService.linkDatastoreClusterDatastores();
        GeneralService.updateRelationshipBetweenOneDatastoreClusterAndDatastores(GeneralService.DatastoreClusterDatastoreList);
        return ResponseEntity.ok("DatastoreCluster Datastore List updated successfully");
    }


    //----------------------------- Datastore VM ----------------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipDatastore-SingleVM
    @PostMapping("/createRelationshipDatastore-SingleVM")
    public void createRelationshipBetweenDatastoreAndVM(@RequestBody Map<String, String> request) {
        String DatastoreName = request.get("Datastore");
        String vmName = request.get("VM");
        GeneralService.createRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatastore-SingleVM
    @DeleteMapping("/deleteRelationshipDatastore-SingleVM")
    public void deleteRelationshipBetweenDatastoreAndVM(@RequestBody Map<String, String> request) {
        String DatastoreName = request.get("Datastore");
        String vmName = request.get("VM");
        GeneralService.deleteRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipDatastore-MultipleVMs
    @PostMapping("/createRelationshipDatastore-MultipleVMs")
    public void createRelationshipBetweenDatastoreAndVMs(@RequestBody Map<String, Object> request) {
        String DatastoreName = (String) request.get("Datastore");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.createRelationshipBetweenDatacenterAndDatastore(DatastoreName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatastore-MultipleVMs
    @DeleteMapping("/deleteRelationshipDatastore-MultipleVMs")
    public void deleteRelationshipBetweenDatastoreAndVMs(@RequestBody Map<String, Object> request) {
        String DatastoreName = (String) request.get("Datastore");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.deleteRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Datastore-VMList
    @PostMapping("/update-Datastore-VMList")
    public ResponseEntity<String> updateOneDatastoreVMList() {
        GeneralService.linkDatastoreVMs();
        GeneralService.updateRelationshipBetweenOneDatastoreAndVMs(GeneralService.DatastoreVMList);
        return ResponseEntity.ok("Datastore VM List updated successfully");
    }

    //--------------------------------- Datacenter HypervisorCluster --------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-SingleHypervisorCluster
    @PostMapping("/createRelationshipDatacenter-SingleHypervisorCluster")
    public void createRelationshipBetweenDatacenterAndHypervisorCluster(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String HypervisorClusterName = request.get("HypervisorCluster");
        GeneralService.createRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-SingleHypervisorCluster
    @DeleteMapping("/deleteRelationshipDatacenter-SingleHypervisorCluster")
    public void deleteRelationshipBetweenDatacenterAndHypervisorCluster(@RequestBody Map<String, String> request) {
        String DatacenterName = request.get("Datacenter");
        String HypervisorClusterName = request.get("HypervisorCluster");
        GeneralService.deleteRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipDatacenter-MultipleHypervisorClusters
    @PostMapping("/createRelationshipDatacenter-MultipleHypervisorClusters")
    public void createRelationshipBetweenDatacenterAndHypervisorClusters(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> HypervisorClusterNames = (List<String>) request.get("HypervisorClusters");
        for (String HypervisorClusterName : HypervisorClusterNames) {
            GeneralService.createRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipDatacenter-MultipleHypervisorClusters
    @DeleteMapping("/deleteRelationshipDatacenter-MultipleHypervisorClusters")
    public void deleteRelationshipBetweenDatacenterAndHypervisorClusters(@RequestBody Map<String, Object> request) {
        String DatacenterName = (String) request.get("Datacenter");
        List<String> HypervisorClusterNames = (List<String>) request.get("HypervisorClusters");
        for (String HypervisorClusterName : HypervisorClusterNames) {
            GeneralService.deleteRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Datacenter-HypervisorClusterList
    @PostMapping("/update-Datacenter-HypervisorClusterList")
    public ResponseEntity<String> updateOneDatacenterHypervisorClusterList() {
        GeneralService.linkDatacenterHypervisorClusters();
        GeneralService.updateRelationshipBetweenOneDatacenterAndHypervisorClusters(GeneralService.DatacenterHypervisorClusterList);
        return ResponseEntity.ok("Datacenter HypervisorCluster List updated successfully");
    }


    //--------------------------------- HypervisorCluster Hypervisor --------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisorCluster-SingleHypervisor
    @PostMapping("/createRelationshipHypervisorCluster-SingleHypervisor")
    public void createRelationshipBetweenHypervisorClusterAndHypervisor(@RequestBody Map<String, String> request) {
        String HypervisorClusterName = request.get("HypervisorCluster");
        String HypervisorName = request.get("Hypervisor");
        GeneralService.createRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisorCluster-SingleHypervisor
    @DeleteMapping("/deleteRelationshipHypervisorCluster-SingleHypervisor")
    public void deleteRelationshipBetweenHypervisorClusterAndHypervisor(@RequestBody Map<String, String> request) {
        String HypervisorClusterName = request.get("HypervisorCluster");
        String HypervisorName = request.get("Hypervisor");
        GeneralService.deleteRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisorCluster-MultipleHypervisors
    @PostMapping("/createRelationshipHypervisorCluster-MultipleHypervisors")
    public void createRelationshipBetweenHypervisorClusterAndHypervisors(@RequestBody Map<String, Object> request) {
        String HypervisorClusterName = (String) request.get("HypervisorCluster");
        List<String> HypervisorNames = (List<String>) request.get("Hypervisors");
        for (String HypervisorName : HypervisorNames) {
            GeneralService.createRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisorCluster-MultipleHypervisors
    @DeleteMapping("/deleteRelationshipHypervisorCluster-MultipleHypervisors")
    public void deleteRelationshipBetweenHypervisorClusterAndHypervisors(@RequestBody Map<String, Object> request) {
        String HypervisorClusterName = (String) request.get("HypervisorCluster");
        List<String> HypervisorNames = (List<String>) request.get("Hypervisors");
        for (String HypervisorName : HypervisorNames) {
            GeneralService.deleteRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-HypervisorCluster-HypervisorList
    @PostMapping("/update-HypervisorCluster-HypervisorList")
    public ResponseEntity<String> updateOneHypervisorClusterHypervisorList() {
        GeneralService.linkHypervisorClusterHypervisors();
        GeneralService.updateRelationshipBetweenOneHypervisorClusterAndHypervisors(GeneralService.HypervisorClusterHypervisorList);
        return ResponseEntity.ok("HypervisorCluster Hypervisor List updated successfully");
    }

    //----------------------------- Hypervisor VM ----------------------------------------------

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisor-SingleVM
    @PostMapping("/createRelationshipHypervisor-SingleVM")
    public void createRelationshipBetweenHypervisorAndVM(@RequestBody Map<String, String> request) {
        String hypervisorName = request.get("Hypervisor");
        String vmName = request.get("VM");
        GeneralService.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisor-SingleVM
    @DeleteMapping("/deleteRelationshipHypervisor-SingleVM")
    public void deleteRelationshipBetweenHypervisorAndVM(@RequestBody Map<String, String> request) {
        String hypervisorName = request.get("Hypervisor");
        String vmName = request.get("VM");
        GeneralService.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }

    // http://localhost:8089/ooredoo/General/createRelationshipHypervisor-MultipleVMs
    @PostMapping("/createRelationshipHypervisor-MultipleVMs")
    public void createRelationshipBetweenHypervisorAndVMs(@RequestBody Map<String, Object> request) {
        String hypervisorName = (String) request.get("Hypervisor");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/deleteRelationshipHypervisor-MultipleVMs
    @DeleteMapping("/deleteRelationshipHypervisor-MultipleVMs")
    public void deleteRelationshipBetweenHypervisorAndVMs(@RequestBody Map<String, Object> request) {
        String hypervisorName = (String) request.get("Hypervisor");
        List<String> vmNames = (List<String>) request.get("VMs");
        for (String vmName : vmNames) {
            GeneralService.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
        }
    }

    // http://localhost:8089/ooredoo/General/update-Hypervisor-VMList
    @PostMapping("/update-Hypervisor-VMList")
    public ResponseEntity<String> updateOneHypervisorVMList() {
        GeneralService.linkHypervisorVMs();
        GeneralService.updateRelationshipBetweenOneHypervisorAndVMs(GeneralService.HypervisorVMList);
        return ResponseEntity.ok("Hypervisor VM List updated successfully");
    }


// ############################## Components Update ########################################################

    // http://localhost:8089/ooredoo/General/update-Datacenter-database
    @PostMapping("/update-Datacenter-database")
    public ResponseEntity<String> updateDatacenterDatabase() {
        GeneralService.addDatacenterToList();
        GeneralService.updateDatacenterDatabase(GeneralService.DatacentersList);
        return ResponseEntity.ok("Datacenter Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-DatastoreCluster-database
    @PostMapping("/update-DatastoreCluster-database")
    public ResponseEntity<String> updateDatastoreClusterDatabase() {
        GeneralService.addDatastoreClusterToList();
        GeneralService.updateDatastoreClusterDatabase(GeneralService.DatastoreClustersList);
        return ResponseEntity.ok("Datastore Cluster Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-Datastore-database
    @PostMapping("/update-Datastore-database")
    public ResponseEntity<String> updateDatastoreDatabase() {
        GeneralService.addDatastoreToList();
        GeneralService.updateDatastoreDatabase(GeneralService.DatastoresList);
        return ResponseEntity.ok("Datastore Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-HypervisorCluster-database
    @PostMapping("/update-HypervisorCluster-database")
    public ResponseEntity<String> updateHypervisorClusterDatabase() {
        GeneralService.addHypervisorClusterToList();
        GeneralService.updateHypervisorClusterDatabase(GeneralService.HypervisorClustersList);
        return ResponseEntity.ok("Hypervisor Cluster Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-Hypervisor-database
    @PostMapping("/update-Hypervisor-database")
    public ResponseEntity<String> updateHypervisorDatabase() {
        GeneralService.addHypervisorToList();
        GeneralService.updateHypervisorDatabase(GeneralService.HypervisorsList);
        return ResponseEntity.ok("Hypervisor Database updated successfully");
    }

    // http://localhost:8089/ooredoo/General/update-vm-database
    @PostMapping("/update-vm-database")
    public ResponseEntity<String> updateVMDatabase() {
        GeneralService.addVMToList();
        GeneralService.updateVMDatabase(GeneralService.VMsList);
        return ResponseEntity.ok("VM Database updated successfully");
    }



}