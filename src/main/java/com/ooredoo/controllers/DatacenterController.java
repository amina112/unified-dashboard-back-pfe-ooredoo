package com.ooredoo.controllers;

import com.ooredoo.entities.Datacenter;
import com.ooredoo.services.DatacenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Datacenter")
public class DatacenterController {

    final DatacenterService DatacenterService;

    public DatacenterController(DatacenterService DatacenterService) {
        this.DatacenterService = DatacenterService;
    }

    // http://localhost:8089/ooredoo/Datacenter/Datacenter-Datastores-HypervisorClusters-Hypervisors-VMs
    @GetMapping("/Datacenter-Datastores-HypervisorClusters-Hypervisors-VMs")
    @ResponseBody
    public Collection<Datacenter> getAll() {
        return DatacenterService.getAll();
    }
    // http://localhost:8089/ooredoo/Datacenter/Datacenter-HypervisorClusters
   @GetMapping("/Datacenter-HypervisorClusters")
    @ResponseBody
    public Collection<Datacenter> getAllDatacentersandHypervisorClusters() {
        return DatacenterService.getAllDatacentersandHypervisorClusters();
    }
    // http://localhost:8089/ooredoo/Datacenter/Datacenter-Datastores
    @GetMapping("/Datacenter-Datastores")
    @ResponseBody
    public Collection<Datacenter> getAllDatacentersandDatastores() {
        return DatacenterService.getAllDatacentersandDatastores();
    }
    // http://localhost:8089/ooredoo/Datacenter/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Datacenter> getAllDatacenters() { return DatacenterService.getAllDatacenters(); }



    // http://localhost:8089/ooredoo/Datacenter/add-one
    @PostMapping("/add-one")
    public ResponseEntity<Datacenter> addSingleDatacenter(@RequestBody Datacenter Datacenter) {
        Datacenter savedDatacenter = DatacenterService.addSingleDatacenter(Datacenter);
        return ResponseEntity.ok(savedDatacenter);
    }
    // http://localhost:8089/ooredoo/Datacenter/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Datacenter>> addMultipleDatacenters(@RequestBody List<Datacenter> Datacenters) {
        List<Datacenter> savedDatacenters = DatacenterService.addMultipleDatacenters(Datacenters);
        return ResponseEntity.ok(savedDatacenters);
    }



    // http://localhost:8089/ooredoo/Datacenter/update/{name}
    @PutMapping("/update/{name}")
    public Datacenter updateDatacenterByName(@PathVariable String name, @RequestBody Datacenter updatedDatacenter) {
        return DatacenterService.updateDatacenterByName(name, updatedDatacenter);
    }



    // http://localhost:8089/ooredoo/Datacenter/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteDatacenterByName(@PathVariable("name") String name) {
        DatacenterService.deleteSingleDatacenterByName(name);
        return ResponseEntity.ok("Datacenter deleted successfully");
    }
    // http://localhost:8089/ooredoo/Datacenter/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleDatacentersByName(@RequestBody List<String> names) {
        DatacenterService.deleteMultipleDatacentersByName(names);
        return ResponseEntity.ok("Datacenters deleted successfully");
    }
    // http://localhost:8089/ooredoo/Datacenter/upload
    @PostMapping("/upload")
    public String uploadDatacentersFile(@RequestParam("fileDa") MultipartFile file) {
        if (file.isEmpty()) {
            return "Fichier vide!";
        }
        try {
            DatacenterService.importDatacentersFromExcel(file);
            return "Données datacenters importées avec succès!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'importation des données.";
        }
    }

    @GetMapping
    public List<Datacenter> getDatacenters() {
        return DatacenterService.getDatacenters();
    }
}


