package com.ooredoo.controllers;

import com.ooredoo.entities.VM;
import com.ooredoo.services.VMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/VM")
public class VMController {

    final VMService VMService;

    public VMController(VMService VMService) {
        this.VMService = VMService;
    }

    // http://localhost:8089/ooredoo/VM/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<VM> getAllVMs() { return VMService.getAllVMs(); }
    // http://localhost:8089/ooredoo/VM/{hypervisorName}/vms
    @GetMapping("/Hypervisor/{hypervisorName}/vms")
    public List<VM> getVMsByHypervisorName(@PathVariable String hypervisorName) {
        return VMService.getVMsByHypervisorName(hypervisorName);
    }

    // http://localhost:8089/ooredoo/VM/{DatastoreName}/vms
    @GetMapping("/Datastore/{DatastoreName}/vms")
    public List<VM> getVMsByDatastoreName(@PathVariable String DatastoreName) {
        return VMService.getVMsByDatastoreName(DatastoreName);
    }


    // http://localhost:8089/ooredoo/VM/add-one
    @PostMapping("/add-one")
    public ResponseEntity<VM> addSingleVM(@RequestBody VM VM) {
        VM savedVM = VMService.addSingleVM(VM);
        return ResponseEntity.ok(savedVM);
    }
    // http://localhost:8089/ooredoo/VM/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<VM>> addMultipleVMs(@RequestBody List<VM> VMs) {
        List<VM> savedVMs = VMService.addMultipleVMs(VMs);
        return ResponseEntity.ok(savedVMs);
    }



    // http://localhost:8089/ooredoo/VM/update/{name}
    @PutMapping("/update/{name}")
    public VM updateVMByName(@PathVariable String name, @RequestBody VM updatedVM) {
        return VMService.updateVMByName(name, updatedVM);
    }



    // http://localhost:8089/ooredoo/VM/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteVMByName(@PathVariable("name") String name) {
        VMService.deleteSingleVMByName(name);
        return ResponseEntity.ok("VM deleted successfully");
    }
    // http://localhost:8089/ooredoo/VM/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleVMsByName(@RequestBody List<String> names) {
        VMService.deleteMultipleVMsByName(names);
        return ResponseEntity.ok("VMs deleted successfully");
    }



}
