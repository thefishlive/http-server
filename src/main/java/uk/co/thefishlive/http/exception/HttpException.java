package uk.co.thefishlive.http.exception;

import uk.co.thefishlive.http.data.HttpStatusCode;

public class HttpException extends Exception {

    private final HttpStatusCode status;

    public HttpException(HttpStatusCode status) {
        this.status = status;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}
