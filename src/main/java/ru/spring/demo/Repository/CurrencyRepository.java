package ru.spring.demo.Repository;

import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.spring.demo.Objects.Money;

import java.time.LocalDateTime;

@Repository
public class CurrencyRepository {

    @Scheduled(cron = "${interval-in-cron}")
    public void getCurrencyFromNET() {

        final RestTemplate restTemplate = new RestTemplate();
        final String jsonObject = restTemplate.getForObject("https://www.cbr-xml-daily.ru/daily_json.js", String.class);

        Gson gson = new Gson();
        Money money = gson.fromJson(jsonObject,Money.class);

        System.out.println(money.getValute());

        System.out.println(LocalDateTime.now());
    }
}
