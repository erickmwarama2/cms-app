package springfive.cms.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springfive.cms.domain.models.News;
// import springfive.cms.domain.repository.CategoryRepository;
import springfive.cms.domain.repository.NewsRepository;

@Service
@Transactional(readOnly = true)
public class NewsService {
    
    private final NewsRepository newsRepository;
    // private final CategoryRepository categoryRepository

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Transactional
    public News create(News news) {
        return this.newsRepository.save(news);
    }

    public News findOne(String id) {
        Optional<News> news =  this.newsRepository.findById(id);
        return news.isPresent()? news.get() : null;
    }

    public List<News> findAll() {
        return this.newsRepository.findAll();
    }

    public void delete(String id) {
        this.newsRepository.deleteById(id);
    }
}
