package br.com.santanajs.zipcode.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.santanajs.zipcode.models.AddressStatus;

public interface AddressStatusRepository extends CrudRepository<AddressStatus, Integer>{
  
}
