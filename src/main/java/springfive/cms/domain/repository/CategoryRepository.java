package springfive.cms.domain.repository;

// import java.net.http.WebSocket.Listener;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import springfive.cms.domain.models.Category;

@Service
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    List<Category> findByName(String name);

    List<Category> findByNameIgnoreCaseStartingWith(String name);

    Category findByNameAndId(String name, String id);
    
}
