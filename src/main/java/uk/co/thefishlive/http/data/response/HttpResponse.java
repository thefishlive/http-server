package uk.co.thefishlive.http.data.response;

import uk.co.thefishlive.http.data.HttpHeader;
import uk.co.thefishlive.http.data.HttpMessage;

import java.util.List;

public class HttpResponse extends HttpMessage<HttpStatusLine> {
    public HttpResponse(HttpStatusLine startLine, List<HttpHeader> headers) {
        super(startLine, headers);
    }
}
