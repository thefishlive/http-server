package uk.co.thefishlive.http.data;

public enum HttpStatusCode {
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),

    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTHORITATIVE_INFORMATION(203, "non-authoritative information"),
    NO_CONTENT(204),
    RESET_CONTENT(205),

    MULTIPLE_CHOICES(300),
    MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    USE_PROXY(305),
    TEMPORARY_REDIRECT(307),

    BAD_REQUEST(400),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PAYLOAD_TOO_LARGE(413),
    URI_TOO_LONG(414),
    UNSUPPORTED_MEDIA_TYPE(415),
    EXPECTATION_FAILED(417),
    UPGRADE_REQUIRED(426),

    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
    HTTP_VERSION_NOT_SUPPORTED(505),
    ;

    private final int code;
    private final String reason;

    HttpStatusCode(int code) {
        this.code = code;
        this.reason = name().toLowerCase().replace('_', ' ');
    }


    HttpStatusCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.reason;
    }
}
