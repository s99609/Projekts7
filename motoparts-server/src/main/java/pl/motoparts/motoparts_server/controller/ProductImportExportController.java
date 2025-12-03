package pl.motoparts.motoparts_server.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.motoparts.motoparts_server.service.ProductImportExportService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/products")
public class ProductImportExportController {

    private final ProductImportExportService importExportService;

    public ProductImportExportController(ProductImportExportService importExportService) {
        this.importExportService = importExportService;
    }

    @GetMapping("/export/json")
    public ResponseEntity<Resource> exportJson() throws IOException {
        File file = importExportService.exportProductsToJson();
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products-export.json\"")
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

    @GetMapping("/export/xml")
    public ResponseEntity<Resource> exportXml() throws IOException {
        File file = importExportService.exportProductsToXml();
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products-export.xml\"")
                .contentType(MediaType.APPLICATION_XML)
                .body(resource);
    }

    @PostMapping("/import/json")
    public ResponseEntity<String> importJson() throws IOException {
        int imported = importExportService.importProductsFromJson();
        return ResponseEntity.ok("Imported products: " + imported);
    }
}