package uk.co.thefishlive.http.data;

import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.payload.HttpPayload;

import java.util.regex.Pattern;

public class HttpMessage<T extends HttpStartLine> {

    private static final Pattern HEADER_PATTERN = Pattern.compile("([^:]*):(.*)");

    protected T startLine;
    protected HttpHeaders headers;
    protected HttpPayload payload;

    public HttpMessage(T startLine, HttpHeaders headers, HttpPayload payload) {
        this.startLine = startLine;
        this.headers = headers;
        this.payload = payload;
    }

    public T getStartLine() {
        return startLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void addHeader(String header) {
        headers.addHeader(header);
    }

    public void addHeader(String key, String value) {
        headers.addHeader(key, value);
    }

    public boolean hasHeader(String key) {
        return headers.hasHeader(key);
    }

    public String getHeaderValue(String key) {
        return headers.getHeaderValue(key);
    }

    public HttpPayload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "HttpMessage{" +
                "startLine=" + startLine +
                ", headers=" + headers +
                ", payload=" + payload +
                '}';
    }
}
