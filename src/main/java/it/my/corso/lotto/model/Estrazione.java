package it.my.corso.lotto.model;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
public class Estrazione {
    private LocalDateTime ora;
    private List<Integer> numeri;

}
