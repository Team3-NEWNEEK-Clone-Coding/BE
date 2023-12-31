package com.example.newnique.newsletter.service;

import com.example.newnique.news.entity.News;
import com.example.newnique.news.repository.NewsRepository;
import com.example.newnique.newsletter.dto.Maildto;
import com.example.newnique.newsletter.dto.SubscriptionRequestDto;
import com.example.newnique.newsletter.entity.Subscription;
import com.example.newnique.newsletter.handler.EmailSender;
import com.example.newnique.newsletter.repository.SubscriptionRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final NewsRepository newsRepository;
    private final EmailSender emailSender;
    public Subscription createSub(SubscriptionRequestDto subscriptionRequestDto) throws MessagingException {
        Subscription subscription = new Subscription(subscriptionRequestDto);
        Subscription saveSubscription = subscriptionRepository.save(subscription);
        News news = newsRepository.findTopByOrderByIdDesc();

        Maildto maildto = Maildto.builder().title(subscriptionRequestDto.getNickName()+"님 환영합니다")
                .message("뉴닉을 구독해주신걸 환영합니다 !\n\n\n"+news.getTitle() + "\n\n\n"+news.getNewsSummary())
                .toAddress(subscriptionRequestDto.getEmail()).build();
        emailSender.sendMail(maildto);

        return saveSubscription;

    }
}
