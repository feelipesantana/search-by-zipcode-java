package br.com.santanajs.zipcode.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Address {
  
  @Id
  private String zipcode;

  private String street;
  private String district;
  private String city;
  private String state;
  private String country;
  
}
