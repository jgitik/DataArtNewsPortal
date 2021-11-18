package News.site.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "TITLE")
    private String title;

    @Type(type = "materialized_clob")
    @Column(name = "SHORT_ARTICLE")
    private String shortArticle;


    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_long")
    private ArticleLong articleLong;

    public Article() {
    }

    public Article(String title, String shortArticle, ArticleLong articlelong) {
        this.title = title;
        this.shortArticle = shortArticle;
        this.articleLong = articlelong;
    }

    public Article(String title, String shortArticle) {
        this.title = title;
        this.shortArticle = shortArticle;
    }


}

