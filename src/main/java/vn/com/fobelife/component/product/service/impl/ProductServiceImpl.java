package vn.com.fobelife.component.product.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.product.dto.ProductDto;
import vn.com.fobelife.component.product.entity.Product;
import vn.com.fobelife.component.product.repository.ProductRepository;
import vn.com.fobelife.component.product.service.ProductService;

@Service
@Transactional(readOnly = true)
@Slf4j
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
        dto.setStep(entity.getStep() != null ? entity.getStep() : 1);
        dto.setBonus(entity.getBonus() != null ? entity.getBonus() : 0);
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
            try {
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
                product.setStep(model.getStep());
                product.setBonus(model.getBonus());
                product = proRepo.save(product);
            } catch (Exception e) {
                log.error("Save product {}", model.getCode(), e);
            }
        }
        reader.close();

    }

    @Override
    public ProductDto getByCode(String code) throws Exception {
        Product product = proRepo.findByCode(code);
        return applyDto(product);
    }
    
    @Getter
    protected class ProductImportModel {

        private String code;
        private String image;
        private String name;
        private String type;
        private Integer price;
        private String description;
        private String status;
        private Integer step;
        private Integer bonus;

        public ProductImportModel(String[] properties) {
            this.code = properties[0];
            this.image = properties[1];
            this.name = properties[2];
            this.description = properties[3];
            this.price = NumberUtils.toInt(properties[4]);
            this.type = properties[5].toUpperCase();
            this.status = properties[6].toLowerCase();
            this.step = NumberUtils.toInt(properties[7]);
            this.bonus = NumberUtils.toInt(properties[8]);
        }
    }
}
