package currency.converter;

import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
@EnableCaching
public class CorsSecurityConfig {

    ValueConfigs valueConfigs;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(valueConfigs.getApiLink()).build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(valueConfigs.getFrontendAddress())
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                        .allowCredentials(true)
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("rates");
    }

}
