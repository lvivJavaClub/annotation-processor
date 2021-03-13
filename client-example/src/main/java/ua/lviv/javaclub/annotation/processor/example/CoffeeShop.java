package ua.lviv.javaclub.annotation.processor.example;

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
}
