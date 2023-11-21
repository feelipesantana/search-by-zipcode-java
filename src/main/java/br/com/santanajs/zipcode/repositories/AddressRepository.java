package br.com.santanajs.zipcode.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.santanajs.zipcode.models.Address;

public interface AddressRepository extends CrudRepository<Address, String>{
  
}
