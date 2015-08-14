package com.zeros.api;

public final class EndpointsPath {

    @Deprecated
    private EndpointsPath(){}

    private static final String URL_SEPARATOR = "/";
    private static final String VERSION = "v1";
    private static final String API_PREFIXE = "rest";
    private static final String URL_PREFIXE = URL_SEPARATOR + API_PREFIXE + URL_SEPARATOR + VERSION + URL_SEPARATOR;
    public static final String LOGIN = "/login";
    public static final String POST_PATH = URL_PREFIXE + "post";

}
