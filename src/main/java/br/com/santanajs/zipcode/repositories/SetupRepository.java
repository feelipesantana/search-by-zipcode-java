package br.com.santanajs.zipcode.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import br.com.santanajs.zipcode.models.Address;

@Repository
public class SetupRepository {

  @Value("${csvaddesses.base.url}")
  private String url;

  public List<Address> getFromOrigin() throws Exception{
    List<Address> resultList = new ArrayList<>();

    String resultStr = "";


    try (CloseableHttpClient httpClient = HttpClients.createDefault(); 
          CloseableHttpResponse response = httpClient.execute(new HttpGet(this.url))){
      HttpEntity entity = response.getEntity();
      resultStr = EntityUtils.toString(entity);

    }

    String[] resultsSplited = resultStr.split("\n");

    for(String currentLine : resultsSplited){
      String[] currentLineSplited = currentLine.split(",");
      
      Address address = new Address();

      

      address.setState(currentLineSplited[0]);
      address.setCity(currentLineSplited[1]);
      address.setDistrict(currentLineSplited[2]);
      address.setZipcode(StringUtils.leftPad(currentLineSplited[3], 8, "0"));
      address.setStreet(currentLineSplited.length > 4 ? currentLineSplited[4] : null);
      resultList.add(address);
    }
     return resultList;
  }
  
}
