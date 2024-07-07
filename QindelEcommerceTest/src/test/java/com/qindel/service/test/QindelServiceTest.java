package com.qindel.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.qindel.dto.PrizesData;
import com.qindel.dto.PrizesFilterData;
import com.qindel.jpa.PrizesRepository;
import com.qindel.jpa.dto.Brand;
import com.qindel.jpa.dto.Prizes;
import com.qindel.jpa.helper.PrizesPredicates;
import com.qindel.jpa.helper.PrizesSpecification;
import com.qindel.service.impl.QindelServiceImpl;
import com.qindel.validation.InputFieldValidation;
import com.qindel.validation.impl.InputFieldValidationImpl;
import com.qindel.wrapper.PrizesToPrizesDataFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/** QindelServiceTest. */
@Slf4j
public class QindelServiceTest {
  
  /** QindelServiceImpl. */
  @InjectMocks
  QindelServiceImpl qindelService;
  
  /** predicates. */
  @Spy
  private PrizesPredicates predicates;
  
  /** filters. */
  @Spy
  private PrizesSpecification filter;
  
  /** validation. */
  @Spy
  private InputFieldValidation validation;
  
  /** repository. */
  @Mock
  private PrizesRepository repository;
  
  /** conversion function. */
  @Mock
  private PrizesToPrizesDataFunction function;
  
  /** listPrizes. */
  private List<Prizes> listPrizes;
  
  /** listPrizesData. */
  private List<PrizesData> listPrizesData;
  
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    
    MockitoAnnotations.initMocks(this);
    
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    filter = new PrizesSpecification();
    predicates = new PrizesPredicates();
    validation = new InputFieldValidationImpl();
    
    final SimpleDateFormat tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    Brand brand = new Brand();
    brand.setId(1L);
    brand.setBrandName("ZARA");
    
    listPrizesData = new ArrayList<PrizesData>();
    PrizesData dataOne = new PrizesData();
    dataOne.setId(1L);
    dataOne.setCurrency("EUR");
    dataOne.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
    dataOne.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
    dataOne.setPriority(0);
    dataOne.setProductId(35455);
    dataOne.setPrice(35.5f);
    dataOne.setPriceList(1);
    dataOne.setBrand("ZARA");
    listPrizesData.add(dataOne);
    
    PrizesData dataTwo = new PrizesData();
    dataTwo.setId(2L);
    dataTwo.setCurrency("EUR");
    dataTwo.setEndDate(tmpDate.parse("2020-06-14 18:30:00"));
    dataTwo.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
    dataTwo.setPriority(1);
    dataTwo.setProductId(35455);
    dataTwo.setPrice(25.45f);
    dataTwo.setPriceList(2);
    dataTwo.setBrand("ZARA");
    listPrizesData.add(dataTwo);
    
    PrizesData dataThree = new PrizesData();
    dataThree.setId(3L);
    dataThree.setCurrency("EUR");
    dataThree.setEndDate(tmpDate.parse("2020-06-15 11:00:00"));
    dataThree.setStartDate(tmpDate.parse("2020-06-15 00:00:00"));
    dataThree.setPriority(1);
    dataThree.setProductId(35455);
    dataThree.setPrice(30.50f);
    dataThree.setPriceList(3);
    dataThree.setBrand("ZARA");
    listPrizesData.add(dataThree);
    
    PrizesData dataFour = new PrizesData();
    dataFour.setId(4L);
    dataFour.setCurrency("EUR");
    dataFour.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
    dataFour.setStartDate(tmpDate.parse("2020-06-15 16:00:00"));
    dataFour.setPriority(1);
    dataFour.setProductId(35455);
    dataFour.setPrice(38.95f);
    dataFour.setPriceList(4);
    dataFour.setBrand("ZARA");
    listPrizesData.add(dataFour);
    
    
    listPrizes = new ArrayList<Prizes>();
    
    Prizes prOne = new Prizes();
    prOne.setId(1L);
    prOne.setCurrency("EUR");
    prOne.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
    prOne.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
    prOne.setPriority(0);
    prOne.setProductId(35455);
    prOne.setPrice(35.5f);
    prOne.setPriceList(1);
    prOne.setBrand(brand);
    listPrizes.add(prOne);
    
    Prizes prTwo = new Prizes();
    prTwo.setId(2L);
    prTwo.setCurrency("EUR");
    prTwo.setEndDate(tmpDate.parse("2020-06-14 18:30:00"));
    prTwo.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
    prTwo.setPriority(1);
    prTwo.setProductId(35455);
    prTwo.setPrice(25.45f);
    prTwo.setPriceList(2);
    prTwo.setBrand(brand);
    listPrizes.add(prTwo);
    
    Prizes prThree = new Prizes();
    prThree.setId(3L);
    prThree.setCurrency("EUR");
    prThree.setEndDate(tmpDate.parse("2020-06-15 11:00:00"));
    prThree.setStartDate(tmpDate.parse("2020-06-15 00:00:00"));
    prThree.setPriority(1);
    prThree.setProductId(35455);
    prThree.setPrice(30.50f);
    prThree.setPriceList(3);
    prThree.setBrand(brand);
    listPrizes.add(prThree);
    
    Prizes prFour = new Prizes();
    prFour.setId(4L);
    prFour.setCurrency("EUR");
    prFour.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
    prFour.setStartDate(tmpDate.parse("2020-06-15 16:00:00"));
    prFour.setPriority(1);
    prFour.setProductId(35455);
    prFour.setPrice(38.95f);
    prFour.setPriceList(4);
    prFour.setBrand(brand);
    listPrizes.add(prFour);
  }
  
  /** filter is null.*/
  @DisplayName("Process List")
  @Test
  public void criteriaList() {
    Mockito.when(repository.findAll(Sort.by(Sort.Direction.DESC, "priority")))
           .thenReturn(listPrizes);
    Mockito.when(function.wrapList(listPrizes)).thenReturn(listPrizesData);
    
    List<PrizesData> prizes = qindelService.getAllPrizes();
    assertThat(prizes).isNotNull();
    assertThat(prizes).isNotEmpty();
    assertThat(prizes.size()).isEqualTo(4);
    assertThat(prizes.get(0).getPrice()).isEqualTo(35.5f);
    assertThat(prizes.get(1).getPrice()).isEqualTo(25.45f);
    assertThat(prizes.get(2).getPrice()).isEqualTo(30.50f);
    assertThat(prizes.get(3).getPrice()).isEqualTo(38.95f);
  }
  
  @DisplayName("Process List Filtered")
  @Test
  public void criteriaListFiltered() {
    try {
      SimpleDateFormat tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      PrizesFilterData filterData = new PrizesFilterData();
      filterData.setProductId(35455);
      filterData.setAppDate(tmpDate.parse("2020-06-14 10:00:00"));
      filterData.setBrand("ZARA");
        
      Brand brand = new Brand();
      brand.setId(1L);
      brand.setBrandName("ZARA");
        
      final ArrayList listPrizesTest = new ArrayList<Prizes>();
      Prizes prOne = new Prizes();
      prOne.setId(1L);
      prOne.setCurrency("EUR");
      prOne.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
      prOne.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
      prOne.setPriority(0);
      prOne.setProductId(35455);
      prOne.setPrice(35.5f);
      prOne.setPriceList(1);
      prOne.setBrand(brand);
      listPrizesTest.add(prOne);
        
      final ArrayList listPrizesDataTest = new ArrayList<PrizesData>();
      PrizesData dataOne = new PrizesData();
      dataOne.setId(1L);
      dataOne.setCurrency("EUR");
      dataOne.setEndDate(tmpDate.parse("2020-12-31 23:59:59"));
      dataOne.setStartDate(tmpDate.parse("2020-06-14 00:00:00"));
      dataOne.setPriority(0);
      dataOne.setProductId(35455);
      dataOne.setPrice(35.5f);
      dataOne.setPriceList(1);
      dataOne.setBrand("ZARA");
      listPrizesDataTest.add(dataOne);
        
        
      Mockito.when(repository
              .findAll(Mockito.any(Specification.class), 
               Mockito.any(Sort.class)))
               .thenReturn(listPrizesTest);
      Mockito.when(function.wrapList(listPrizesTest)).thenReturn(listPrizesDataTest);
        
      List<PrizesData> prizes = qindelService.getPrizesFiltered(filterData);
      assertThat(prizes).isNotNull();
      assertThat(prizes).isNotEmpty();
      assertThat(prizes.size()).isEqualTo(1);
      assertThat(prizes.get(0).getPrice()).isEqualTo(35.5f);
        
    } catch (Exception e) {
      log.info("[TEST] something happened");
    }
  }
  
  

}
