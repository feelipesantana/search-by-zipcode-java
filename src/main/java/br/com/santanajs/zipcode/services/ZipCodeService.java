package br.com.santanajs.zipcode.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.santanajs.zipcode.ZipcodeApplication;
import br.com.santanajs.zipcode.exceptions.NoContentException;
import br.com.santanajs.zipcode.exceptions.NotReadyException;
import br.com.santanajs.zipcode.models.Address;
import br.com.santanajs.zipcode.models.AddressStatus;
import br.com.santanajs.zipcode.models.Status;
import br.com.santanajs.zipcode.repositories.AddressRepository;
import br.com.santanajs.zipcode.repositories.AddressStatusRepository;
import br.com.santanajs.zipcode.repositories.SetupRepository;

@Service
public class ZipCodeService {

  private static Logger logger = LoggerFactory.getLogger(ZipCodeService.class);

  @Autowired
  private AddressRepository addressRepository;
  
  @Autowired
  private AddressStatusRepository addressStatusRepository;

  @Autowired
  private SetupRepository setupRepository;

  public Status getStatus(){
   AddressStatus addressStatus = new AddressStatus();
   addressStatus.setStatus(Status.NEED_SETUP);

    return this.addressStatusRepository.findById(AddressStatus.DEFAULT_ID).orElse(addressStatus).getStatus();
    

  }

  public  Address getAddressByZipCode(String zipcode) throws NoContentException, NotReadyException{
    
    if(!this.getStatus().equals(Status.READY)) throw new NotReadyException();

    return addressRepository.findById(zipcode).orElseThrow(NoContentException::new);
     
  }

  private void saveStatus(Status status){

    AddressStatus addressStatus = new AddressStatus();
    addressStatus.setId(addressStatus.DEFAULT_ID);
    addressStatus.setStatus(status);
    this.addressStatusRepository.save(addressStatus);

  }
  @EventListener(ApplicationReadyEvent.class)
  protected void setupOnSturtup(){
    try{
      this.setup();

    }catch(Exception err){
      ZipcodeApplication.close(999);
      logger.error("Exception Error: ", err);
    }
  }

  public void setup() throws Exception{
    logger.info("----");
    logger.info("----");
    logger.info("---- Setup Running");
    logger.info("----");
     logger.info("----");
    if(this.getStatus().equals(Status.NEED_SETUP)){
      this.saveStatus(Status.RUNNING_SETUP);

      try{
        this.addressRepository.saveAll(this.setupRepository.getFromOrigin());
        this.saveStatus(Status.READY);

      }catch(Exception err){
        this.saveStatus(Status.NEED_SETUP);
        throw err;
      }
      
    }

      logger.info("----");
      logger.info("----");
      logger.info("---- SERVICE READY");
      logger.info("----");
      logger.info("----");
  }
}
