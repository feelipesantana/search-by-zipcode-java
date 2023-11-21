package br.com.santanajs.zipcode.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Address {
  private String zipcode;
  private String street;
  private String district;
  private String city;
  private String state;
  private String country;
  
}
