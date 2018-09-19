package com.mustafaergan.microservis.admin.repository;

import com.mustafaergan.microservis.admin.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {

    Iterable<Parameter> findByParam(String param);

}
