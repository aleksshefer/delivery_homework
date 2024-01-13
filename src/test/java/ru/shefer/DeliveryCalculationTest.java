package ru.shefer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCalculationTest {

    @ParameterizedTest
    @Tag("test")
    @DisplayName("Тестs по расстоянию")
    @CsvSource({"1,550", "2,600", "5,600", "10,700", "15,700", "30,800"})
    void calculateDeliveryCost_differentDistance(int distance, double price) {
        Delivery delivery = new Delivery(distance, CargoSize.LARGE, true);
        DeliveryService deliveryService = new DeliveryService(Workload.NORMAL);
        DeliveryCalculation calculation = new DeliveryCalculation();

        double calculatedPrice = calculation.calculateDeliveryCost(delivery, deliveryService);

        assertEquals(price, calculatedPrice);
    }

    @ParameterizedTest
    @Tag("test")
    @DisplayName("Тестs по загрузке службы доставки")
    @CsvSource({"1.0,500", "1.2,600", "1.4,700", "1.6,800"})
    void calculateDeliveryCost_differentLoad(double load, double price) {
        Delivery delivery = new Delivery(30, CargoSize.LARGE, false);
        DeliveryService deliveryService = new DeliveryService(Workload.getWorkload(load));
        DeliveryCalculation calculation = new DeliveryCalculation();

        double calculatedPrice = calculation.calculateDeliveryCost(delivery, deliveryService);

        assertEquals(price, calculatedPrice);
    }

    @Test
    @Tag("test")
    @DisplayName("Тесты по хрупкости")
    void calculateDeliveryCost_notFragile() {
        Delivery delivery = new Delivery(30, CargoSize.LARGE, false);
        DeliveryService deliveryService = new DeliveryService(Workload.NORMAL);
        DeliveryCalculation calculation = new DeliveryCalculation();

        double calculatedPrice = calculation.calculateDeliveryCost(delivery, deliveryService);

        assertEquals(500, calculatedPrice);
    }

    @Test
    @Tag("test")
    @DisplayName("Тесты по хрупкости на длинную дистанцию")
    void calculateDeliveryCost_fragileLongDistance() {
        Delivery delivery = new Delivery(31, CargoSize.LARGE, true);
        DeliveryService deliveryService = new DeliveryService(Workload.NORMAL);
        DeliveryCalculation calculation = new DeliveryCalculation();

        assertThrowsExactly(UnacceptableDeliveryException.class, () -> calculation.calculateDeliveryCost(delivery, deliveryService));
    }

    @Test
    @Tag("test")
    @DisplayName("Тесты по минимальной доставке")
    void calculateDeliveryCost_minPrice() {
        Delivery delivery = new Delivery(1, CargoSize.SMALL, false);
        DeliveryService deliveryService = new DeliveryService(Workload.NORMAL);
        DeliveryCalculation calculation = new DeliveryCalculation();

        double calculatedPrice = calculation.calculateDeliveryCost(delivery, deliveryService);

        assertEquals(400, calculatedPrice);
    }

    @Test
    @Tag("test")
    @DisplayName("Тесты по измененной минимальной доставке")
    void calculateDeliveryCost_minPriceChanged() {
        Delivery delivery = new Delivery(1, CargoSize.SMALL, false);
        DeliveryService deliveryService = new DeliveryService(Workload.NORMAL);
        DeliveryCalculation calculation = new DeliveryCalculation(500);

        double calculatedPrice = calculation.calculateDeliveryCost(delivery, deliveryService);

        assertEquals(500, calculatedPrice);
    }
}