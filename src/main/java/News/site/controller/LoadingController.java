package News.site.controller;


import News.site.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class LoadingController {


    private ArticleService articleService;

    @GetMapping("/download")
    public String index() {
        return "download";
    }


    @PostMapping("/download")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        String message = articleService.saveArticle(file);
        model.addAttribute("message", message);


        return "download";
    }


}

