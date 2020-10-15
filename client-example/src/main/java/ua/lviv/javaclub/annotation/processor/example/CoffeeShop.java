package ua.lviv.javaclub.annotation.processor.example;

import ua.lviv.javaclub.annotation.processor.Builder;

@Builder
public class CoffeeShop {
  private String name;
  private Integer capacity;
  private Address address;
  private boolean sellsAlcohol;

  public CoffeeShop(String name, Integer capacity, Address address, boolean sellsAlcohol) {
    this.name = name;
    this.capacity = capacity;
    this.address = address;
    this.sellsAlcohol = sellsAlcohol;
  }

  @Override
  public String toString() {
    return "CoffeeShop{" +
        "name='" + name + '\'' +
        ", capacity=" + capacity +
        ", address=" + address +
        ", sellsAlcohol=" + sellsAlcohol +
        '}';
  }
}
