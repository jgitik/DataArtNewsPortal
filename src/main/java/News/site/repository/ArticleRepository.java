package News.site.repository;

import News.site.entity.Article;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {


}
