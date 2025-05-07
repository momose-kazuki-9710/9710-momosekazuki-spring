package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    // 日付の文字列フォーマット
    private static final DateTimeFormatter FTM = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");

    // フィールド
    private LocalDateTime createdAt;
    private String title;
    private String content;

    // 引数有りのコンストラクタ
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();   // 処理の日時を入れる
    }

    public String getCreatedAt() {
        return createdAt.format(FTM);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content.replaceAll("\n", "<br />");  // 改行文字を<br>タグに変換して返却する
    }


}