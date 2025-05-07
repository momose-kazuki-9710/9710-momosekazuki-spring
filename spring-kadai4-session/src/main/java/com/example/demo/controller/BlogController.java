package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.model.PostList;

@Controller
public class BlogController {

    @Autowired
    HttpSession session;

    @Autowired
    Account account;

    @Autowired
    PostList postList;

    @GetMapping({"/", "/logout"})
    public String index() {

        // セッション情報の全破棄
        session.invalidate();

        // login.htmlを表示
        return "login";
    }

    // ログインボタンを押下した時の処理
    @PostMapping("/login")
    public String login(
            @RequestParam(name="name") String name
            ) {

        // 名前をセッションに登録
        account.setName(name);

        return "blog";
    }

    // 投稿するボタンを押下した時の処理
    @PostMapping("/blog")
    public String posts(
            @RequestParam(name="title") String title,
            @RequestParam(name="content") String content
            ) {

        // セッションに保持された投稿リストを取得
        List<Post> allPosts = postList.getPosts();

        //受け取ったパラメータを投稿リストに追加
        allPosts.add(new Post(title, content));


        return "blog";
    }
}