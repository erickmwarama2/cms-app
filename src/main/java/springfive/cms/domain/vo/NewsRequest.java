package springfive.cms.domain.vo;

import java.util.Set;

import lombok.Data;
import springfive.cms.domain.models.Category;
import springfive.cms.domain.models.Tag;

@Data
public class NewsRequest {
    String title;

    String content;

    String author;

    Set<Category> categories;

    Set<Tag> tags;
}
