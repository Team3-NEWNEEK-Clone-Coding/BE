package com.example.newnique.news.dto;

import com.example.newnique.news.entity.News;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewsDetailsResponseDto {

    private String title;

    private String content;

    private String img;

    private String category;

    private LocalDate date;

    private int heart;

    private String tag;

    public NewsDetailsResponseDto(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.img = news.getImgUrl();
        this.category = news.getCategory();
        this.date = news.getNewsDate();
        this.heart = news.getHeartCount();
        this.tag = news.getTag();
    }
}
