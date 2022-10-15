package com.navi.modelSchool.repository;

import com.navi.modelSchool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepo() Method Params: Holidays -> POJO Class String -> Data type of the primary key

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {


}
