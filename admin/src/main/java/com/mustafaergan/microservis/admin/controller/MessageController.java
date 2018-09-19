package com.mustafaergan.microservis.admin.controller;

import com.mustafaergan.microservis.admin.entity.Log;
import com.mustafaergan.microservis.admin.entity.MessageResource;
import com.mustafaergan.microservis.admin.service.DatabaseMessageSourceService;
import com.mustafaergan.microservis.admin.repository.MessageResourceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path ="/message")
@Api("Message Ile Ilgili Verileri Saglar")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DatabaseMessageSourceService databaseMessageSourceService;

    @Autowired
    private MessageResourceRepository messageResourceRepository;


    @ApiOperation(value = "tüm dilleri doner")
    @GetMapping(value ="/")
    public Map<String, Map<Locale, String>>  getMessage() {
        return databaseMessageSourceService.getMessages();
    }

    @ApiOperation(value = "Butun MessageResource Listeler")
    @GetMapping(value ="/db/")
    public Iterable<MessageResource> listLog() {
        return messageResourceRepository.findAll();
    }


    @ApiOperation(value = "Log Id ile arama yapar",response = Log.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResource getLog(@PathVariable("id") String id) throws Exception {
        Optional<MessageResource> log = messageResourceRepository.findById(id);
        if (!log.isPresent())
            throw new Exception("id-" + id);
        return log.get();
    }

    @ApiOperation(value = "code ve lang gore dil gonderir")
    @GetMapping(value ="/messagekey/{code}/{lang}")
    public String getMessage(@PathVariable("code") String code, @PathVariable("lang") String lang) {
        return messageSource.getMessage(code,null,LocaleUtils.toLocale(lang));
    }

    @ApiOperation(value = "Log Id ile arama yapar",response = Log.class)
    @GetMapping(value = "/messagekey/{messageKey}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResource getMessageKey(@PathVariable("messageKey") String messageKey) throws Exception {
        Optional<MessageResource> message = messageResourceRepository.findByMessageKey(messageKey);
        if (!message.isPresent())
            throw new Exception("messageKey-" + messageKey);
        return message.get();
    }

    @ApiOperation(value = "MessageResource kayıt eder")
    @PostMapping
    public ResponseEntity<MessageResource> save(@RequestBody MessageResource messageResource) {
        MessageResource savedMessageResource = messageResourceRepository.save(messageResource);
        databaseMessageSourceService.initialize();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMessageResource.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
