package News.site.service;


import News.site.exeption.InputFileHandlerException;
import News.site.logic.ZipUtil;
import News.site.entity.Article;
import News.site.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;


    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public FileService fileService;


    public List<Article> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "Id");
        return (List<Article>) articleRepository.findAll(sort);
    }


    public String saveArticle(MultipartFile file) {
        String message = "File published";
        if (file.isEmpty()) return message = "Please, Load the File";
        fileService.uploadFile(file);
        String pathOfFile = fileService.FindNameZip();
        Article article = null;
        try {
            article = ZipUtil.getContextFromFile(pathOfFile);
        } catch (InputFileHandlerException e) {
            e.printStackTrace();
            fileService.DeleteFiles(pathOfFile);
            return e.getMessage();
        }
        fileService.DeleteFiles(pathOfFile);
        articleRepository.save(article);


        return message;

    }


    public Article findById(Long id) {
        return articleRepository.findById(id).get();
    }


}
