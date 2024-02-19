package currency.converter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ConvertRequest {
    @Positive
    @NotNull
    Float amount;

    @NotNull
    Currency from;

    @NotNull
    Currency to;
}
