package springfive.cms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import springfive.cms.domain.models.Category;
// import springfive.cms.domain.repository.NewsRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// @EnableSwagger2
@EnableMongoRepositories(basePackages = "springfive.cms.domain.repository")
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

	@Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build();
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Category sports = new Category();
            sports.setName("sports");

            Category music = new Category();
            music.setName("music");

            // Flux
            System.out.println("===The music category is " + music.getName());

            // Flux.just(sports, music)
            // .doOnNext(System.out::println)
            // .subscribe();

            UnicastProcessor<String> hotSource = UnicastProcessor.create();
            Flux<Category> hotPublisher = hotSource.publish()
            .autoConnect().map((String t) -> {
                Category cat = new Category();
                cat.setName(t);
                return cat;
            });

            hotSource.onNext("Lifestyle");
            hotSource.onNext("climate");
            // hotPublisher.subscribe(category -> System.out.println("Subscriber 1: " + category.getName()));

            hotSource.onNext("politics");
            hotSource.onNext("Automotive");
            hotSource.onNext("Tech");

            hotPublisher.subscribe(category -> System.out.println("Subscriber 2: " + category.getName()));
            
            hotSource.onNext("Gaming");
            hotSource.onNext("Agriculture");

            hotSource.onComplete();
        };
    }

}
