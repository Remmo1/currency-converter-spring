package currency.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final ApiProxy apiProxy;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public ConvertResponse convert(ConvertRequest convertRequest) {
        String toCurrencyLowercase = convertRequest.to.toString().toLowerCase();
        String fromCurrencyLowercase = convertRequest.from.toString().toLowerCase();
        String rate = apiProxy.getRateFromTheInternet(fromCurrencyLowercase, toCurrencyLowercase);

        log.info("Rate {} -> {} is: {}. Counting in progress...", fromCurrencyLowercase, toCurrencyLowercase, rate);
        float exchangedValue = convertRequest.amount * Float.parseFloat(rate);
        return ConvertResponse.builder().rate(rate).exchanged(decimalFormat.format(exchangedValue)).build();
    }


}
