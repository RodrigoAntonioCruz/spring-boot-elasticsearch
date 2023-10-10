package com.example.services;


import com.example.domains.Product;
import com.example.domains.dto.ProductDTO;
import com.example.exception.NotFoundException;
import com.example.repositories.ProductRepository;
import com.example.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ModelMapper mapper;
    private final ProductRepository productRepository;

    public ProductDTO create(ProductDTO productDTO) {
        var product = mapper.map(productDTO, Product.class);
        return saveProduct(null, product);
    }

    public List<ProductDTO> add(List<ProductDTO> products) {
        List<Product> productEntities = mapper.map(products, new TypeToken<List<Product>>() {}.getType());
        Iterable<Product> savedProducts = productRepository.saveAll(productEntities);
        return mapper.map(savedProducts, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    public ProductDTO update(String id, ProductDTO productDTO) {
        var updateProduct = findProduct(id);

        updateProduct.setName(productDTO.getName());
        updateProduct.setDescription(productDTO.getDescription());
        updateProduct.setPrice(productDTO.getPrice());

        return saveProduct(id, updateProduct);
    }

    public Page<ProductDTO> findAll(String keyword, Pageable pageable) {
        Page<ProductDTO> page;

        if (keyword.isEmpty()) {
            page = productRepository.findAll(pageable)
                    .map(p -> mapper.map(p, ProductDTO.class));
        } else {
            page = productRepository.findByProductKeyword(keyword, pageable)
                    .map(p -> mapper.map(p, ProductDTO.class));
        }
        return page;
    }

    public ProductDTO findById(String id) {
        var product = findProduct(id);
        return mapper.map(product, ProductDTO.class);
    }

    public void delete(String id) {
        productRepository.deleteById(findProduct(id).getId());
    }

    private Product findProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND));
    }

    private ProductDTO saveProduct(String id, Product product) {
        product.setId(id);
        var productUpdated = productRepository.save(product);
        return mapper.map(productUpdated, ProductDTO.class);
    }
}
