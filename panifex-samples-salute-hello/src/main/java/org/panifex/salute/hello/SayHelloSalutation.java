package org.panifex.salute.hello;

import org.apache.aries.blueprint.annotation.Bean;
import org.apache.aries.blueprint.annotation.Service;
import org.panifex.salute.spi.SalutationService;

@Bean(id = "sayHelloSalutation")
@Service(interfaces = SalutationService.class)
public class SayHelloSalutation implements SalutationService {

    @Override
    public String salutationName() {
        return "Say hello";
    }

    @Override
    public String salute(String name) {
        return "Hello " + name + "!";
    }

    @Override
    public String toString() {
        return salutationName();
    }
}
