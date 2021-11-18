package News.site.controller;

import News.site.entity.*;
import News.site.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@AllArgsConstructor
@Controller
public class HomePageController {

    private ArticleService articleService;


    @GetMapping("/")
    public String helloPage(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "index";
    }


    @GetMapping("/article{id}")
    public String articleFull(@PathVariable(value = "id") long id, Model model) {

        model.addAttribute("articl",   articleService.findById(id));
        model.addAttribute("articles", articleService
                .findById(id)
                .getArticleLong());


        return "full-article";
    }
}

