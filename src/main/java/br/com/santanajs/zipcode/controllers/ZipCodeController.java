package br.com.santanajs.zipcode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.santanajs.zipcode.exceptions.NoContentException;
import br.com.santanajs.zipcode.exceptions.NotReadyException;
import br.com.santanajs.zipcode.models.Address;
import br.com.santanajs.zipcode.services.ZipCodeService;



@RestController
public class ZipCodeController {
  
  @Autowired
  private ZipCodeService service;

  @GetMapping("/status")
  public String getStatus(){
    return "Service status: " + service.getStatus();
  }

  @GetMapping("/zipcode/{zipcode}")
  public Address getAddressByZipCode(@PathVariable(value="zipcode") String zipcode) throws NoContentException, NotReadyException{
    return this.service.getAddressByZipCode(zipcode);
  }

} 
