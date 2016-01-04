package uk.co.thefishlive.http.data.headers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HttpHeaders {
    private static final Pattern HEADER_PATTERN = Pattern.compile("([^:]*):(.*)");

    private Map<String, String> headers = new HashMap<>();

    public void addHeader(String header) {
        Matcher matcher = HEADER_PATTERN.matcher(header);

        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("String (%s) is not a valid header", header));
        }

        headers.put(matcher.group(1).trim(), matcher.group(2).trim());
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public boolean hasHeader(String key) {
        return headers.containsKey(key);
    }

    public String getHeaderValue(String key) {
        return headers.get(key);
    }

    public Set<HttpHeader> getHeaders() {
        return headers.entrySet()
                .stream()
                .map(entry -> new HttpHeader(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return headers.toString();
    }
}
