package com.qindel.jpa;

import com.qindel.jpa.dto.Prizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/** Query Prizes data. */
public interface PrizesRepository extends JpaRepository<Prizes, Long>, 
    JpaSpecificationExecutor<Prizes>  {

}
