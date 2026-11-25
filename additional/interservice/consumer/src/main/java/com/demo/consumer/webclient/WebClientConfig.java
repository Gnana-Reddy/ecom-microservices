package com.demo.consumer.webclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    @Scope("prototype")
    public WebClient.Builder loadBalancedWebClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient getWebClient(@LoadBalanced WebClient.Builder builder){
        return  builder
                .baseUrl("http://provider")
                .build();
    }
}
