package com.ooredoo.controllers;

import com.ooredoo.entities.Datastore;
import com.ooredoo.entities.VM;
import com.ooredoo.services.DatastoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Datastore")
public class DatastoreController {

    final DatastoreService DatastoreService;

    public DatastoreController(DatastoreService DatastoreService) {
        this.DatastoreService = DatastoreService;
    }

    // http://localhost:8089/ooredoo/Datastore/Datastore-VM
    @GetMapping("/Datastore-VM")
    @ResponseBody
    public Collection<Datastore> getAll() {
        return DatastoreService.getAll();
    }
    // http://localhost:8089/ooredoo/Datastore/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Datastore> getAllDatastores() { return DatastoreService.getAllDatastores(); }
    // http://localhost:8089/ooredoo/Datastore/VM/{VMName}/Datastores
    @GetMapping("/VM/{VMName}/Datastores")
    public List<Datastore> getDatastoresByVMName(@PathVariable String VMName) {
        return DatastoreService.getDatastoresByVMName(VMName);
    }


    // http://localhost:8089/ooredoo/Datastore/add-one
    @PostMapping("/add-one")
    public ResponseEntity<Datastore> addSingleDatastore(@RequestBody Datastore Datastore) {
        Datastore savedDatastore = DatastoreService.addSingleDatastore(Datastore);
        return ResponseEntity.ok(savedDatastore);
    }
    // http://localhost:8089/ooredoo/Datastore/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Datastore>> addMultipleDatastores(@RequestBody List<Datastore> Datastores) {
        List<Datastore> savedDatastores = DatastoreService.addMultipleDatastores(Datastores);
        return ResponseEntity.ok(savedDatastores);
    }



    // http://localhost:8089/ooredoo/Datastore/update/{name}
    @PutMapping("/update/{name}")
    public Datastore updateDatastoreByName(@PathVariable String name, @RequestBody Datastore updatedDatastore) {
        return DatastoreService.updateDatastoreByName(name, updatedDatastore);
    }



    // http://localhost:8089/ooredoo/Datastore/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteDatastoreByName(@PathVariable("name") String name) {
        DatastoreService.deleteSingleDatastoreByName(name);
        return ResponseEntity.ok("Datastore deleted successfully");
    }
    // http://localhost:8089/ooredoo/Datastore/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleDatastoresByName(@RequestBody List<String> names) {
        DatastoreService.deleteMultipleDatastoresByName(names);
        return ResponseEntity.ok("Datastores deleted successfully");
    }

}
