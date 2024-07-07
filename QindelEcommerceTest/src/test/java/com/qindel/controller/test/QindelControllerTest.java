package com.qindel.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.qindel.controller.QindelController;
import com.qindel.controller.dto.PrizesFilterRequest;
import com.qindel.controller.dto.PrizesResponse;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/** QindelControllerTest. */
@ExtendWith(MockitoExtension.class)
public class QindelControllerTest {

  /** Qindel controller. */
  @InjectMocks
  QindelController controller;
  
  /** service. */
  @Mock
  private QindelService service;
  
  /** wrapper. */
  @Mock
  private PrizesDataToPrizesResponseFunction function;
  
  /** wrapper. */
  @Mock
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
    request.setBrand("ZARA");
    request.setProductId(35455);
    request.setAppDate(format.parse("2020-06-14 01:00:00"));
    
    filter = new PrizesFilterData();
    filter.setBrand("ZARA");
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
  
  /** list all prizes. */
  @DisplayName("list all prizes")
  @Test
  public void listAllPrizes() {
    try {
      Mockito.when(service.getAllPrizes()).thenReturn(listAllPrizesData);
      Mockito.when(function.wrapList(listAllPrizesData)).thenReturn(listAllPrizesDataResponse);
      
      PrizesResponse response = controller.getPrizes();
      
      assertThat(response.getPrizesList()).isNotNull();
      assertThat(response.getPrizesList()).isNotEmpty();
      
      assertThat(response.getPrizesList().size())
          .isEqualTo(listAllPrizesDataResponse.size());
      assertThat(response.getPrizesList().get(0)).isNotNull();
      assertThat(response.getPrizesList().get(0).getBrand())
          .isEqualTo(listAllPrizesDataResponse.get(0).getBrand());
      assertThat(response.getPrizesList().get(0).getProductId())
          .isEqualTo(listAllPrizesDataResponse.get(0).getProductId());
      assertThat(response.getPrizesList().get(0).getStartDate())
          .isEqualTo(listAllPrizesDataResponse.get(0).getStartDate());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /** list prizes filtered. */
  @DisplayName("list prizes filtered")
  @Test
  public void listPrizesFiltered() {
    try {
      Mockito.when(functionFilter.wrapData(request)).thenReturn(filter);
      Mockito.when(service.getPrizesFiltered(filter)).thenReturn(listPrizesFilteredData);
      Mockito.when(function.wrapList(listPrizesFilteredData))
            .thenReturn(listPrizesFilteredDataResponse);
      
      PrizesResponse response = controller.getPrizes(request);
      assertThat(response.getPrizesList()).isNotNull();
      assertThat(response.getPrizesList()).isNotEmpty();
      
      assertThat(response.getPrizesList().size())
          .isEqualTo(listPrizesFilteredDataResponse.size());
      assertThat(response.getPrizesList().get(0)).isNotNull();
      assertThat(response.getPrizesList().get(0).getBrand())
          .isEqualTo(listPrizesFilteredDataResponse.get(0).getBrand());
      assertThat(response.getPrizesList().get(0).getProductId())
          .isEqualTo(listPrizesFilteredDataResponse.get(0).getProductId());
      assertThat(response.getPrizesList().get(0).getStartDate())
          .isEqualTo(listPrizesFilteredDataResponse.get(0).getStartDate());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  


}
