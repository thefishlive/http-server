package uk.co.thefishlive.http.data.response;

import uk.co.thefishlive.http.data.HttpStartLine;
import uk.co.thefishlive.http.data.HttpStatusCode;
import uk.co.thefishlive.http.data.HttpVersion;

public class HttpStatusLine extends HttpStartLine {

    private HttpVersion version;
    private HttpStatusCode statusCode;

    public HttpStatusLine(HttpVersion version, HttpStatusCode statusCode) {
        this.version = version;
        this.statusCode = statusCode;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpStatusLine that = (HttpStatusLine) o;

        if (version != that.version) return false;
        return statusCode == that.statusCode;

    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", version.toString(), statusCode.getCode(), statusCode.toString());
    }
}
