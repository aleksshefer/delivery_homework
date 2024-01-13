package ru.shefer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Delivery {
    private int distance;
    private CargoSize size;
    private boolean fragile;
}
