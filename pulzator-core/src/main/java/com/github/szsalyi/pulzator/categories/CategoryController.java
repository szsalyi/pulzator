package com.github.szsalyi.pulzator.categories;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/categories",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryVO> save(@RequestBody final CategoryVO categoryVO) {
        CategoryVO savedCategory = categoryService.save(categoryVO);

        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryVO>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryVO> getCategoryById(@PathVariable("id") final Long id) {
        CategoryVO categoryVO = categoryService.getById(id);
        return ResponseEntity.ok(categoryVO);
    }

    @GetMapping(path = "{/name/{name}")
    public ResponseEntity<CategoryVO> getCategoryByName(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCategory(@PathVariable final Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<CategoryVO> updateCategory(@RequestBody final CategoryVO categoryVO) {
        CategoryVO saved = categoryService.save(categoryVO);

        return ResponseEntity.ok(saved);
    }
}
