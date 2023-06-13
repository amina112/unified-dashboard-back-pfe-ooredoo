package com.ooredoo.services;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.DatastoreClusterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class DatastoreClusterService {

    final DatastoreClusterRepository DatastoreClusterRepository;
    final DatastoreService DatastoreService;
    final VMService vmService;

    public DatastoreClusterService(DatastoreClusterRepository DatastoreClusterRepository, VMService vmService, DatastoreService DatastoreService) {
        this.DatastoreClusterRepository = DatastoreClusterRepository;
        this.vmService = vmService;
        this.DatastoreService = DatastoreService;
    }


    //------------------- display --------------------------
    //display DatastoreClusters and their Datastores list and vms
   public Collection<DatastoreCluster> getAll() {
        Collection<DatastoreCluster> DatastoreClusters = DatastoreClusterRepository.getAll();

        for (DatastoreCluster DatastoreCluster : DatastoreClusters) {
            String DatastoreClusterName = DatastoreCluster.getName();
            List<Datastore> Datastores = DatastoreService.getDatastoresByDatastoreClusterName(DatastoreClusterName);
           for (Datastore Datastore : Datastores) {
                String DatastoreName = Datastore.getName();
                List<VM> vms = vmService.getVMsByDatastoreName(DatastoreName);
                Datastore.setVMS(vms);
            }
            DatastoreCluster.setDatastores(Datastores);
        }

        return DatastoreClusters;
    }
    //display DatastoreClusters and their Datastores list
  public Collection<DatastoreCluster> getAllDatastoreClustersandDatastores() {
            Collection<DatastoreCluster> DatastoreClusters = DatastoreClusterRepository.getAll();

            for (DatastoreCluster DatastoreCluster : DatastoreClusters) {
                String DatastoreClusterName = DatastoreCluster.getName();
                List<Datastore> Datastores = DatastoreService.getDatastoresByDatastoreClusterName(DatastoreClusterName);
                DatastoreCluster.setDatastores(Datastores);
            }

            return DatastoreClusters;
              }
    //display only DatastoreClusters
    public Collection<DatastoreCluster> getAllDatastoreClusters() {
        return DatastoreClusterRepository.getAllDatastoreClusters();
    }

    //display DatastoreCluster list of a Datacenter
    public List<DatastoreCluster> getDatastoreClustersByDatacenterName(String DatacenterName) { return DatastoreClusterRepository.findDatastoreClustersByDatacenterName(DatacenterName);}


    //-------------------- add ---------------------------------
    //add single
    public DatastoreCluster addSingleDatastoreCluster(DatastoreCluster DatastoreCluster) { return DatastoreClusterRepository.save(DatastoreCluster);}
    //add multiple
    public List<DatastoreCluster> addMultipleDatastoreClusters(List<DatastoreCluster> DatastoreClusters) {return DatastoreClusterRepository.saveAll(DatastoreClusters);}

    //------------------- update --------------------------------
    public DatastoreCluster updateDatastoreClusterByName(String name, DatastoreCluster updatedDatastoreCluster) {
        DatastoreCluster existingDatastoreCluster = DatastoreClusterRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedDatastoreCluster.getName() != null) { existingDatastoreCluster.setName(updatedDatastoreCluster.getName());}
        if (updatedDatastoreCluster.getFreeSpace() != 0.0) {existingDatastoreCluster.setFreeSpace(updatedDatastoreCluster.getFreeSpace());}
        if (updatedDatastoreCluster.getTotalCapacity() != 0.0) {existingDatastoreCluster.setTotalCapacity(updatedDatastoreCluster.getTotalCapacity());}


        // Save the updated DatastoreCluster node
        return DatastoreClusterRepository.save(existingDatastoreCluster);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleDatastoreClusterByName(String name) { DatastoreClusterRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleDatastoreClustersByName(List<String> names) { DatastoreClusterRepository.deleteAllByNameIn(names);}


}
