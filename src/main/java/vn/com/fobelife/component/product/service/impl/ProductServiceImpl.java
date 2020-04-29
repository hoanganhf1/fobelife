package vn.com.fobelife.component.product.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;

import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.entity.Product;
import vn.com.fobelife.component.product.repository.ProductRepository;
import vn.com.fobelife.component.product.service.ProductService;
import vn.com.fobelife.component.product.service.model.ProductImportModel;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository proRepo;

    @Override
    @Transactional(readOnly = false)
    public ProductDto save(ProductDto dto) throws Exception {
        Product product = new Product();
        product.setCode(UUID.randomUUID().toString());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus().trim());
        product = proRepo.save(product);
        return applyDto(product);
    }

    private ProductDto applyDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus().trim());
        return dto;
    }

    @Override
    public List<ProductDto> getAll() throws Exception {
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        proRepo.findAll().forEach(p -> productDtos.add(applyDto(p)));
        return productDtos;
    }

    @Override
    public List<ProductDto> getByStatus(String status) throws Exception {
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        proRepo.findByStatus(status).forEach(p -> productDtos.add(applyDto(p)));
        return productDtos;
    }

    @Override
    @Transactional(readOnly = false)
    public ProductDto updateStatus(String code, String status) throws Exception {
        Product product = proRepo.findByCode(code);
        if (product == null) {
            throw new EntityNotFoundException("Product is not existing.");
        }
        product.setStatus(status.trim().toUpperCase());
        product = proRepo.save(product);
        return applyDto(product);

    }

    @Override
    public List<ProductDto> getByStatusAndType(String status, String type) throws Exception {
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        proRepo.findByStatusAndTypeOrderByPriceAsc(status, type.toUpperCase())
                .forEach(p -> productDtos.add(applyDto(p)));
        return productDtos;
    }

    @Override
    @Transactional(readOnly = false)
    public void importProduct(String csvContent) throws Exception {
        CSVReader reader = new CSVReader(new StringReader(csvContent));
        String[] line;
        boolean isFirstLine = true;
        while ((line = reader.readNext()) != null) {

            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            ProductImportModel model = new ProductImportModel(line);
            Product product = proRepo.findByCode(model.getCode());
            if (product == null) {
                product = new Product();
                product.setCode(model.getCode());
            }
            product.setStatus(model.getStatus());
            product.setImage(model.getImage());
            product.setName(model.getName());
            product.setDescription(model.getDescription());
            product.setPrice(model.getPrice());
            product.setType(model.getType());
            product = proRepo.save(product);

        }
        reader.close();

    }

    @Override
    public ProductDto getByCode(String code) throws Exception {
        Product product = proRepo.findByCode(code);
        return applyDto(product);
    }
}
