package com.mustafaergan.microservis.admin.service;

import com.mustafaergan.microservis.admin.entity.MessageResource;
import com.mustafaergan.microservis.admin.repository.MessageResourceRepository;
import com.mustafaergan.microservis.common.conf.enums.LocaleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@ApplicationScope
public class DatabaseMessageSourceService extends AbstractMessageSource {

    @Autowired
    private MessageResourceRepository messageResourceRepository;

    private boolean initialized;

    private Map<String, Map<Locale, String>> messages = new HashMap();

    @PostConstruct
    public void initialize() {
        if (!initialized) {
            initialized = true;
            List<MessageResource> messageResourceList = messageResourceRepository.findAll();
            for (MessageResource messages : messageResourceList) {
                addMessage(messages.getMessageKey(), LocaleEnum.TR.getLocale(), messages.getTr());
                addMessage(messages.getMessageKey(), LocaleEnum.EN.getLocale(), messages.getEn());
                addMessage(messages.getMessageKey(), LocaleEnum.FR.getLocale(), messages.getFr());
                addMessage(messages.getMessageKey(), LocaleEnum.DE.getLocale(), messages.getDe());
            }
            initialized = false;
        }
    }

    public void addMessage(String code, Locale locale, String text) {
        Map<Locale, String> messagesByCode = messages.get(code);
        if (messagesByCode == null) {
            messagesByCode = new HashMap();
            messages.put(code, messagesByCode);
        }
        messagesByCode.put(locale, text);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        initialize();
        MessageFormat result = null;
        Map<Locale, String> localeToTextMap = messages.get(code);
        if (localeToTextMap != null) {
            String text = localeToTextMap.get(locale);
            result = createMessageFormat(text, locale);
        }
        return result;
    }

    public Map<String, Map<Locale, String>> getMessages() {
        return messages;
    }
}
