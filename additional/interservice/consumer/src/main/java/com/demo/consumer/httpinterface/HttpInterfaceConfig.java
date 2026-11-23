package com.demo.consumer.httpinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpInterfaceConfig {


//    @Bean
//    public ProviderHttpInterface providerHttpInterface(){       //using rest client
//
//        RestClient restClient = RestClient.builder()
//                .baseUrl("http://localhost:8081")
//                .build();
//
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
//        return client;
//    }

//    @Bean
//    public ProviderHttpInterface providerHttpInterface(){       //using web client
//
//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8081")
//                .build();
//
//        WebClientAdapter adapter = WebClientAdapter.create(webClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
//        return client;
//    }

    @Bean
    public ProviderHttpInterface providerHttpInterface(){       //using rest template

        RestTemplate restTemplate = new RestTemplate();
                    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));

        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
        return client;
    }
}
