package ua.lviv.javaclub.annotation.processor.example;

public class Main {

  public static void main(String[] args) {
    Address address = AddressBuilder.builder()
        .city("Lviv")
        .street("Rynok sq.")
        .building("8a")
        .build();

    CoffeeShop coffeeShop = CoffeeShopBuilder.builder()
        .address(address)
        .capacity(80)
        .sellsAlcohol(true)
        .name("Svit Kavy")
        .build();

    System.out.println(coffeeShop.toString());
  }
}
