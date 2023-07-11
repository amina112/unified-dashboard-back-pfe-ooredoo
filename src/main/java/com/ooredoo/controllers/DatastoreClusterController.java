package com.ooredoo.controllers;

import com.ooredoo.entities.DatastoreCluster;
import com.ooredoo.services.DatastoreClusterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/DatastoreCluster")
public class DatastoreClusterController {

    final DatastoreClusterService DatastoreClusterService;

    public DatastoreClusterController(DatastoreClusterService DatastoreClusterService) {
        this.DatastoreClusterService = DatastoreClusterService;
    }

    // http://localhost:8089/ooredoo/DatastoreCluster/DatastoreCluster-Datastores-VMs
   @GetMapping("/DatastoreCluster-Datastores-VMs")
    @ResponseBody
    public Collection<DatastoreCluster> getAll() {
        return DatastoreClusterService.getAll();
    }

    // http://localhost:8089/ooredoo/DatastoreCluster/DatastoreCluster-Datastores
    @GetMapping("/DatastoreCluster-Datastores")
    @ResponseBody
    public Collection<DatastoreCluster> getAllDatastoreClustersandDatastores() {
        return DatastoreClusterService.getAllDatastoreClustersandDatastores();
    }
    // http://localhost:8089/ooredoo/DatastoreCluster/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<DatastoreCluster> getAllDatastoreClusters() { return DatastoreClusterService.getAllDatastoreClusters(); }



    // http://localhost:8089/ooredoo/DatastoreCluster/add-one
    @PostMapping("/add-one")
    public ResponseEntity<DatastoreCluster> addSingleDatastoreCluster(@RequestBody DatastoreCluster DatastoreCluster) {
        DatastoreCluster savedDatastoreCluster = DatastoreClusterService.addSingleDatastoreCluster(DatastoreCluster);
        return ResponseEntity.ok(savedDatastoreCluster);
    }
    // http://localhost:8089/ooredoo/DatastoreCluster/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<DatastoreCluster>> addMultipleDatastoreClusters(@RequestBody List<DatastoreCluster> DatastoreClusters) {
        List<DatastoreCluster> savedDatastoreClusters = DatastoreClusterService.addMultipleDatastoreClusters(DatastoreClusters);
        return ResponseEntity.ok(savedDatastoreClusters);
    }



    // http://localhost:8089/ooredoo/DatastoreCluster/update/{name}
    @PutMapping("/update/{name}")
    public DatastoreCluster updateDatastoreClusterByName(@PathVariable String name, @RequestBody DatastoreCluster updatedDatastoreCluster) {
        return DatastoreClusterService.updateDatastoreClusterByName(name, updatedDatastoreCluster);
    }



    // http://localhost:8089/ooredoo/DatastoreCluster/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteDatastoreClusterByName(@PathVariable("name") String name) {
        DatastoreClusterService.deleteSingleDatastoreClusterByName(name);
        return ResponseEntity.ok("DatastoreCluster deleted successfully");
    }
    // http://localhost:8089/ooredoo/DatastoreCluster/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleDatastoreClustersByName(@RequestBody List<String> names) {
        DatastoreClusterService.deleteMultipleDatastoreClustersByName(names);
        return ResponseEntity.ok("DatastoreClusters deleted successfully");
    }

}
