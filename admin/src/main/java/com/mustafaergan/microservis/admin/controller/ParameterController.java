package com.mustafaergan.microservis.admin.controller;

import com.mustafaergan.microservis.admin.entity.Parameter;
import com.mustafaergan.microservis.admin.repository.ParameterRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/parameter")
@Api("Parameter Rest Service")
public class ParameterController {
    private Logger logger = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    private ParameterRepository repository;

    @ApiOperation(value = "Tüm Parameteri listeler")
    @GetMapping
    public ResponseEntity<List<Parameter>> list(){
        return ResponseEntity.ok(repository.findAll());
    }

    @ApiOperation(value = "Parameter Id ile arama yapar",response = Parameter.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Parameter> findById(@PathVariable("id") String id) throws Exception {
        Optional<Parameter> parameter = repository.findById(id);
        if (!parameter.isPresent())
            throw new Exception("id-" + id);
        return ResponseEntity.ok(parameter.get());
    }

    @ApiOperation(value = "Parameter Param ile arama yapar",response = Parameter.class)
    @GetMapping(value = "/param/{param}")
    public Iterable<Parameter> findByParam(@PathVariable("param") String param) {
        return repository.findByParam(param);
    }

    @ApiOperation(value = "Parameter Kayıt Eder")
    @PostMapping
    public ResponseEntity<Parameter> save(@RequestBody Parameter parameter) {
        Parameter savedParameter = repository.save(parameter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedParameter.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Ilgili Id Parametreyi Gunceller")
    @PutMapping("/{id}")
    public ResponseEntity<Parameter>  updateParameter(@RequestBody Parameter parameter, @PathVariable String id) {
        Optional<Parameter> parameterOptional = repository.findById(id);
        if (!parameterOptional.isPresent())
            return ResponseEntity.notFound().build();
        parameter.setId(id);
        repository.save(parameter);
        return ResponseEntity.ok(parameter);
    }

    @ApiOperation(value = "Parametre Siler")
    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParameter(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
