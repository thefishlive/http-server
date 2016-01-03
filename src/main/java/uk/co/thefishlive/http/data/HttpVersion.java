package uk.co.thefishlive.http.data;

import java.util.HashMap;
import java.util.Map;

public enum HttpVersion {

    HTTP_1_0(1, 0),
    HTTP_1_1(1, 1),
    HTTP_2_0(2, 0);

    private static final Map<String, HttpVersion> VERSIONS = new HashMap<>();

    static {
        for (HttpVersion version : values()) {
            VERSIONS.put(version.toString(), version);
        }
    }

    private final int major;
    private final int minor;

    HttpVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    @Override
    public String toString() {
        return String.format("HTTP/%d.%d", this.major, this.minor);
    }

    public static HttpVersion getVersion(String version) {
        return VERSIONS.get(version);
    }
}
