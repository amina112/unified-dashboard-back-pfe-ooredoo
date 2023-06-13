package com.ooredoo.services;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.DatastoreRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class DatastoreService {

    final DatastoreRepository DatastoreRepository;
    final VMService vmService;

    public DatastoreService(DatastoreRepository DatastoreRepository, VMService vmService) {
        this.DatastoreRepository = DatastoreRepository;
        this.vmService = vmService;
    }


    //------------------- display --------------------------
    //display Datastores and their vm list
    public Collection<Datastore> getAll() {
            Collection<Datastore> Datastores = DatastoreRepository.getAll();

            for (Datastore Datastore : Datastores) {
                String DatastoreName = Datastore.getName();
                List<VM> vms = vmService.getVMsByDatastoreName(DatastoreName);
                Datastore.setVMS(vms);
            }

            return Datastores;
              }
    //display only Datastores
    public Collection<Datastore> getAllDatastores() {
        return DatastoreRepository.getAllDatastores();
    }
    //display Datastore list of a DatastoreCluster
    public List<Datastore> getDatastoresByDatastoreClusterName(String DatastoreClusterName) { return DatastoreRepository.findDatastoresByDatastoreClusterName(DatastoreClusterName);}
    //display datastore list of a vm
    public List<Datastore> getDatastoresByVMName(String VMName) {return DatastoreRepository.findDatastoresByVMName(VMName);}
    //display Datastore list of a Datacenter
    public List<Datastore> getDatastoresByDatacenterName(String DatacenterName) { return DatastoreRepository.findDatastoresByDatacenterName(DatacenterName);}

    //-------------------- add ---------------------------------
    //add single
    public Datastore addSingleDatastore(Datastore Datastore) { return DatastoreRepository.save(Datastore);}
    //add multiple
    public List<Datastore> addMultipleDatastores(List<Datastore> Datastores) {return DatastoreRepository.saveAll(Datastores);}

    //------------------- update --------------------------------
    public Datastore updateDatastoreByName(String name, Datastore updatedDatastore) {
        Datastore existingDatastore = DatastoreRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedDatastore.getName() != null) { existingDatastore.setName(updatedDatastore.getName());}
        if (updatedDatastore.getType() != null) { existingDatastore.setType(updatedDatastore.getType());}
        if (updatedDatastore.getHypervisors() != 0) {existingDatastore.setHypervisors(updatedDatastore.getHypervisors());}
        if (updatedDatastore.getBandwidth() != 0.0) {existingDatastore.setBandwidth(updatedDatastore.getBandwidth());}
        if (updatedDatastore.getCapacity() != 0.0) {existingDatastore.setCapacity(updatedDatastore.getCapacity());}
        if (updatedDatastore.getFreeSpace() != 0.0) {existingDatastore.setFreeSpace(updatedDatastore.getFreeSpace());}
        if (updatedDatastore.getLatency() != 0.0) {existingDatastore.setLatency(updatedDatastore.getLatency());}
        if (updatedDatastore.getProvisioned() != 0.0) {existingDatastore.setProvisioned(updatedDatastore.getProvisioned());}
        if (updatedDatastore.getThroughput() != 0.0) {existingDatastore.setThroughput(updatedDatastore.getThroughput());}
        if (updatedDatastore.getUsedSpace() != 0.0) {existingDatastore.setUsedSpace(updatedDatastore.getUsedSpace());}

        // Save the updated Datastore node
        return DatastoreRepository.save(existingDatastore);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleDatastoreByName(String name) { DatastoreRepository.deleteByName(name);}
    //delete multiple
    public void deleteMultipleDatastoresByName(List<String> names) { DatastoreRepository.deleteAllByNameIn(names);}

}
