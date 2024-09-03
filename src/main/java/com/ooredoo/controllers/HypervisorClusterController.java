package com.ooredoo.controllers;

import com.ooredoo.entities.HypervisorCluster;
import com.ooredoo.services.HypervisorClusterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/HypervisorCluster")
public class HypervisorClusterController {

    final HypervisorClusterService HypervisorClusterService;

    public HypervisorClusterController(HypervisorClusterService HypervisorClusterService) {
        this.HypervisorClusterService = HypervisorClusterService;
    }

    // http://localhost:8089/ooredoo/HypervisorCluster/HypervisorCluster-Hypervisors-VMs
    @GetMapping("/HypervisorCluster-Hypervisors-VMs")
    @ResponseBody
    public Collection<HypervisorCluster> getAll() {
        return HypervisorClusterService.getAll();
    }

    // http://localhost:8089/ooredoo/HypervisorCluster/HypervisorCluster-Hypervisors
    @GetMapping("/HypervisorCluster-Hypervisors")
    @ResponseBody
    public Collection<HypervisorCluster> getAllHypervisorClustersandHypervisors() {
        return HypervisorClusterService.getAllHypervisorClustersandHypervisors();
    }
    // http://localhost:8089/ooredoo/HypervisorCluster/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<HypervisorCluster> getAllHypervisorClusters() { return HypervisorClusterService.getAllHypervisorClusters(); }



    // http://localhost:8089/ooredoo/HypervisorCluster/add-one
    @PostMapping("/add-one")
    public ResponseEntity<HypervisorCluster> addSingleHypervisorCluster(@RequestBody HypervisorCluster HypervisorCluster) {
        HypervisorCluster savedHypervisorCluster = HypervisorClusterService.addSingleHypervisorCluster(HypervisorCluster);
        return ResponseEntity.ok(savedHypervisorCluster);
    }
    // http://localhost:8089/ooredoo/HypervisorCluster/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<HypervisorCluster>> addMultipleHypervisorClusters(@RequestBody List<HypervisorCluster> HypervisorClusters) {
        List<HypervisorCluster> savedHypervisorClusters = HypervisorClusterService.addMultipleHypervisorClusters(HypervisorClusters);
        return ResponseEntity.ok(savedHypervisorClusters);
    }



    // http://localhost:8089/ooredoo/HypervisorCluster/update/{name}
    @PutMapping("/update/{name}")
    public HypervisorCluster updateHypervisorClusterByName(@PathVariable String name, @RequestBody HypervisorCluster updatedHypervisorCluster) {
        return HypervisorClusterService.updateHypervisorClusterByName(name, updatedHypervisorCluster);
    }



    // http://localhost:8089/ooredoo/HypervisorCluster/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteHypervisorClusterByName(@PathVariable("name") String name) {
        HypervisorClusterService.deleteSingleHypervisorClusterByName(name);
        return ResponseEntity.ok("HypervisorCluster deleted successfully");
    }
    // http://localhost:8089/ooredoo/HypervisorCluster/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleHypervisorClustersByName(@RequestBody List<String> names) {
        HypervisorClusterService.deleteMultipleHypervisorClustersByName(names);
        return ResponseEntity.ok("HypervisorClusters deleted successfully");
    }
    @PostMapping("/upload")
    public String uploadHypervisorClustersFile(@RequestParam("file1") MultipartFile file) {
        if (file.isEmpty()) {
            return "Fichier vide!";
        }
        try {
            HypervisorClusterService.importHypervisorClustersFromExcel(file);
            return "Données clusters d'hyperviseurs importées avec succès!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'importation des données.";
        }
    }

    // Endpoint pour récupérer tous les clusters d'hyperviseurs
    @GetMapping
    public List<HypervisorCluster> getHypervisorClusters() {
        return HypervisorClusterService.getHypervisorClusters();
    }
}
