package com.ooredoo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ooredoo.services.ExcelService;

@RestController
public class ExcelController {
//
   /* @Autowired
   private ExcelService excelService;
    @PostMapping("/upload")
    public String uploadClientsFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Fichier vide!";
        }
        try {
            excelService.importClientsFromExcel(file);
            return "Données clients importées avec succès!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'importation des données.";
        }
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return excelService.getAllClients();
    }*/
}
