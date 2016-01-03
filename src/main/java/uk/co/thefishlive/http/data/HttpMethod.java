package uk.co.thefishlive.http.data;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {

    /**
     * Transfer a current representation of the target resource.
     */
    GET,
    /**
     * Same as GET but only transfer the status line and header section.
     */
    HEAD,
    /**
     * Perform resource-specific processing on the request payload.
     */
    POST,
    /**
     * Replace all current representations of the target resource with the request payload.
     */
    PUT,
    /**
     * Remove all current representations of the target resource.
     */
    DELETE,
    /**
     * Establish a tunnel to the server identified by the target resource.
     */
    CONNECT,
    /**
     * Describe the communication options for the target resource.
     */
    OPTIONS,
    /**
     * Perform a message loop-back test along the path to the target resource.
     */
    TRACE;

    private static final Map<String, HttpMethod> METHODS = new HashMap<>();

    static {
        for (HttpMethod method : values()) {
            METHODS.put(method.name(), method);
        }
    }

    public static HttpMethod getMethod(String method) {
        return METHODS.get(method);
    }
}
