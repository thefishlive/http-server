package uk.co.thefishlive.http.data.request;

import uk.co.thefishlive.http.data.HttpHeader;
import uk.co.thefishlive.http.data.HttpMessage;

import java.util.List;

public class HttpRequest extends HttpMessage<HttpRequestLine> {
    public HttpRequest(HttpRequestLine startLine, List<HttpHeader> headers) {
        super(startLine, headers);
    }
}
