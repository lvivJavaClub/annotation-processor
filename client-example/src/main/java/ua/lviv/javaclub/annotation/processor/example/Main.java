package ua.lviv.javaclub.annotation.processor.example;

public class Main {

  public static void main(String[] args) {
    Address address = AddressBuilder.builder()
        .city("Львів")
        .street("Площа Ринок")
        .building("8a")
        .build();

    CoffeeShop coffeeShop = CoffeeShopBuilder.builder()
        .address(address)
        .capacity(80)
        .sellsAlcohol(true)
        .name("Світ Кави")
        .build();

    System.out.println(coffeeShop.toString());
  }
}
