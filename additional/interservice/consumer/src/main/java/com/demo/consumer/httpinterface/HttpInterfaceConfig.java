package com.demo.consumer.httpinterface;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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


    // Already RestClient.Builder bean is defined in the RestClientConfig class
    @Bean
    public ProviderHttpInterface providerHttpInterface(@LoadBalanced RestClient.Builder restClientBuilder){       //using rest client

        RestClient restClient = restClientBuilder
                .baseUrl("http://provider")
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
        return client;
    }


    // Already WebClient.Builder bean has been defined in the WebClientConfig class
//    @Bean
//    public ProviderHttpInterface providerHttpInterface(@LoadBalanced WebClient.Builder webClientBuilder){       //using web client
//
//        WebClient webClient = webClientBuilder
//                .baseUrl("http://provider")
//                .build();
//
//        WebClientAdapter adapter = WebClientAdapter.create(webClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
//        return client;
//    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//    @Bean
//    public ProviderHttpInterface providerHttpInterface(RestTemplate restTemplate){       //using rest template
//
//                    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://provider"));
//
//        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//
//        ProviderHttpInterface client = factory.createClient(ProviderHttpInterface.class);
//        return client;
//    }

}
