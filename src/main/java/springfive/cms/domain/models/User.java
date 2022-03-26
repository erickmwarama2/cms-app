package springfive.cms.domain.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    String id;

    String identity;

    String name;

    Role role;
}
