package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.HypervisorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class HypervisorService {

    final HypervisorRepository hypervisorRepository;
    final VMService vmService;

    public HypervisorService(HypervisorRepository hypervisorRepository, VMService vmService) {
        this.hypervisorRepository = hypervisorRepository;
        this.vmService = vmService;
    }


    //------------------- display --------------------------
    //display hypervisors and their vm list
    public Collection<Hypervisor> getAll() {
            Collection<Hypervisor> hypervisors = hypervisorRepository.getAll();

            for (Hypervisor hypervisor : hypervisors) {
                String hypervisorName = hypervisor.getName();
                List<VM> vms = vmService.getVMsByHypervisorName(hypervisorName);
                hypervisor.setVMS(vms);
            }

            return hypervisors;
              }
    //display only hypervisors
    public Collection<Hypervisor> getAllHypervisors() {
        return hypervisorRepository.getAllHypervisors();
    }
    //display hypervisor list of a HypervisorCluster
    public List<Hypervisor> getHypervisorsByHypervisorClusterName(String hypervisorClusterName) { return hypervisorRepository.findHypervisorsByHypervisorClusterName(hypervisorClusterName);}

    //-------------------- add ---------------------------------
    //add single
    public Hypervisor addSingleHypervisor(Hypervisor hypervisor) { return hypervisorRepository.save(hypervisor);}
    //add multiple
    public List<Hypervisor> addMultipleHypervisors(List<Hypervisor> hypervisors) {return hypervisorRepository.saveAll(hypervisors);}

    //------------------- update --------------------------------
    public Hypervisor updateHypervisorByName(String name, Hypervisor updatedHypervisor) {
        Hypervisor existingHypervisor = hypervisorRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedHypervisor.getName() != null) { existingHypervisor.setName(updatedHypervisor.getName());}
        if (updatedHypervisor.getCPU_Utilization() != 0.0) {existingHypervisor.setCPU_Utilization(updatedHypervisor.getCPU_Utilization());}
        if (updatedHypervisor.getDisk_Bandwidth() != 0.0) {existingHypervisor.setDisk_Bandwidth(updatedHypervisor.getDisk_Bandwidth());}
        if (updatedHypervisor.getMemory_Utilization() != 0.0) {existingHypervisor.setMemory_Utilization(updatedHypervisor.getMemory_Utilization());}
        if (updatedHypervisor.getModel() != null) {existingHypervisor.setModel(updatedHypervisor.getModel());}
        if (updatedHypervisor.getStatus() != null) {existingHypervisor.setStatus(updatedHypervisor.getStatus());}
        if (updatedHypervisor.getTotal_CPU() != 0) {existingHypervisor.setTotal_CPU(updatedHypervisor.getTotal_CPU());}
        if (updatedHypervisor.getTotal_Memory() != 0) {existingHypervisor.setTotal_Memory(updatedHypervisor.getTotal_Memory());}
        if (updatedHypervisor.getVersion() != null) {existingHypervisor.setVersion(updatedHypervisor.getVersion());}

        // Save the updated Hypervisor node
        return hypervisorRepository.save(existingHypervisor);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleHypervisorByName(String name) { hypervisorRepository.deleteByName(name);}
    //delete multiple
    public void deleteMultipleHypervisorsByName(List<String> names) { hypervisorRepository.deleteAllByNameIn(names);}

}
