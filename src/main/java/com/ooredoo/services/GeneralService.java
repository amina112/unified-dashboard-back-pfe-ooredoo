package com.ooredoo.services;

import com.ooredoo.entities.*;
import com.ooredoo.repositories.*;
import org.neo4j.driver.types.Relationship;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeneralService {

    private final DatacenterRepository DatacenterRepository;
    private final DatastoreClusterRepository DatastoreClusterRepository;
    private final DatastoreRepository DatastoreRepository;
    private final HypervisorClusterRepository HypervisorClusterRepository;
    private final HypervisorRepository HypervisorRepository;

    private final DatacenterService DatacenterService;
    private final DatastoreClusterService DatastoreClusterService;
    private final DatastoreService DatastoreService;
    private final HypervisorClusterService HypervisorClusterService;
    private final HypervisorService HypervisorService;
    private final VMService VMService;


    public GeneralService(DatacenterRepository DatacenterRepository,DatastoreClusterRepository DatastoreClusterRepository,DatastoreRepository DatastoreRepository, HypervisorClusterRepository HypervisorClusterRepository, HypervisorRepository HypervisorRepository, DatacenterService DatacenterService, DatastoreClusterService DatastoreClusterService, HypervisorClusterService HypervisorClusterService, VMService VMService, HypervisorService HypervisorService, DatastoreService DatastoreService) {

        this.DatacenterRepository = DatacenterRepository;
        this.DatastoreClusterRepository = DatastoreClusterRepository;
        this.DatastoreRepository = DatastoreRepository;
        this.HypervisorRepository=HypervisorRepository;
        this.HypervisorClusterRepository=HypervisorClusterRepository;

        this.DatacenterService = DatacenterService;
        this.DatastoreClusterService = DatastoreClusterService;
        this.DatastoreService = DatastoreService;
        this.HypervisorClusterService = HypervisorClusterService;
        this.HypervisorService = HypervisorService;
        this.VMService  = VMService;

    }

// ############################## Relationships Update ########################################################

    //-------------------- Update Datacenter DatastoreCluster  relationship database ------------------
    public Relationship createRelationshipBetweenDatacenterAndDatastoreCluster(String DatacenterName, String DatastoreClusterName) {
        return DatacenterRepository.createRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
    }
    public void deleteRelationshipBetweenDatacenterAndDatastoreCluster(String DatacenterName, String DatastoreClusterName) {
        DatacenterRepository.deleteRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);
    }

    public Map<String, Object> DatacenterDatastoreClusterList = new HashMap<>();
    public void linkDatacenterDatastoreClusters(){
        DatacenterDatastoreClusterList.put("Datacenter", "Test Datacenter");
        DatacenterDatastoreClusterList.put("DatastoreClusters", Arrays.asList("Test DatastoreCluster 1", "Test DatastoreCluster"));
    }
    public void updateRelationshipBetweenOneDatacenterAndDatastoreClusters(Map<String, Object> DatacenterDatastoreClusterList) {
        String DatacenterName = (String) DatacenterDatastoreClusterList.get("Datacenter");
        List<String> DatastoreClusterList = (List<String>) DatacenterDatastoreClusterList.get("DatastoreClusters");

        List<DatastoreCluster> currentDatastoreClusters = DatastoreClusterService.getDatastoreClustersByDatacenterName(DatacenterName);// Get the current DatastoreClusters associated with the Datacenter

        for (String DatastoreClusterName : DatastoreClusterList) // Create relationships for new DatastoreClusters
            if (!currentDatastoreClusters.stream().anyMatch(DatastoreCluster -> DatastoreCluster.getName().equals(DatastoreClusterName))) createRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreClusterName);

        for (DatastoreCluster DatastoreCluster : currentDatastoreClusters) // Delete relationships for DatastoreClusters not in the list
            if (!DatastoreClusterList.contains(DatastoreCluster.getName())) deleteRelationshipBetweenDatacenterAndDatastoreCluster(DatacenterName, DatastoreCluster.getName());

    }


    //-------------------- Update Datacenter Datastore  relationship database ------------------
    public Relationship createRelationshipBetweenDatacenterAndDatastore(String DatacenterName, String DatastoreName) {
        return DatacenterRepository.createRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
    }
    public void deleteRelationshipBetweenDatacenterAndDatastore(String DatacenterName, String DatastoreName) {
        DatacenterRepository.deleteRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);
    }

    public Map<String, Object> DatacenterDatastoreList = new HashMap<>();
    public void linkDatacenterDatastores(){
        DatacenterDatastoreList.put("Datacenter", "DatacenterTest1");
        DatacenterDatastoreList.put("Datastores", Arrays.asList("TestDatastore1", "TestDatastore"));
    }
    public void updateRelationshipBetweenOneDatacenterAndDatastores(Map<String, Object> DatacenterDatastoreList) {
        String DatacenterName = (String) DatacenterDatastoreList.get("Datacenter");
        List<String> DatastoreList = (List<String>) DatacenterDatastoreList.get("Datastores");

        List<Datastore> currentDatastores = DatastoreService.getDatastoresByDatacenterName(DatacenterName);// Get the current Datastores associated with the Datacenter

        for (String DatastoreName : DatastoreList) // Create relationships for new Datastores
            if (!currentDatastores.stream().anyMatch(Datastore -> Datastore.getName().equals(DatastoreName))) createRelationshipBetweenDatacenterAndDatastore(DatacenterName, DatastoreName);

        for (Datastore Datastore : currentDatastores) // Delete relationships for Datastores not in the list
            if (!DatastoreList.contains(Datastore.getName())) deleteRelationshipBetweenDatacenterAndDatastore(DatacenterName, Datastore.getName());

    }


    //-------------------- Update DatastoreCluster Datastore  relationship database ------------------
    public Relationship createRelationshipBetweenDatastoreClusterAndDatastore(String DatastoreClusterName, String DatastoreName) {
        return DatastoreClusterRepository.createRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
    }
    public void deleteRelationshipBetweenDatastoreClusterAndDatastore(String DatastoreClusterName, String DatastoreName) {
        DatastoreClusterRepository.deleteRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);
    }

    public Map<String, Object> DatastoreClusterDatastoreList = new HashMap<>();
    public void linkDatastoreClusterDatastores(){
        DatastoreClusterDatastoreList.put("DatastoreCluster", "DatastoreClusterTest1");
        DatastoreClusterDatastoreList.put("Datastores", Arrays.asList("TestDatastore1", "TestDatastore"));
    }
    public void updateRelationshipBetweenOneDatastoreClusterAndDatastores(Map<String, Object> DatastoreClusterDatastoreList) {
        String DatastoreClusterName = (String) DatastoreClusterDatastoreList.get("DatastoreCluster");
        List<String> DatastoreList = (List<String>) DatastoreClusterDatastoreList.get("Datastores");

        List<Datastore> currentDatastores = DatastoreService.getDatastoresByDatastoreClusterName(DatastoreClusterName);// Get the current Datastores associated with the DatastoreCluster

        for (String DatastoreName : DatastoreList) // Create relationships for new Datastores
            if (!currentDatastores.stream().anyMatch(Datastore -> Datastore.getName().equals(DatastoreName))) createRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, DatastoreName);

        for (Datastore Datastore : currentDatastores) // Delete relationships for Datastores not in the list
            if (!DatastoreList.contains(Datastore.getName())) deleteRelationshipBetweenDatastoreClusterAndDatastore(DatastoreClusterName, Datastore.getName());

    }


    //-------------------- Update Datastore VM  relationship database ------------------
    public Relationship createRelationshipBetweenDatastoreAndVM(String DatastoreName, String vmName) {
        return DatastoreRepository.createRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);
    }
    public void deleteRelationshipBetweenDatastoreAndVM(String DatastoreName, String vmName) {
        DatastoreRepository.deleteRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);
    }

    public Map<String, Object> DatastoreVMList = new HashMap<>();
    public void linkDatastoreVMs(){
        DatastoreVMList.put("Datastore", "DatastoreTest1");
        DatastoreVMList.put("VMs", Arrays.asList("TestVM1", "TestVM"));
    }
    public void updateRelationshipBetweenOneDatastoreAndVMs(Map<String, Object> DatastoreVMList) {
        String DatastoreName = (String) DatastoreVMList.get("Datastore");
        List<String> vmList = (List<String>) DatastoreVMList.get("VMs");

        List<VM> currentVMs = VMService.getVMsByDatastoreName(DatastoreName);// Get the current VMs associated with the Datastore

        for (String vmName : vmList) // Create relationships for new VMs
            if (!currentVMs.stream().anyMatch(vm -> vm.getName().equals(vmName))) createRelationshipBetweenDatastoreAndVM(DatastoreName, vmName);

        for (VM vm : currentVMs) // Delete relationships for VMs not in the list
            if (!vmList.contains(vm.getName())) deleteRelationshipBetweenDatastoreAndVM(DatastoreName, vm.getName());

    }


    //-------------------- Update Datacenter HypervisorCluster  relationship database ------------------
    public Relationship createRelationshipBetweenDatacenterAndHypervisorCluster(String DatacenterName, String HypervisorClusterName) {
        return DatacenterRepository.createRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
    }
    public void deleteRelationshipBetweenDatacenterAndHypervisorCluster(String DatacenterName, String HypervisorClusterName) {
        DatacenterRepository.deleteRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);
    }

    public Map<String, Object> DatacenterHypervisorClusterList = new HashMap<>();
    public void linkDatacenterHypervisorClusters(){
        DatacenterHypervisorClusterList.put("Datacenter", "DatacenterTest1");
        DatacenterHypervisorClusterList.put("HypervisorClusters", Arrays.asList("TestHypervisorCluster1", "TestHypervisorCluster"));
    }
    public void updateRelationshipBetweenOneDatacenterAndHypervisorClusters(Map<String, Object> DatacenterHypervisorClusterList) {
        String DatacenterName = (String) DatacenterHypervisorClusterList.get("Datacenter");
        List<String> HypervisorClusterList = (List<String>) DatacenterHypervisorClusterList.get("HypervisorClusters");

        List<HypervisorCluster> currentHypervisorClusters = HypervisorClusterService.getHypervisorClustersByDatacenterName(DatacenterName);// Get the current HypervisorClusters associated with the Datacenter

        for (String HypervisorClusterName : HypervisorClusterList) // Create relationships for new HypervisorClusters
            if (!currentHypervisorClusters.stream().anyMatch(HypervisorCluster -> HypervisorCluster.getName().equals(HypervisorClusterName))) createRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorClusterName);

        for (HypervisorCluster HypervisorCluster : currentHypervisorClusters) // Delete relationships for HypervisorClusters not in the list
            if (!HypervisorClusterList.contains(HypervisorCluster.getName())) deleteRelationshipBetweenDatacenterAndHypervisorCluster(DatacenterName, HypervisorCluster.getName());

    }


    //-------------------- Update HypervisorCluster Hypervisor  relationship database ------------------
    public Relationship createRelationshipBetweenHypervisorClusterAndHypervisor(String HypervisorClusterName, String HypervisorName) {
        return HypervisorClusterRepository.createRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
    }
    public void deleteRelationshipBetweenHypervisorClusterAndHypervisor(String HypervisorClusterName, String HypervisorName) {
        HypervisorClusterRepository.deleteRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);
    }

    public Map<String, Object> HypervisorClusterHypervisorList = new HashMap<>();
    public void linkHypervisorClusterHypervisors(){
        HypervisorClusterHypervisorList.put("HypervisorCluster", "HypervisorClusterTest1");
        HypervisorClusterHypervisorList.put("Hypervisors", Arrays.asList("TestHypervisor1", "TestHypervisor"));
    }
    public void updateRelationshipBetweenOneHypervisorClusterAndHypervisors(Map<String, Object> HypervisorClusterHypervisorList) {
        String HypervisorClusterName = (String) HypervisorClusterHypervisorList.get("HypervisorCluster");
        List<String> HypervisorList = (List<String>) HypervisorClusterHypervisorList.get("Hypervisors");

        List<Hypervisor> currentHypervisors = HypervisorService.getHypervisorsByHypervisorClusterName(HypervisorClusterName);// Get the current Hypervisors associated with the HypervisorCluster

        for (String HypervisorName : HypervisorList) // Create relationships for new Hypervisors
            if (!currentHypervisors.stream().anyMatch(Hypervisor -> Hypervisor.getName().equals(HypervisorName))) createRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, HypervisorName);

        for (Hypervisor Hypervisor : currentHypervisors) // Delete relationships for Hypervisors not in the list
            if (!HypervisorList.contains(Hypervisor.getName())) deleteRelationshipBetweenHypervisorClusterAndHypervisor(HypervisorClusterName, Hypervisor.getName());

    }


    //-------------------- Update Hypervisor VM  relationship database ------------------
    public Relationship createRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        return HypervisorRepository.createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }
    public void deleteRelationshipBetweenHypervisorAndVM(String hypervisorName, String vmName) {
        HypervisorRepository.deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);
    }

    public Map<String, Object> HypervisorVMList = new HashMap<>();
    public void linkHypervisorVMs(){
        HypervisorVMList.put("Hypervisor", "HypervisorTest1");
        HypervisorVMList.put("VMs", Arrays.asList("TestVM1", /*"TestVM2",*/ "TestVM"));
    }
    public void updateRelationshipBetweenOneHypervisorAndVMs(Map<String, Object> hypervisorVMList) {
        String hypervisorName = (String) hypervisorVMList.get("Hypervisor");
        List<String> vmList = (List<String>) hypervisorVMList.get("VMs");

        List<VM> currentVMs = VMService.getVMsByHypervisorName(hypervisorName);// Get the current VMs associated with the hypervisor

        for (String vmName : vmList) // Create relationships for new VMs
            if (!currentVMs.stream().anyMatch(vm -> vm.getName().equals(vmName))) createRelationshipBetweenHypervisorAndVM(hypervisorName, vmName);

        for (VM vm : currentVMs) // Delete relationships for VMs not in the list
            if (!vmList.contains(vm.getName())) deleteRelationshipBetweenHypervisorAndVM(hypervisorName, vm.getName());

    }


// ############################## Components Update ########################################################


    //-------------------- Update Datacenter database ------------------
    public void updateDatacenterDatabase(List<Datacenter> Datacenters) {
        Collection<Datacenter> existingDatacenters = DatacenterService.getAllDatacenters(); // Retrieve all existing Datacenters from the database
        List<String> existingDatacenterNames = existingDatacenters.stream().map(Datacenter::getName).collect(Collectors.toList());

        // Add or update VMs
        for (Datacenter Datacenter : Datacenters) {
            if (existingDatacenterNames.contains(Datacenter.getName()))  DatacenterService.updateDatacenterByName(Datacenter.getName(), Datacenter);  // Datacenter already exists, update it
            else DatacenterService.addMultipleDatacenters(Collections.singletonList(Datacenter)); // Datacenter is new, add it
        }
        // Delete Datacenters not present in the input list
        List<String> inputDatacenterNames = Datacenters.stream().map(Datacenter::getName).collect(Collectors.toList());
        List<String> DatacenterNamesToDelete = existingDatacenterNames.stream().filter(name -> !inputDatacenterNames.contains(name)).collect(Collectors.toList());

        DatacenterService.deleteMultipleDatacentersByName(DatacenterNamesToDelete);
    }

    public List<Datacenter> DatacentersList = new ArrayList<>();
    public void addDatacenterToList() {
        DatacentersList.add(new Datacenter("OT-Mghira", 9, 120, 26, 377));
        DatacentersList.add(new Datacenter("Datacenter", 0, 80, 11, 246));
        DatacentersList.add(new Datacenter("OT-Charguia", 4, 87, 19, 546));
        DatacentersList.add(new Datacenter("Test Datacenter", 4, 87, 19, 546));

    }

    //-------------------- Update DatastoreCluster database ------------------
    public void updateDatastoreClusterDatabase(List<DatastoreCluster> DatastoreClusters) {
        Collection<DatastoreCluster> existingDatastoreClusters = DatastoreClusterService.getAllDatastoreClusters(); // Retrieve all existing DatastoreClusters from the database
        List<String> existingDatastoreClusterNames = existingDatastoreClusters.stream().map(DatastoreCluster::getName).collect(Collectors.toList());

        // Add or update VMs
        for (DatastoreCluster DatastoreCluster : DatastoreClusters) {
            if (existingDatastoreClusterNames.contains(DatastoreCluster.getName()))  DatastoreClusterService.updateDatastoreClusterByName(DatastoreCluster.getName(), DatastoreCluster);  // DatastoreCluster already exists, update it
            else DatastoreClusterService.addMultipleDatastoreClusters(Collections.singletonList(DatastoreCluster)); // DatastoreCluster is new, add it
        }
        // Delete DatastoreClusters not present in the input list
        List<String> inputDatastoreClusterNames = DatastoreClusters.stream().map(DatastoreCluster::getName).collect(Collectors.toList());
        List<String> DatastoreClusterNamesToDelete = existingDatastoreClusterNames.stream().filter(name -> !inputDatastoreClusterNames.contains(name)).collect(Collectors.toList());

        DatastoreClusterService.deleteMultipleDatastoreClustersByName(DatastoreClusterNamesToDelete);
    }

    public List<DatastoreCluster> DatastoreClustersList = new ArrayList<>();
    public void addDatastoreClusterToList() {
        DatastoreClustersList.add(new DatastoreCluster("MGH-UNIFY_NFV_WLD_BCK_DSC", 8220.82, 12286.5));
        DatastoreClustersList.add(new DatastoreCluster("CHA-UNIFY_NFV_WLD_DSC", 2227.0, 8191.5));
        DatastoreClustersList.add(new DatastoreCluster("MGH-UNIFY_ITAAS_U550F_LOCAL", 7262.44, 8191.5));
        DatastoreClustersList.add(new DatastoreCluster("Test DatastoreCluster", 7262.44, 8191.5));
        DatastoreClustersList.add(new DatastoreCluster("Test DatastoreCluster 1", 7262.44, 8191.5));
    }

    //-------------------- Update Datastore database ------------------
    public void updateDatastoreDatabase(List<Datastore> Datastores) {
        Collection<Datastore> existingDatastores = DatastoreService.getAllDatastores(); // Retrieve all existing Datastores from the database
        List<String> existingDatastoreNames = existingDatastores.stream().map(Datastore::getName).collect(Collectors.toList());

        // Add or update VMs
        for (Datastore Datastore : Datastores) {
            if (existingDatastoreNames.contains(Datastore.getName()))  DatastoreService.updateDatastoreByName(Datastore.getName(), Datastore);  // Datastore already exists, update it
            else DatastoreService.addMultipleDatastores(Collections.singletonList(Datastore)); // Datastore is new, add it
        }
        // Delete Datastores not present in the input list
        List<String> inputDatastoreNames = Datastores.stream().map(Datastore::getName).collect(Collectors.toList());
        List<String> DatastoreNamesToDelete = existingDatastoreNames.stream().filter(name -> !inputDatastoreNames.contains(name)).collect(Collectors.toList());

        DatastoreService.deleteMultipleDatastoresByName(DatastoreNamesToDelete);
    }

    public List<Datastore> DatastoresList = new ArrayList<>();
    public void addDatastoreToList() {
        DatastoresList.add(new Datastore("ExtP_Unify_ITAAS_U550F_LOCAL_DS09", "VMFS", 3399.68, 744.27, 0.0, 696.07, 0.0, 0.0, 0, 4095.75));
        DatastoresList.add(new Datastore("ExtP_Unify_ITAAS_U550F_LOCAL_DS10", "VMFS", 3862.76, 281.2, 0.0, 232.99, 0.0, 0.0, 0, 4095.75));
        DatastoresList.add(new Datastore("Bck_Unify_NFVWL_VNX5600_DS00", "VMFS", 1985.05, 2248.86, 23.0, 2110.45, 0.0, 0.21, 6, 4095.5));
        DatastoresList.add(new Datastore("Bck_Unify_NFVWL_VNX5600_DS04", "VMFS", 1068.62, 1037.35, 35.0, 979.13, 0.0, 0.23, 6, 2047.75));
        DatastoresList.add(new Datastore("Bck_Unify_NFVWL_VNX5600_DS01", "VMFS", 1508.99, 699.28, 139.0, 538.76, 1.0, 8.48, 6, 2047.75));
        DatastoresList.add(new Datastore("MGH_Unify_ITAAS_U600_LOCAL_DS20", "VMFS", 6.97, 4097.36, 10434.0, 4088.78, 3.0, 62.8, 9, 4095.75));
        DatastoresList.add(new Datastore("P_UnifyDev_ITAAS2_5600", "VMFS", 4.92, 2073.45, 24.0, 2042.83, 76.0, 0.41, 5, 2047.75));
        DatastoresList.add(new Datastore("CHA_UNIFY_ITAAS_UNITY480_LOCAL_LUN-00", "VMFS", 17.09, 5102.66, 1374.0, 5102.66, 21.0, 41.17, 8, 5119.75));
        DatastoresList.add(new Datastore("test DS", "VMFS", 3399.68, 744.27, 302.1, 696.07, 0.2, 25.6, 10, 4095.75));
        DatastoresList.add(new Datastore("test DS1", "VMFS", 3399.68, 744.27, 302.1, 696.07, 0.2, 0.6, 10, 4095.75));

    }

    //-------------------- Update HypervisorCluster database ------------------
    public void updateHypervisorClusterDatabase(List<HypervisorCluster> HypervisorClusters) {
        Collection<HypervisorCluster> existingHypervisorClusters = HypervisorClusterService.getAllHypervisorClusters(); // Retrieve all existing HypervisorClusters from the database
        List<String> existingHypervisorClusterNames = existingHypervisorClusters.stream().map(HypervisorCluster::getName).collect(Collectors.toList());

        // Add or update VMs
        for (HypervisorCluster HypervisorCluster : HypervisorClusters) {
            if (existingHypervisorClusterNames.contains(HypervisorCluster.getName()))  HypervisorClusterService.updateHypervisorClusterByName(HypervisorCluster.getName(), HypervisorCluster);  // HypervisorCluster already exists, update it
            else HypervisorClusterService.addMultipleHypervisorClusters(Collections.singletonList(HypervisorCluster)); // HypervisorCluster is new, add it
        }
        // Delete HypervisorClusters not present in the input list
        List<String> inputHypervisorClusterNames = HypervisorClusters.stream().map(HypervisorCluster::getName).collect(Collectors.toList());
        List<String> HypervisorClusterNamesToDelete = existingHypervisorClusterNames.stream().filter(name -> !inputHypervisorClusterNames.contains(name)).collect(Collectors.toList());

        HypervisorClusterService.deleteMultipleHypervisorClustersByName(HypervisorClusterNamesToDelete);
    }

    public List<HypervisorCluster> HypervisorClustersList = new ArrayList<>();
    public void addHypervisorClusterToList() {
        HypervisorClustersList.add(new HypervisorCluster("ITaas", 3, 5, 368, 1278));
        HypervisorClustersList.add(new HypervisorCluster("ITaas Workload", 2, 15, 1160, 12284));
        HypervisorClustersList.add(new HypervisorCluster("Edge", 0, 10, 757, 6653));
        HypervisorClustersList.add(new HypervisorCluster("Test Hypervisor-Cluster", 0, 10, 757, 6653));

    }

    //-------------------- Update Hypervisor database ------------------
    public void updateHypervisorDatabase(List<Hypervisor> Hypervisors) {
        Collection<Hypervisor> existingHypervisors = HypervisorService.getAllHypervisors(); // Retrieve all existing Hypervisors from the database
        List<String> existingHypervisorNames = existingHypervisors.stream().map(Hypervisor::getName).collect(Collectors.toList());

        // Add or update VMs
        for (Hypervisor Hypervisor : Hypervisors) {
            if (existingHypervisorNames.contains(Hypervisor.getName()))  HypervisorService.updateHypervisorByName(Hypervisor.getName(), Hypervisor);  // Hypervisor already exists, update it
            else HypervisorService.addMultipleHypervisors(Collections.singletonList(Hypervisor)); // Hypervisor is new, add it
        }
        // Delete Hypervisors not present in the input list
        List<String> inputHypervisorNames = Hypervisors.stream().map(Hypervisor::getName).collect(Collectors.toList());
        List<String> HypervisorNamesToDelete = existingHypervisorNames.stream().filter(name -> !inputHypervisorNames.contains(name)).collect(Collectors.toList());

        HypervisorService.deleteMultipleHypervisorsByName(HypervisorNamesToDelete);
    }

    public List<Hypervisor> HypervisorsList = new ArrayList<>();
    public void addHypervisorToList() {
        HypervisorsList.add(new Hypervisor("cha-esxi-itaas04.intra.local", 48.78, 140.97, 76.77, "CH121 V3", "Normal", 88, 1048173, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-itaas03.intra.local", 45.14, 254.57, 88.26, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-edge04.intra.local", 1.18, 0.06, 3.29, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("mgh-esxi-edge01.intra.local", 1.36, 0.29, 2.0, "ProLiant BL460c Gen9", "Normal", 64, 524158, "VMware ESXi 7.0.3 build-20036589"));
        HypervisorsList.add(new Hypervisor("esxi01-edge.unifydev.local", 0.52, 0.12, 2.15, "CH121 V3", "Normal", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("cha-esxi-edge03.intra.local", 1.66, 0.14, 1.88, "CH121 V3", "Normal", 88, 1048173, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("esxi01-itaas.unifydev.local", 25.03, 8.61, 89.21, "CH121 V3", "Warning", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("esxi04-itaas.unifydev.local", 68.78, 0.98, 95.38, "CH121 V3", "Alert", 74, 261749, "VMware ESXi 6.5.0 build-4887370"));
        HypervisorsList.add(new Hypervisor("HypervisorTest", 48.78, 140.0, 76.77, "V3", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353"));
        HypervisorsList.add(new Hypervisor("HypervisorTest1", 8.78, 40.0, 6.77, "V2", "Normal", 8, 14, "VMware ESXi 7.0.3 build-20328353"));

    }

    //-------------------- Update VM database ------------------
    public void updateVMDatabase(List<VM> VMs) {
        Collection<VM> existingVMs = VMService.getAllVMs(); // Retrieve all existing VMs from the database
        List<String> existingVMNames = existingVMs.stream().map(VM::getName).collect(Collectors.toList());

        // Add or update VMs
        for (VM vm : VMs) {
            if (existingVMNames.contains(vm.getName()))  VMService.updateVMByName(vm.getName(), vm);  // VM already exists, update it
            else VMService.addMultipleVMs(Collections.singletonList(vm)); // VM is new, add it
        }
        // Delete VMs not present in the input list
        List<String> inputVMNames = VMs.stream().map(VM::getName).collect(Collectors.toList());
        List<String> vmNamesToDelete = existingVMNames.stream().filter(name -> !inputVMNames.contains(name)).collect(Collectors.toList());

        VMService.deleteMultipleVMsByName(vmNamesToDelete);
    }

    public List<VM> VMsList = new ArrayList<>();
    public void addVMToList() {
        VMsList.add(new VM("vmNAFprod2", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("Customized RHEL 7.5 (b0732cab-9479-40bf-99f8-ba4820ff26cb)", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("vmPrepCasApp2", 2, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("noi-devcol-01", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("SNP-WSO2", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VoMS-data33", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("rc1vm10 (c6ee83fa-ab6e-49f9-9c09-c400aa2601ae)", 22, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 6350, 34096, "Normal", 2020, 0.7, 560, 340, 0.4, 10042));
        VMsList.add(new VM("drcrmprodcomp05", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("noi-netimp-02", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VmDBINFORMATICAprd", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("VM_TEST_DMZ_WEB", 2, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("VM-Rhel-Hiba-", 2, "Powered On", 0.6, "Red Hat Enterprise Linux 8 (64-bit)", "192.168.1.100", "Resources", new ArrayList<>(), 80, 60, 4096, "Normal", 200, 0.7, 50, 30, 0.4, 100));
        VMsList.add(new VM("VM-CDHTestWKn02", 12, "Powered On", 0.4, "VMware Photon OS (64-bit)", "192.10.1.100", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 20, 6056, "Alert", 200, 0.7, 50, 30, 0.4, 150));
        VMsList.add(new VM("VMSoftSecCent", 6, "Powered Off", 0.2, "Red Hat Enterprise Linux 6 (64-bit)", "192.168.1.155", "Resources", new ArrayList<>(), 80, 320, 512, "Unknown", 200, 0.9, 41, 32, 0.4, 1235));
        VMsList.add(new VM("rm2dev.orascomtunisie.com", 22, "Powered On", 0.6, "Linux 4.18.0-80.el8.x86_64 Red Hat Enterprise Linux release 8.0 (Ootpa)", "10.184.1.10", "MGH-OrgvDC (335a414f-75d3-4f1e-b36a-7a88ac1f202d)", new ArrayList<>(), 80, 6350, 34096, "Normal", 2020, 0.7, 560, 340, 0.4, 10042));
    }


}
