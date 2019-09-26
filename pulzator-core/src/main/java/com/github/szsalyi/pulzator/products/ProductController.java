package com.github.szsalyi.pulzator.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductVO> createProduct(@RequestBody final ProductVO product) throws URISyntaxException {
        ProductVO newProduct = productService.save(product);
        return ResponseEntity
                .created(new URI("/products/" + newProduct.getId()))
                .body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductVO>> getAllProducts() {
        List<ProductVO> products = productService.loadAll();

        return ResponseEntity.ok(products);
    }

    @PutMapping
    public void updateProduct(@RequestBody @Valid final ProductVO product) {
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable final Long id) throws Exception {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductById(@PathVariable("id") final Long id) throws Exception {
        Optional<ProductVO> optionalProduct = productService.loadById(id);

        return optionalProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductVO>> getProductByName(@RequestParam("name") final String name) throws Exception {
        Optional<List<ProductVO>> optionalProduct = productService.loadByNameContaining(name);

        return optionalProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
