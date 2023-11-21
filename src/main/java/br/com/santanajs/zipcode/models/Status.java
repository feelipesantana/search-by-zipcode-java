package br.com.santanajs.zipcode.models;

public enum Status {
  NEED_SETUP, //  Pecisa Baixa o CSV dos Correios
  RUNNING_SETUP,// Esta baixando salvando no banco
  READY; // Serviço está apito a ser consumido

}
