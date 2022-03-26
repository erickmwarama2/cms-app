package springfive.cms.domain.service;

import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfive.cms.domain.models.User;
import springfive.cms.domain.repository.UserRepository;
// import springfive.cms.domain.vo.UserRequest;
import springfive.cms.domain.vo.UserRequest;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> update(String id, UserRequest userRequest) {
        return this.userRepository.findById(id)
        .flatMap(userDb -> {
            userDb.setName(userRequest.getName());
            userDb.setIdentity(userRequest.getIdentity());
            userDb.setRole(userRequest.getRole());

            return this.userRepository.save(userDb);
        });
    }

    public Mono<User> create(UserRequest userRequest) {
        User user = new User();
        // user.setId(UUID.randomUUID().toString());
        user.setIdentity(userRequest.getIdentity());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());

        return this.userRepository.save(user);
    }

    public void delete(String id) {
        // final User user = this.userRepository.findOne(id);
        // this.userRepository.delete(user);
        this.userRepository.deleteById(id);
    }

    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    public Mono<User> findOne(String id) {
        return this.userRepository.findById(id);
    }
}
