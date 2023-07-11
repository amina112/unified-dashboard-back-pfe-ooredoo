package com.ooredoo.services;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.entities.VM;
import com.ooredoo.repositories.VMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VMService {

    final VMRepository VMRepository;

    public VMService(VMRepository VMRepository) {
        this.VMRepository = VMRepository;
    }

    //------------------- display --------------------------
    //display vm list
    public Collection<VM> getAllVMs() {
        return VMRepository.getAllVMs();
    }
    //display vm list of a Hypervisor
    public List<VM> getVMsByHypervisorName(String hypervisorName) {
        return VMRepository.findVMsByHypervisorName(hypervisorName);
    }
    //display vm list of a Datastore
    public List<VM> getVMsByDatastoreName(String DatastoreName) {
        return VMRepository.findVMsByDatastoreName(DatastoreName);
    }

    //-------------------- add ---------------------------------
    //add single
    public VM addSingleVM(VM VM) { return VMRepository.save(VM);}
    //add multiple
    public List<VM> addMultipleVMs(List<VM> VMs) {return VMRepository.saveAll(VMs);}

    //------------------- update --------------------------------
    public VM updateVMByName(String name, VM updatedVM) {
        VM existingVM = VMRepository.findByName(name);


        // Update the specific fields with the provided values
        if (updatedVM.getName() != null) { existingVM.setName(updatedVM.getName());}
        if (updatedVM.getCPU_Usage() != 0.0) {existingVM.setCPU_Usage(updatedVM.getCPU_Usage());}
        if (updatedVM.getCPU_Utilization() != 0.0) {existingVM.setCPU_Utilization(updatedVM.getCPU_Utilization());}
        if (updatedVM.getGuest_OS() != null) {existingVM.setGuest_OS(updatedVM.getGuest_OS());}
        if (updatedVM.getIP() != null) {existingVM.setIP(updatedVM.getIP());}
        if (updatedVM.getCPU_Utilization() != 0.0) {existingVM.setCPU_Utilization(updatedVM.getCPU_Utilization());}
        if (updatedVM.getStatus() != null) {existingVM.setStatus(updatedVM.getStatus());}
        if (updatedVM.getState() != null) {existingVM.setState(updatedVM.getState());}
        if (updatedVM.getResource_Pool() != null) {existingVM.setResource_Pool(updatedVM.getResource_Pool());}
        if (updatedVM.getMemory_Utilization() != 0.0) {existingVM.setMemory_Utilization(updatedVM.getMemory_Utilization());}
        if (updatedVM.getvCPUs() != 0) {existingVM.setvCPUs(updatedVM.getvCPUs());}
        if (updatedVM.getMemory_Size() != 0) {existingVM.setMemory_Size(updatedVM.getMemory_Size());}
        if (updatedVM.getVirtual_Disk_Bandwidth() != 0) {existingVM.setVirtual_Disk_Bandwidth(updatedVM.getVirtual_Disk_Bandwidth());}
        if (updatedVM.getWrite_Throughput() != 0) {existingVM.setWrite_Throughput(updatedVM.getWrite_Throughput());}
        if (updatedVM.getUsed_Space() != 0) {existingVM.setUsed_Space(updatedVM.getUsed_Space());}
        if (updatedVM.getThroughput() != 0) {existingVM.setThroughput(updatedVM.getThroughput());}
        if (updatedVM.getRead_Throughput() != 0) {existingVM.setRead_Throughput(updatedVM.getRead_Throughput());}
        if (updatedVM.getProvisioned_Space() != 0) {existingVM.setProvisioned_Space(updatedVM.getProvisioned_Space());}



        // Save the updated Hypervisor node
        return VMRepository.save(existingVM);
    }

    //---------------------- delete -------------------------------
    //delete single
    public void deleteSingleVMByName(String name) { VMRepository.deleteByName(name);}
    //delete multipe
    public void deleteMultipleVMsByName(List<String> names) { VMRepository.deleteAllByNameIn(names);}


}
