package com.mechague.tacocloud.runner;

import com.mechague.tacocloud.webclient.WebClientCall;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebClientRunner implements CommandLineRunner {

    private WebClientCall webClientCall;

    public WebClientRunner(WebClientCall webClientCall){
        this.webClientCall = webClientCall;
    }

    @Override
    public void run(String... args){
        webClientCall.makeCalls();
    }

}
