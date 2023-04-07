package com.example.projectbackend.controller;
import com.example.projectbackend.dto.ProductDto;
import com.example.projectbackend.service.ProductService;
import com.example.projectbackend.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto, BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        else {
            Long createdId = productService.createProduct(productDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/products/create/" + createdId).toUriString());
            return ResponseEntity.created(uri).body("Product " + createdId + " created!");
        }
    }


//    @GetMapping("/{id}")
//    @Transactional
//    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.getProduct(id));
//    }


    @GetMapping( "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        ProductDto optionalProduct = productService.getProduct(id);
        return ResponseEntity.ok().body(optionalProduct);
    }


    @GetMapping("")
    public ResponseEntity<Iterable<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct (@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



//        @GetMapping("/aftereventdate")
//    public ResponseEntity<Iterable<Product>> getProductsEventdate(
//            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) {
//        return ResponseEntity.ok(repos.findByEventdate(date));
//    }
//
//
//
//        @GetMapping("/findbyeventname")
//    public ResponseEntity<Iterable<Product>> getProductsContaining(
//            @RequestParam String query) {
//        return ResponseEntity.ok(repos.findByEventNameContaining(query));
//    }

}
