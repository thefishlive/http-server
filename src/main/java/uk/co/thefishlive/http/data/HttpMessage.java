package uk.co.thefishlive.http.data;

import java.util.List;

public class HttpMessage<T extends HttpStartLine> {

    private T startLine;
    private List<HttpHeader> headers;

    public HttpMessage(T startLine, List<HttpHeader> headers) {
        this.startLine = startLine;
        this.headers = headers;
    }

    public T getStartLine() {
        return startLine;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }
}
