package com.mustafaergan.microservis.admin.controller;


import com.mustafaergan.microservis.admin.entity.Log;
import com.mustafaergan.microservis.admin.repository.LogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path ="/log")
@Api("Log Ile Ilgili Verileri Saglar")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogRepository logRepository;

    @ApiOperation(value = "Butun Log Listeler")
    @GetMapping(value ="/")
    public Iterable<Log> listLog() {
        return logRepository.findAll();
    }


    @ApiOperation(value = "Log Id ile arama yapar",response = Log.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Log getLog(@PathVariable("id") String id) throws Exception {
        Optional<Log> log = logRepository.findById(id);
        if (!log.isPresent())
            throw new Exception("id-" + id);
        return log.get();
    }

    @ApiOperation(value = "Log Kayıt Eder")
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> saveAgency(@RequestBody Log log) {
        Log savedAgency = logRepository.save(log);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAgency.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
