package currency.converter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exchange")
@AllArgsConstructor
@ControllerAdvice
public class AppController {

    private final AppService appService;

    @PostMapping
    public ResponseEntity<ConvertResponse> exchangeMoney(@Valid @RequestBody ConvertRequest convertRequest) {
        return ResponseEntity.ok(appService.convert(convertRequest));
    }

}
