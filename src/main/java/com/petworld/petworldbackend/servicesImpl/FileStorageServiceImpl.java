package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.services.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation;

    public FileStorageServiceImpl() {
        // Define la carpeta donde se guardarán las imágenes
        this.rootLocation = Paths.get("uploads");
    }

    public void init() {
        try {
            // Crea la carpeta si no existe
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la carpeta de almacenamiento", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            // Genera un nombre único para evitar colisiones
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Guarda el archivo en la carpeta uploads
            Path destinationFile = this.rootLocation.resolve(Paths.get(fileName))
                    .normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // Devuelve la ruta relativa del archivo
            return "uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error al almacenar el archivo", e);
        }
    }

}