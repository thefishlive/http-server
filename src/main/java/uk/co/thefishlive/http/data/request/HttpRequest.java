package uk.co.thefishlive.http.data.request;

import uk.co.thefishlive.http.data.headers.HttpHeaders;
import uk.co.thefishlive.http.data.HttpMessage;
import uk.co.thefishlive.http.data.payload.HttpPayload;

public class HttpRequest extends HttpMessage<HttpRequestLine> {
    public HttpRequest(HttpRequestLine startLine, HttpHeaders headers, HttpPayload payload) {
        super(startLine, headers, payload);
    }
}
