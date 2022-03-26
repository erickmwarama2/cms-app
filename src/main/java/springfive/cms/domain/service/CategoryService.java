package springfive.cms.domain.service;

import java.util.List;
import java.util.Optional;

// import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springfive.cms.domain.models.Category;
import springfive.cms.domain.repository.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category update(Category category) {
        return this.categoryRepository.save(category);
    }

    @Transactional
    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    @Transactional
    public void delete(String id) {
        final Optional<Category> category = this.categoryRepository.findById(id);
        if(!category.isEmpty()){
            this.categoryRepository.delete(category.get());
        }
        
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findOne(String id) {
        // return this.categoryRepository.findOne(id);
        Optional<Category> category =  this.categoryRepository.findById(id);
        return category.isEmpty()? null : category.get();
    }
}
