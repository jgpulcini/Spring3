package com.example.springBoot.controller;

import com.example.springBoot.dto.ProductRecordDto;
import com.example.springBoot.models.ProductModel;
import com.example.springBoot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getUmProduto(@PathVariable (value="id") UUID id){
        Optional<ProductModel> productA = productRepository.findById(id);
        if (productA.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findById(id));
    }

 //   @PutMapping("/products")
//    public ResponseEntity<ProductModel> updateProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
 //       return ResponseEntity.status(HttpStatus.OK).body(productRepository.save());
 //   }



}
