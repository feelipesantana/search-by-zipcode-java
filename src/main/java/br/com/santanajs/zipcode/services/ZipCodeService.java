package br.com.santanajs.zipcode.services;

import org.springframework.stereotype.Service;

import br.com.santanajs.zipcode.models.Address;
import br.com.santanajs.zipcode.models.Status;

@Service
public class ZipCodeService {
  
  public Status getStatus(){
    return Status.READY;
  }

  public Address getAddressByZipCode(String zipcode){
    return null;
  }

  public void setup(){

  }
}
