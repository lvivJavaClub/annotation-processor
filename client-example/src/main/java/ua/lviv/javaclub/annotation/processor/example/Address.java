package ua.lviv.javaclub.annotation.processor.example;

import ua.lviv.javaclub.annotation.processor.Builder;

@Builder
public class Address {
  private String city;
  private String street;
  private String building;

  public Address(String city, String street, String building) {
    this.city = city;
    this.street = street;
    this.building = building;
  }

  @Override
  public String toString() {
    return "Address{" +
        "city='" + city + '\'' +
        ", street='" + street + '\'' +
        ", building='" + building + '\'' +
        '}';
  }
}
