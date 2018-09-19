package com.mustafaergan.microservis.admin.repository;

import com.mustafaergan.microservis.admin.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, String> {

}