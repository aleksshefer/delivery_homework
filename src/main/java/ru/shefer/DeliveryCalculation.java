package ru.shefer;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

@Getter
public class DeliveryCalculation {
    private final double minDeliveryCost;

    public DeliveryCalculation(double minDeliveryCost) {
        this.minDeliveryCost = minDeliveryCost;
    }

    @SneakyThrows
    public DeliveryCalculation() {
        Properties property = new Properties();
        property.load(new FileInputStream("/home/aleksandr/IdeaProjects/delivery_home_work/src/main/resources/delivery.properties"));
        minDeliveryCost = Double.parseDouble(property.getProperty("minDeliveryCost"));
    }

    public double calculateDeliveryCost(Delivery delivery, DeliveryService deliveryService) {
        double firstTerm = 0;
        double secondTerm = 0;
        double thirdTerm = 0;

        if (delivery.getDistance() < 2) {
            firstTerm = 50;
        } else if (delivery.getDistance() < 10) {
            firstTerm = 100;
        } else if (delivery.getDistance() < 30) {
            firstTerm = 200;
        } else {
            firstTerm = 300;
        }

        if (delivery.getSize() == CargoSize.LARGE) {
            secondTerm = 200;
        } else if (delivery.getSize() == CargoSize.SMALL) {
            secondTerm = 100;
        }

        if (delivery.isFragile()) {
            if (delivery.getDistance() > 30) {
                throw new UnacceptableDeliveryException("Delivery of fragile goods over a distance of more than 30 km is impossible");
            }
            thirdTerm = 300;
        }
        double deliveryPrice = (firstTerm + secondTerm + thirdTerm) * deliveryService.getWorkload().getLoadFactor();

        if (Double.compare(deliveryPrice, minDeliveryCost) < 0) {
            return minDeliveryCost;
        } else {
            return deliveryPrice;
        }
    }
}
