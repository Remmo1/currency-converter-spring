package currency.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@AllArgsConstructor
public class ApiProxy {

    private WebClient webClient;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "rates")
    public String getRateFromTheInternet(String from, String to) {
        log.info("Getting rate {} -> {}", from, to);
        String uri = from + "/" + to + ".json";
        var response = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonNode.get(to).toString();
    }

}
