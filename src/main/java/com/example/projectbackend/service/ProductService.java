package com.example.projectbackend.service;
import com.example.projectbackend.dto.ProductDto;
import com.example.projectbackend.exceptions.RecordNotFoundException;
import com.example.projectbackend.model.Product;
import com.example.projectbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(ProductDto productDto) {

        Product product = new Product();
        product.setId(productDto.id);
        product.setImg(productDto.img);
        product.setTitle(productDto.title);
        product.setDescription(productDto.description);
        product.setPrice(productDto.price);

        Product savedProduct = productRepository.save(toProduct(productDto));
        return savedProduct.getId();
    }


    public ProductDto getProduct(Long id) {
        ProductDto productDto = new ProductDto();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productDto = fromProduct(product.get());
        } else {
            throw new RecordNotFoundException();
        }
        return productDto;
    }


    public Iterable<ProductDto> getProducts() {
        Iterable<Product> allProducts = productRepository.findAll();
        ArrayList<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : allProducts) {

            ProductDto productDto = new ProductDto();
            productDto.id = product.getId();
            productDto.img = product.getImg();
            productDto.title = product.getTitle();
            productDto.description = product.getDescription();
            productDto.price = product.getPrice();

            productDtoList.add(productDto);
        }
        return productDtoList;
    }


    public void updateProduct(Long id, ProductDto newProduct) {
        if (!productRepository.existsById(id)) throw new RecordNotFoundException();
        Product product = productRepository.findById(id).get();

        product.setId(newProduct.getId());
        product.setImg(newProduct.getImg());
        product.setTitle(newProduct.getTitle());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());

        productRepository.save(product);
    }

    public void deleteProduct(@RequestBody Long id) {
        productRepository.deleteById(id);
    }
    public static ProductDto fromProduct(Product product){

        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.img = product.getImg();
        productDto.title = product.getTitle();
        productDto.description = product.getDescription();
        productDto.price = product.getPrice();

        return productDto;
    }


    public Product toProduct(ProductDto productDto) {

        Product product = new Product();
        product.setId(productDto.getId());
        product.setImg(productDto.getImg());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return product;
    }

}




