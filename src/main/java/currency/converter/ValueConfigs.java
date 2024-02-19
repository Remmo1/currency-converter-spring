package currency.converter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ValueConfigs {

    @Value("${currency.api.link}")
    private String apiLink;

    @Value("${frontend.address}")
    private String frontendAddress;

}
