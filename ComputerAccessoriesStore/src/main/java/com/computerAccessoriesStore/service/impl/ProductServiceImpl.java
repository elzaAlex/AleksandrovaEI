package com.computerAccessoriesStore.service.impl;

import com.computerAccessoriesStore.models.Product;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.repository.ProductRepository;
import com.computerAccessoriesStore.service.ProductService;
import com.computerAccessoriesStore.transfer.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return StreamSupport.stream(productRepository
                .findAll().spliterator(), false)
                .filter(product -> product.getProduct_name().contains(productName))
                .collect(Collectors.toList());
    }

    @Override
    public void add(ProductDTO dto) {
//        Product product = Product.builder()
//                .product_name(dto.getProduct_name())
//                .product_cost(dto.getProduct_cost())
//                .seller(User.builder().id(dto.getIdSeller()).build())
//                .build();
        productRepository.save(getTransformedObject(dto));
    }

    public Product getTransformedObject(ProductDTO dto){
        return Product.builder()
                .product_name(dto.getProduct_name())
                .product_cost(dto.getProduct_cost())
                .seller(User.builder().id(dto.getIdSeller()).build())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void edit(ProductDTO dto) {
        Product product = Product.builder()
                .id(dto.getId())
                .product_name(dto.getProduct_name())
                .product_cost(dto.getProduct_cost())
                .seller(User.builder().id(dto.getIdSeller()).build())
                .build();
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllBySellerId(Long id) {
        return productRepository.findAllBySellerId(id);
    }
}
