package springfive.cms.domain.resources;

// import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfive.cms.domain.models.Category;
import springfive.cms.domain.service.CategoryService;
import springfive.cms.domain.vo.CategoryRequest;

@RestController
@RequestMapping("/api/category")
@Api(tags = "category", description = "Category API")
@CrossOrigin(originPatterns = "http://localhost:*")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find category", notes = "Find by category ID")
    @ApiResponses( value = {
        @ApiResponse(code = 200, message = "Category Found"),
        @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> findOne(@PathVariable("id") String id){
        return ResponseEntity.ok(this.categoryService.findOne(id));
    }

    @GetMapping
    @ApiOperation(value = "List Categories", notes = "List all categories")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Categories found"),
        @ApiResponse(code = 404, message = "Categories not found")
    })
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(this.categoryService.findAll());
        // return ResponseEntity.ok(Arrays.asList(new Category(), new Category()));
    }

    @PostMapping
    @ApiOperation(value = "Create new category", notes = "Allows one to create a new category")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Category created successfully"),
        @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Category> newCategory(@RequestBody String name) {
        Category cat = new Category();
        // cat.setId(category.getId());
        cat.setName(name);

        return new ResponseEntity<>(this.categoryService.create(cat), HttpStatus.CREATED);
        // return new ResponseEntity<>(new Category(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove category", notes = "Allows one to remove a category")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Category removed successfully"),
        @ApiResponse(code = 404, message = "Category not found")
    })
    public void removeCategory(String id) {
        this.categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update category", notes = "Allows one to update a category")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Category updated successfully"),
        @ApiResponse(code = 404, message = "Category not found"),
        @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, CategoryRequest category) {
        Category cat = new Category();
        cat.setId(id);
        cat.setName(category.getName());

        return new ResponseEntity<>(this.categoryService.update(cat), HttpStatus.OK);
    }
}
