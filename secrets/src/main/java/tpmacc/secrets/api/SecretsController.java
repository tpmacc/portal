package tpmacc.secrets.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpmacc.secrets.domain.Secret;

@RestController
@RequestMapping("/v1/secrets")
public class SecretsController {

    @GetMapping()
    Secret getOnlyTask() {
        return new Secret("Top Secret");
    }

}
