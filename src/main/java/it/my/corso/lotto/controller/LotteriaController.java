package it.my.corso.lotto.controller;

import it.my.corso.lotto.model.Coord;
import it.my.corso.lotto.model.Estrazione;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

@CrossOrigin
@RestController
public class LotteriaController {

    @GetMapping(value = "/estrai", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Estrazione> randomNumbers() {
        final Random random = new Random(100);
        return Flux.interval(Duration.ofSeconds(2)).map(pulse -> {
            Estrazione ret = new Estrazione();
            ret.setOra(LocalDateTime.now());
            ret.setNumeri(new ArrayList<>());
            while (ret.getNumeri().size() < 5) {
                Integer number = random.nextInt(90);
                if (ret.getNumeri().indexOf(number) < 0)
                    ret.getNumeri().add(number);
            }
            return ret;
        });
    }

    @GetMapping(value = "/getPos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Coord> getPositions() {
        final Random random = new Random(2);
        return Flux.interval(Duration.ofSeconds(2)).map(pulse -> {
            Coord ret = new Coord();
            ret.setLat(51.513 + random.nextDouble() );
            ret.setLongi(-0.09 + random.nextDouble() );
            return ret;
        });
    }

}
