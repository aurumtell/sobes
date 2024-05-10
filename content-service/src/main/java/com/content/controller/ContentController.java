package com.content.controller;


import com.content.model.response.AdviseResponse;
import com.content.model.response.ArticleResponse;
import com.content.service.ArticleAdviseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "Authorization")
@Tag(description = "Api to manage articles",
        name = "Article Resource")
public class ContentController {

    @Autowired
    ArticleAdviseService articleAdviseService;

    @Operation(summary = "Get article",
            description = "Get article by Article Id")
    @GetMapping(value = "/user/article/{articleId}")
    @ResponseBody
    public ArticleResponse getArticleById(@PathVariable Long articleId){
        return articleAdviseService.getArticleById(articleId);
    }

    @Operation(summary = "Get article",
            description = "Get all articles")
    @GetMapping(value = "/user/article")
    @ResponseBody
    public List<ArticleResponse> getAllArticle(){
        return articleAdviseService.getAllArticle();
    }

    @Operation(summary = "Get advise",
            description = "Get all advises")
    @GetMapping(value = "/user/advise")
    @ResponseBody
    public List<AdviseResponse> getAllAdvise(){
        return articleAdviseService.getAllAdvise();
    }

//    @Operation(summary = "Get article",
//            description = "Get article by Category Id")
//    @GetMapping(value = "/user/article/category/{categoryId}")
//    @ResponseBody
//    public List<ArticleResponse> getArticleByProfessionId(@PathVariable Integer categoryId, @AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.getArticleByprofessiId(categoryId, user);
//    }

//    @Operation(summary = "Get article",
//            description = "Get current user's articles")
//    @GetMapping(value = "/user/article")
//    @ResponseBody
//    public List<ArticleResponse> getArticle(@AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.getUserArticle(user);
//    }
//
//    @Operation(summary = "Get article",
//            description = "Get current user's article's drafts")
//    @GetMapping(value = "/user/article/draft")
//    @ResponseBody
//    public List<ArticleResponse> getArticleDrafts(@AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.getUserArticleDrafts(user);
//    }
//

//
//    @Operation(summary = "Get category",
//            description = "Get all categories")
//    @GetMapping(value = "/article/category")
//    @ResponseBody
//    public List<CategoryEntity> getAllCategories(){
//        return articleService.getAllCategories();
//    }
//
//    @Operation(summary = "Create reaction",
//            description = "Create reaction")
//    @PostMapping(value = "/user/article/{articleId}/reaction")
//    @ResponseBody
//    public ArticleResponse createReaction(@PathVariable Long articleId, @RequestParam("reaction") Boolean reaction, @AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.createReaction(articleId, reaction, user);
//    }
//
////    @GetMapping("/user/subscribtion/article")
////    public List<ArticleResponse> getSubscribtionArticles(@AuthenticationPrincipal UserDetailsImpl user) {
////        return articleService.getSubscribtionArticles(user);
////    }
//
//    @Operation(summary = "Create comment",
//            description = "Create comment")
//    @PostMapping(value = "/user/article/{articleId}/comment")
//    @ResponseBody
//    public CommentResponse createComment(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.createComment(articleId, user);
//    }
//
//    @Operation(summary = "Get comments",
//            description = "Get all comments for article")
//    @GetMapping(value = "/user/article/{articleId}/comment")
//    @ResponseBody
//    public List<CommentResponse> getAllComments(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.getAllComments(articleId, user);
//    }
//
//    @Operation(summary = "Get article by word",
//            description = "Search")
//    @GetMapping(value = "/article/search")
//    @ResponseBody
//    public List<ArticleResponse> getSearchArticle(@RequestParam("line") String line, @AuthenticationPrincipal UserDetailsImpl user){
//        return articleService.getSearchArticle(line, user);
//    }
}