package currency.converter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConvertResponse {
    String rate;
    String exchanged;
}
