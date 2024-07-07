package com.qindel.jpa.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.qindel.dto.Predicate;
import com.qindel.dto.PrizesFilterData;
import com.qindel.jpa.PrizesRepository;
import com.qindel.jpa.dto.Prizes;
import com.qindel.jpa.helper.PrizesPredicates;
import com.qindel.jpa.helper.PrizesSpecification;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;


/** QindelServiceTest. */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class QindelJpaTest {

  /** repository. */
  @Autowired
  PrizesRepository repository;
  
  /** Date 1 Ini. */
  private Date dateOneIni;
  
  /** Date 1 End. */
  private Date dateOneEnd;
  
  /** Date 2 Ini. */
  private Date dateTwoIni;
  
  /** Date 2 End. */
  private Date dateTwoEnd;
  
  /** Date 3 Ini. */
  private Date dateThreeIni;
  
  /** Date 4 Ini. */
  private Date dateFourIni;
 
  /** filterOne. */
  private Specification<Prizes> filterOne;
  
  /** filterTwo. */
  private Specification<Prizes> filterTwo;
  
  /** filterThree. */
  private Specification<Prizes> filterThree;
  
  /** filterFour. */
  private Specification<Prizes> filterFour;
  
  /** filterFive. */
  private Specification<Prizes> filterFive;
  
  /** filterLike. */
  private Specification<Prizes> filterLike;
  
  /** filterLikeNotMatch. */
  private Specification<Prizes> filterLikeNotMatch;
  
  /** filterNotFound. */
  private Specification<Prizes> filterNotFound;
  
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SimpleDateFormat tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateOneIni = tmpDate.parse("2020-06-14 00:00:00");
    dateOneEnd = tmpDate.parse("2020-12-31 23:59:59");
    dateTwoIni = tmpDate.parse("2020-06-14 15:00:00");
    dateTwoEnd = tmpDate.parse("2020-06-14 18:30:00");
    dateThreeIni = tmpDate.parse("2020-06-15 00:00:00");
    dateFourIni = tmpDate.parse("2020-06-15 16:00:00");
    
    final PrizesSpecification filter = new PrizesSpecification();
    final PrizesPredicates predicates = new PrizesPredicates();
    
    PrizesFilterData filtOne = new PrizesFilterData();
    filtOne.setProductId(35455);
    filtOne.setAppDate(tmpDate.parse("2020-06-14 10:00:00"));
    filtOne.setBrand("SPRINGFIELD");
    List<Predicate> predOne = predicates.processPredicates(filtOne);
    filterOne = filter.buildFilter(predOne);
    
    PrizesFilterData filtTwo = new PrizesFilterData();
    filtTwo.setProductId(35455);
    filtTwo.setAppDate(tmpDate.parse("2020-06-14 16:00:00"));
    filtTwo.setBrand("SPRINGFIELD");
    List<Predicate> predTwo = predicates.processPredicates(filtTwo);
    filterTwo = filter.buildFilter(predTwo);
    
    PrizesFilterData filtThree = new PrizesFilterData();
    filtThree.setProductId(35455);
    filtThree.setAppDate(tmpDate.parse("2020-06-14 21:00:00"));
    filtThree.setBrand("SPRINGFIELD");
    List<Predicate> predThree = predicates.processPredicates(filtThree);
    filterThree = filter.buildFilter(predThree);
    
    PrizesFilterData filtFour = new PrizesFilterData();
    filtFour.setProductId(35455);
    filtFour.setAppDate(tmpDate.parse("2020-06-15 10:00:00"));
    filtFour.setBrand("SPRINGFIELD");
    List<Predicate> predFour = predicates.processPredicates(filtFour);
    filterFour = filter.buildFilter(predFour);
    
    PrizesFilterData filtFive = new PrizesFilterData();
    filtFive.setProductId(35455);
    filtFive.setAppDate(tmpDate.parse("2020-06-16 21:00:00"));
    filtFive.setBrand("SPRINGFIELD");
    List<Predicate> predFive = predicates.processPredicates(filtFive);
    filterFive = filter.buildFilter(predFive);
    
    PrizesFilterData filtLike = new PrizesFilterData();
    filtLike.setBrand("SPRING");
    List<Predicate> predLike = predicates.processPredicates(filtLike);
    filterLike = filter.buildFilter(predLike);
    
    PrizesFilterData filtLikeNotMatch = new PrizesFilterData();
    filtLikeNotMatch.setBrand("ZAR");
    List<Predicate> predLikeNotMatch = predicates.processPredicates(filtLikeNotMatch);
    filterLikeNotMatch = filter.buildFilter(predLikeNotMatch);
    
    PrizesFilterData filtNotFound = new PrizesFilterData();
    filtNotFound.setProductId(0);
    List<Predicate> predfiltNotFound = predicates.processPredicates(filtNotFound);
    filterNotFound = filter.buildFilter(predfiltNotFound);
    
  }
  
  /** list of prizes filtered five. */
  @DisplayName("Obtain prizes filtered 2020-06-16 21:00:00")
  @Test
  void processFilterFive() {
    List<Prizes> prizesLst = repository.findAll(filterFive, 
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(2);
    assertThat(prizesLst.get(1).getStartDate().toInstant()).isEqualTo(dateOneIni.toInstant());
    assertThat(prizesLst.get(1).getPrice()).isEqualTo(35.50f);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateFourIni.toInstant());
    assertThat(prizesLst.get(0).getPrice()).isEqualTo(38.95f);
  }
 
  /** list of prizes filtered four. */
  @DisplayName("Obtain prizes filtered 2020-06-15 10:00:00")
  @Test
  void processFilterFour() {
    List<Prizes> prizesLst = repository.findAll(filterFour, 
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(2);
    assertThat(prizesLst.get(1).getStartDate().toInstant()).isEqualTo(dateOneIni.toInstant());
    assertThat(prizesLst.get(1).getPrice()).isEqualTo(35.50f);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateThreeIni.toInstant());
    assertThat(prizesLst.get(0).getPrice()).isEqualTo(30.50f);
  }
 
  /** list of prizes filtered three. */
  @DisplayName("Obtain prizes filtered 2020-06-14 21:00:00")
  @Test
  void processFilterThree() {
    List<Prizes> prizesLst = repository.findAll(filterThree, 
                                            Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(1);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateOneIni.toInstant());
    assertThat(prizesLst.get(0).getPrice()).isEqualTo(35.50f);
  }
  
  /** list of prizes filtered two. */
  @DisplayName("Obtain prizes filtered 2020-06-14 16:00:00")
  @Test
  void processFilterTwo() {
    List<Prizes> prizesLst = repository.findAll(filterTwo, 
                                            Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(2);
    assertThat(prizesLst.get(1).getStartDate().toInstant()).isEqualTo(dateOneIni.toInstant());
    assertThat(prizesLst.get(1).getPrice()).isEqualTo(35.50f);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateTwoIni.toInstant());
    assertThat(prizesLst.get(0).getPrice()).isEqualTo(25.45f);
    
  }
  
  /** list of prizes filtered one. */
  @DisplayName("Obtain prizes filtered 2020-06-14 10:00:00")
  @Test
  void processFilterOne() {
    List<Prizes> prizesLst = repository.findAll(filterOne, 
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(1);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateOneIni.toInstant());
    assertThat(prizesLst.get(0).getPrice()).isEqualTo(35.50f);
    
  }
  
  /** list of prizes like ZAR. */
  @DisplayName("Obtain prizes filter by brand like ZAR ")
  @Test
  void processFilterLike() {
    List<Prizes> prizesLst = repository.findAll(filterLike,
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(4);
    
  }
  
  /** list of prizes like SPRING. */
  @DisplayName("Obtain prizes filter by brand like SPRING ")
  @Test
  void processFilterLikeNotMatch() {
    List<Prizes> prizesLst = repository.findAll(filterLikeNotMatch,
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isEmpty();
    
  }
  
  /** list of prizes like SPRING. */
  @DisplayName("Obtain prizes filter not found ")
  @Test
  void processFilterLikeNotFound() {
    List<Prizes> prizesLst = repository.findAll(filterNotFound, 
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isEmpty();
    
  }
  
  /** list of prizes. */
  @DisplayName("Obtain all prizes")
  @Test
  void processListOfPrizes() {
    List<Prizes> prizesLst = repository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isNotEmpty();
    assertThat(prizesLst.size()).isEqualTo(4);
    assertThat(prizesLst.get(0).getStartDate().toInstant()).isEqualTo(dateTwoIni.toInstant());
    assertThat(prizesLst.get(0).getEndDate().toInstant()).isEqualTo(dateTwoEnd.toInstant());
  }

}
