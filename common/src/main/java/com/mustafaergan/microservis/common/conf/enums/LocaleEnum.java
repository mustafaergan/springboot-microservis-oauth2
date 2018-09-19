package com.mustafaergan.microservis.common.conf.enums;

import org.apache.commons.lang.LocaleUtils;

import java.util.Locale;

public enum LocaleEnum {
    TR(LocaleUtils.toLocale("tr")),
    EN(LocaleUtils.toLocale("en")),
    FR(LocaleUtils.toLocale("fr")),
    DE(LocaleUtils.toLocale("de"));

    private final Locale locale;

    LocaleEnum(Locale locale){
        this.locale =locale;
    }

    public Locale getLocale() {
        return locale;
    }

}
