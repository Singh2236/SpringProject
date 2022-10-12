package com.navi.modelSchool.repository;

import com.navi.modelSchool.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//CrudRepo() Method Params: Holidays -> POJO Class String -> Data type of the primary key

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {


}
