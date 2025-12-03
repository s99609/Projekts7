package pl.motoparts.motoparts_server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import pl.motoparts.motoparts_server.model.Product;
import pl.motoparts.motoparts_server.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductImportExportService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    private final File jsonFile = new File("products-export.json");
    private final File xmlFile = new File("products-export.xml");

    public ProductImportExportService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.objectMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
    }

    public File exportProductsToJson() throws IOException {
        List<Product> products = productRepository.findAll();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, products);
        return jsonFile;
    }

    public File exportProductsToXml() throws IOException {
        List<Product> products = productRepository.findAll();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(xmlFile, products);
        return xmlFile;
    }

    public int importProductsFromJson() throws IOException {
        if (!jsonFile.exists()) {
            return 0;
        }

        List<Product> products = objectMapper.readValue(
                jsonFile,
                new TypeReference<List<Product>>() {}
        );

        products.forEach(p -> p.setId(null));
        productRepository.saveAll(products);
        return products.size();
    }
}