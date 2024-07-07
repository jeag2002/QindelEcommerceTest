package com.qindel.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.qindel.controller.QindelController;
import com.qindel.controller.dto.PrizesFilterRequest;
import com.qindel.controller.dto.PrizesResponseDto;
import com.qindel.dto.PrizesData;
import com.qindel.dto.PrizesFilterData;
import com.qindel.service.QindelService;
import com.qindel.wrapper.PrizesDataToPrizesResponseFunction;
import com.qindel.wrapper.PrizesFilterRequestToPrizesFilterDataFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/** Controller External Test. */
@WebMvcTest(QindelController.class)
public class QindelControllerExternalTest {
  
  /** mockMvc. */
  @Autowired
  private MockMvc mockMvc;
  
  /** service. */
  @MockBean
  private QindelService service;
  
  /** wrapper. */
  @MockBean
  private PrizesDataToPrizesResponseFunction function;
  
  /** wrapper. */
  @MockBean
  private PrizesFilterRequestToPrizesFilterDataFunction functionFilter;
  
  /** prizes filter. */
  private PrizesFilterData filter;
  
  /** responseStr. */
  private String responseAllStr;
  
  /** responseSomeStr. */
  private String responseSomeStr;
  
  /** filter. */
  private PrizesFilterRequest request;
  
  /** List all. */
  private List<PrizesData> listAllPrizesData;
  
  /** List prizes filtered data. */
  private List<PrizesData> listPrizesFilteredData;
  
  /** List all external. */
  private List<PrizesResponseDto> listAllPrizesDataResponse;
  
  /** List prizes external external filtered. */
  private List<PrizesResponseDto> listPrizesFilteredDataResponse;
  
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    request = new PrizesFilterRequest();
    request.setBrand("ZAR");
    request.setProductId(35455);
    request.setAppDate(format.parse("2020-06-14 01:00:00"));
    
    filter = new PrizesFilterData();
    filter.setBrand("ZAR");
    filter.setProductId(35455);
    filter.setAppDate(format.parse("2020-06-14 01:00:00"));
    
    responseAllStr = "{\"prizesList\":[{\"id\":1,\"startDate\":\"2020-06-14 00:00:00\","
            + "\"endDate\":\"2020-12-31 23:59:59\",\"priceList\":1,\"productId\":35455,"
            + "\"priority\":0,\"price\":35.50,\"currency\":\"EUR\",\"brand\":\"ZARA\"},"
            + "{\"id\":2,\"startDate\":\"2020-06-14 15:00:00\",\"endDate\":\"2020-06-14 18:30:00\""
            + ",\"priceList\":2,\"productId\":35455,\"priority\":1,\"price\":25.45,"
            + "\"currency\":\"EUR\",\"brand\":\"ZARA\"},{\"id\":3,"
            + "\"startDate\":\"2020-06-15 00:00:00\",\"endDate\":\"2020-06-15 11:00:00\","
            + "\"priceList\":3,\"productId\":35455,\"priority\":1,"
            + "\"price\":30.50,\"currency\":\"EUR\",\"brand\":\"ZARA\"},{\"id\":4,"
            + "\"startDate\":\"2020-06-15 16:00:00\",\"endDate\":\"2020-12-31 23:59:59\","
            + "\"priceList\":4,"
            + "\"productId\":35455,\"priority\":1,\"price\":38.95,\"currency\":\"EUR\""
            + ",\"brand\":\"ZARA\"}]}";
    
    responseSomeStr = "{\"prizesList\":[{\"id\":1,\"startDate\":\"2020-06-14 00:00:00\","
            + "\"endDate\":\"2020-12-31 23:59:59\",\"priceList\":1,\"productId\":35455,"
            + "\"priority\":0,\"price\":35.50,\"currency\":\"EUR\",\"brand\":\"ZARA\"}]}";
    
    listPrizesFilteredData = new ArrayList<PrizesData>();
    
    PrizesData data = new PrizesData();
    data.setId(1L);
    data.setBrand("ZARA");
    data.setStartDate(format.parse("2020-06-14 00:00:00"));
    data.setEndDate(format.parse("2020-12-31 23:59:59"));
    data.setCurrency("EUR");
    data.setProductId(35455);
    data.setPriority(0);
    data.setPrice(35.50f);
    data.setPriceList(1);
    listPrizesFilteredData.add(data);
    
    listAllPrizesData = new ArrayList<PrizesData>();
    
    data = new PrizesData();
    data.setId(1L);
    data.setBrand("ZARA");
    data.setStartDate(format.parse("2020-06-14 00:00:00"));
    data.setEndDate(format.parse("2020-12-31 23:59:59"));
    data.setCurrency("EUR");
    data.setProductId(35455);
    data.setPriority(0);
    data.setPrice(35.50f);
    data.setPriceList(1);
    listAllPrizesData.add(data);
    
    data = new PrizesData();
    data.setId(2L);
    data.setBrand("ZARA");
    data.setStartDate(format.parse("2020-06-14 15:00:00"));
    data.setEndDate(format.parse("2020-06-14 18:30:00"));
    data.setCurrency("EUR");
    data.setProductId(35455);
    data.setPriority(1);
    data.setPrice(25.45f);
    data.setPriceList(2);
    listAllPrizesData.add(data);
    
    data = new PrizesData();
    data.setId(3L);
    data.setBrand("ZARA");
    data.setStartDate(format.parse("2020-06-15 00:00:00"));
    data.setEndDate(format.parse("2020-06-15 11:00:00"));
    data.setCurrency("EUR");
    data.setProductId(35455);
    data.setPriority(1);
    data.setPrice(30.50f);
    data.setPriceList(3);
    listAllPrizesData.add(data);
    
    data = new PrizesData();
    data.setId(4L);
    data.setBrand("ZARA");
    data.setStartDate(format.parse("2020-06-15 16:00:00"));
    data.setEndDate(format.parse("2020-12-31 23:59:59"));
    data.setCurrency("EUR");
    data.setProductId(35455);
    data.setPriority(1);
    data.setPrice(38.95f);
    data.setPriceList(4);
    listAllPrizesData.add(data);
    
    listPrizesFilteredDataResponse = new ArrayList<PrizesResponseDto>();
    
    PrizesResponseDto dto = new PrizesResponseDto();
    dto.setId(1L);
    dto.setBrand("ZARA");
    dto.setStartDate(format.parse("2020-06-14 00:00:00"));
    dto.setEndDate(format.parse("2020-12-31 23:59:59"));
    dto.setCurrency("EUR");
    dto.setProductId(35455);
    dto.setPriority(0);
    dto.setPrice(35.50f);
    dto.setPriceList(1);
    listPrizesFilteredDataResponse.add(dto);
    
    
    listAllPrizesDataResponse = new ArrayList<PrizesResponseDto>();
    
    dto = new PrizesResponseDto();
    dto.setId(1L);
    dto.setBrand("ZARA");
    dto.setStartDate(format.parse("2020-06-14 00:00:00"));
    dto.setEndDate(format.parse("2020-12-31 23:59:59"));
    dto.setCurrency("EUR");
    dto.setProductId(35455);
    dto.setPriority(0);
    dto.setPrice(35.50f);
    dto.setPriceList(1);
    listAllPrizesDataResponse.add(dto);
    
    dto = new PrizesResponseDto();
    dto.setId(2L);
    dto.setBrand("ZARA");
    dto.setStartDate(format.parse("2020-06-14 15:00:00"));
    dto.setEndDate(format.parse("2020-06-14 18:30:00"));
    dto.setCurrency("EUR");
    dto.setProductId(35455);
    dto.setPriority(1);
    dto.setPrice(25.45f);
    dto.setPriceList(2);
    listAllPrizesDataResponse.add(dto);
    
    dto = new PrizesResponseDto();
    dto.setId(3L);
    dto.setBrand("ZARA");
    dto.setStartDate(format.parse("2020-06-15 00:00:00"));
    dto.setEndDate(format.parse("2020-06-15 11:00:00"));
    dto.setCurrency("EUR");
    dto.setProductId(35455);
    dto.setPriority(1);
    dto.setPrice(30.50f);
    dto.setPriceList(3);
    listAllPrizesDataResponse.add(dto);
    
    dto = new PrizesResponseDto();
    dto.setId(4L);
    dto.setBrand("ZARA");
    dto.setStartDate(format.parse("2020-06-15 16:00:00"));
    dto.setEndDate(format.parse("2020-12-31 23:59:59"));
    dto.setCurrency("EUR");
    dto.setProductId(35455);
    dto.setPriority(1);
    dto.setPrice(38.95f);
    dto.setPriceList(4);
    listAllPrizesDataResponse.add(dto);
  }
  
  @DisplayName("get all prizes")
  @Test
  public void getAllPrizes() {
    try {
      
      Mockito.when(service.getAllPrizes()).thenReturn(listAllPrizesData);
      Mockito.when(function.wrapList(listAllPrizesData)).thenReturn(listAllPrizesDataResponse);
      
      this.mockMvc
      .perform(get("/rest/listPrizes")
      .accept(MediaType.APPLICATION_JSON_UTF8)
      .contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.content().json(responseAllStr));
      
      Mockito.verify(service).getAllPrizes();
      Mockito.verify(function).wrapList(listAllPrizesData);
      
    } catch (Exception e) {
      e.getStackTrace();
    }

  }
  
  @DisplayName("get prizes filtered")
  @Test
  public void getPrizesFiltered() {
    try {
      
      Mockito.when(functionFilter.wrapData(request)).thenReturn(filter);
      Mockito.when(service.getPrizesFiltered(filter)).thenReturn(listPrizesFilteredData);
      Mockito.when(function.wrapList(listPrizesFilteredData))
          .thenReturn(listPrizesFilteredDataResponse);
      
      this.mockMvc
      .perform(
       get("/rest/listPrizesFiltered?brand=ZAR&productId=35455&appDate=2020-06-14 01:00:00")
      .accept(MediaType.APPLICATION_JSON_UTF8)
      .contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.content().json(responseSomeStr));
      
      Mockito.verify(functionFilter).wrapData(request);
      Mockito.verify(service).getPrizesFiltered(filter);
      Mockito.verify(function).wrapList(listPrizesFilteredData);
      
    } catch (Exception e) {
      e.getStackTrace();
    }

  }
  

}
