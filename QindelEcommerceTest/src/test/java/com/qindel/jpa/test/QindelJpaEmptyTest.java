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
@ActiveProfiles("testempty")
@ExtendWith(MockitoExtension.class)
public class QindelJpaEmptyTest {

  /** repository. */
  @Autowired
  PrizesRepository repository;
 
  /** filterOne. */
  private Specification<Prizes> filter;
 
  
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SimpleDateFormat tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    final PrizesSpecification filterSpec = new PrizesSpecification();
    final PrizesPredicates predicates = new PrizesPredicates();
    
    PrizesFilterData filtOne = new PrizesFilterData();
    filtOne.setProductId(35455);
    filtOne.setAppDate(tmpDate.parse("2020-06-14 10:00:00"));
    filtOne.setBrand("ZARA");
    List<Predicate> predOne = predicates.processPredicates(filtOne);
    filter = filterSpec.buildFilter(predOne);
  }
  
  /** list of prizes filtered five. */
  @DisplayName("Obtain prizes filtered in empty database")
  @Test
  void processFilterFive() {
    List<Prizes> prizesLst = repository.findAll(filter, 
                                           Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isEmpty();
  }
 
  
  /** list of prizes. */
  @DisplayName("Obtain all prizes in empty database")
  @Test
  void processListOfPrizes() {
    List<Prizes> prizesLst = repository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
    assertThat(prizesLst).isNotNull();
    assertThat(prizesLst).isEmpty();
  }

}
