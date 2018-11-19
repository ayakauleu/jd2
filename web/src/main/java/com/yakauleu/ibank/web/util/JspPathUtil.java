package com.yakauleu.ibank.web.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class JspPathUtil {

    private static final String FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String get(String jspName) {
        return String.format(FORMAT, jspName);
    }
}
