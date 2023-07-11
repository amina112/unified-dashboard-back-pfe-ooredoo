package com.ooredoo.services;

import com.ooredoo.entities.*;
import com.ooredoo.repositories.DatacenterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class DatacenterService {

    final DatacenterRepository DatacenterRepository;
    final HypervisorClusterService HypervisorClusterService;
    final HypervisorService HypervisorService;
    final DatastoreService DatastoreService;
    final VMService VMService;

    public DatacenterService(DatacenterRepository DatacenterRepository, HypervisorService HypervisorService, DatastoreService DatastoreService, HypervisorClusterService HypervisorClusterService, VMService VMService) {
        this.DatacenterRepository = DatacenterRepository;
        this.DatastoreService = DatastoreService;
        this.HypervisorClusterService = HypervisorClusterService;
        this.HypervisorService = HypervisorService;
        this.VMService = VMService;

    }


    //------------------- display --------------------------
    //display Datacenters and their Hypervisors list and vms
  public Collection<Datacenter> getAll() {
        Collection<Datacenter> Datacenters = DatacenterRepository.getAllDatacenters();

        for (Datacenter Datacenter : Datacenters) {
            String DatacenterName = Datacenter.getName();

            //get datastores list
            List<Datastore> Datastores = DatastoreService.getDatastoresByDatacenterName(DatacenterName);
            Datacenter.setDatastoresList(Datastores);


            //get hypervisor clusters list
            List<HypervisorCluster> HypervisorClusters = HypervisorClusterService.getHypervisorClustersByDatacenterName(DatacenterName);
                for (HypervisorCluster HypervisorCluster : HypervisorClusters) {
                    String HypervisorClusterName = HypervisorCluster.getName();
                    //get vm list of hypervisor
                    List<Hypervisor> Hypervisors = HypervisorService.getHypervisorsByHypervisorClusterName(HypervisorClusterName);
                    for (Hypervisor Hypervisor : Hypervisors) {
                        String hypervisorName = Hypervisor.getName();
                        List<VM> vms = VMService.getVMsByHypervisorName(hypervisorName);
                        Hypervisor.setVMS(vms);
                    }
                    HypervisorCluster.setHypervisors(Hypervisors);
                    //get vm list of hypervisor
                }
            Datacenter.setHypervisorClustersList(HypervisorClusters);

        }

        return Datacenters;
    }

    //display Datacenters and their Datastores list
    public Collection<Datacenter> getAllDatacentersandDatastores() {
        Collection<Datacenter> Datacenters = DatacenterRepository.getAllDatacenters();

        for (Datacenter Datacenter : Datacenters) {
            String DatacenterName = Datacenter.getName();
            List<Datastore> Datastores = DatastoreService.getDatastoresByDatacenterName(DatacenterName);
            Datacenter.setDatastoresList(Datastores);
        }

        return Datacenters;
    }
    //display Datacenters and their HypervisorClusters list
    public Collection<Datacenter> getAllDatacentersandHypervisorClusters() {
            Collection<Datacenter> Datacenters = DatacenterRepository.getAllDatacenters();

            for (Datacenter Datacenter : Datacenters) {
                String DatacenterName = Datacenter.getName();
                List<HypervisorCluster> HypervisorClusters = HypervisorClusterService.getHypervisorClustersByDatacenterName(DatacenterName);
                Datacenter.setHypervisorClustersList(HypervisorClusters);
            }

            return Datacenters;
              }
    //display only Datacenters
    public Collection<Datacenter> getAllDatacenters() {return DatacenterRepository.getAllDatacenters();}

    //-------------------- add ---------------------------------
    //add single
    public Datacenter addSingleDatacenter(Datacenter Datacenter) { return DatacenterRepository.save(Datacenter);}
    //add multiple
    public List<Datacenter> addMultipleDatacenters(List<Datacenter> Datacenters) {return DatacenterRepository.saveAll(Datacenters);}

    //------------------- update --------------------------------
    public Datacenter updateDatacenterByName(String name, Datacenter updatedDatacenter) {
        Datacenter existingDatacenter = DatacenterRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedDatacenter.getName() != null) { existingDatacenter.setName(updatedDatacenter.getName());}
        if (updatedDatacenter.getDatastoreClusters() != 0) {existingDatacenter.setDatastoreClusters(updatedDatacenter.getDatastoreClusters());}
        if (updatedDatacenter.getDatastores() != 0) {existingDatacenter.setDatastores(updatedDatacenter.getDatastores());}
        if (updatedDatacenter.getHypervisors() != 0) {existingDatacenter.setHypervisors(updatedDatacenter.getHypervisors());}
        if (updatedDatacenter.getVirtualMachines() != 0) {existingDatacenter.setVirtualMachines(updatedDatacenter.getVirtualMachines());}


        // Save the updated Datacenter node
        return DatacenterRepository.save(existingDatacenter);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleDatacenterByName(String name) { DatacenterRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleDatacentersByName(List<String> names) { DatacenterRepository.deleteAllByNameIn(names);}


}
