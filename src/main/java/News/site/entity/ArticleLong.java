package News.site.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;


@Entity
@Getter
@Setter
@Table(name = "BodyArt")
public class ArticleLong {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Type(type = "materialized_clob")
    private String bodyArticle;

    @OneToOne(optional = true, mappedBy = "articleLong")
    private Article article;


    public ArticleLong() {
    }

    public ArticleLong(String bodyArticle) {
        this.bodyArticle = bodyArticle;
    }
}






