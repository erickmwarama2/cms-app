package springfive.cms.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.stereotype.Repository;
// import org.springframework.stereotype.Service;

import springfive.cms.domain.models.News;

public interface NewsRepository extends MongoRepository<News, String> {

}
