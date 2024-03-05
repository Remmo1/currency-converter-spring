package currency.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class ApiProxy {

    private WebClient webClient;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "rates")
    public String getRateFromTheInternet(String from, String to) {
        log.info("Getting rate {} -> {}", from, to);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.d");

        String uri = "${date}/v1/currencies/${from}.json"
                .replace("${date}", formatter.format(LocalDate.now()))
                .replace("${from}", from);

        var response = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonNode.get(from).get(to).toString();
    }

}
