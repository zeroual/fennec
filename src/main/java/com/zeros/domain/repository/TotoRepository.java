package com.zeros.domain.repository;

import com.zeros.domain.entity.Toto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotoRepository extends CrudRepository<Toto,Long>{

}
