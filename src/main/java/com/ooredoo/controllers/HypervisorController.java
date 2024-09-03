package com.ooredoo.controllers;

import com.ooredoo.entities.Hypervisor;
import com.ooredoo.services.HypervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Hypervisor")
public class HypervisorController {

    final HypervisorService hypervisorService;

    public HypervisorController(HypervisorService hypervisorService) {
        this.hypervisorService = hypervisorService;
    }

    // http://localhost:8089/ooredoo/Hypervisor/Hypervisor-VM
    @GetMapping("/Hypervisor-VM")
    @ResponseBody
    public Collection<Hypervisor> getAll() {
        return hypervisorService.getAll();
    }
    // http://localhost:8089/ooredoo/Hypervisor/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<Hypervisor> getAllHypervisors() { return hypervisorService.getAllHypervisors(); }



    // http://localhost:8089/ooredoo/Hypervisor/add-one
    @PostMapping("/add-one")
    public ResponseEntity<Hypervisor> addSingleHypervisor(@RequestBody Hypervisor hypervisor) {
        Hypervisor savedHypervisor = hypervisorService.addSingleHypervisor(hypervisor);
        return ResponseEntity.ok(savedHypervisor);
    }
    // http://localhost:8089/ooredoo/Hypervisor/add-multiple
    @PostMapping("/add-multiple")
    public ResponseEntity<List<Hypervisor>> addMultipleHypervisors(@RequestBody List<Hypervisor> hypervisors) {
        List<Hypervisor> savedHypervisors = hypervisorService.addMultipleHypervisors(hypervisors);
        return ResponseEntity.ok(savedHypervisors);
    }



    // http://localhost:8089/ooredoo/Hypervisor/update/{name}
    @PutMapping("/update/{name}")
    public Hypervisor updateHypervisorByName(@PathVariable String name, @RequestBody Hypervisor updatedHypervisor) {
        return hypervisorService.updateHypervisorByName(name, updatedHypervisor);
    }



    // http://localhost:8089/ooredoo/Hypervisor/delete-single/{name}
    @DeleteMapping("/delete-single/{name}")
    public ResponseEntity<String> deleteHypervisorByName(@PathVariable("name") String name) {
        hypervisorService.deleteSingleHypervisorByName(name);
        return ResponseEntity.ok("Hypervisor deleted successfully");
    }
    // http://localhost:8089/ooredoo/Hypervisor/delete-multiple
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleHypervisorsByName(@RequestBody List<String> names) {
        hypervisorService.deleteMultipleHypervisorsByName(names);
        return ResponseEntity.ok("Hypervisors deleted successfully");
    }
    // http://localhost:8089/ooredoo/Hypervisor/delete-multiple/uploadhyp
    @PostMapping("/uploadhyp")
    public String uploadHypervisorsFile(@RequestParam("filehyp") MultipartFile file) {
        if (file.isEmpty()) {
            return "Fichier vide!";
        }
        try {
            hypervisorService.importHypervisorsFromExcel(file);
            return "Données hyperviseurs importées avec succès!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'importation des données.";
        }
    }

    @GetMapping
    public List<Hypervisor> getHypervisors() {
        return hypervisorService.getHypervisors();
    }
}

