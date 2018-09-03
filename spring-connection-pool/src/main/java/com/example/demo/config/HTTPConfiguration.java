package com.example.demo.config;


//TODO:확인 필요
import org.apache.http.client.HttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HTTPConfiguration {

    /*
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(60000);
        factory.setConnectTimeout(60000);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(20)
                .build();
        factory.setHttpClient(httpClient);

        return factory;
    }
    */

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate() {{
            setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder
                    .create()
                    .setConnectionManager(new PoolingHttpClientConnectionManager() {{
                        setDefaultMaxPerRoute(20);
                        setMaxTotal(200);
                    }}).build()));
        }};
    }
}
