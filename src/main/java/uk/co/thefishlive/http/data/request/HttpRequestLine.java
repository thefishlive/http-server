package uk.co.thefishlive.http.data.request;

import uk.co.thefishlive.http.data.HttpMethod;
import uk.co.thefishlive.http.data.HttpStartLine;
import uk.co.thefishlive.http.data.HttpVersion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestLine extends HttpStartLine {

    public static final Pattern REQUEST_LINE_PATTERN = Pattern.compile("([\\S]*) ([\\S]*) ([\\S]*)");

    private HttpMethod method;
    private String target;
    private HttpVersion version;

    public HttpRequestLine(String requestline) {
        Matcher matcher = REQUEST_LINE_PATTERN.matcher(requestline);

        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("String (%s) is not a valid header", requestline));
        }

        this.method = HttpMethod.getMethod(matcher.group(1).trim());
        this.target = matcher.group(2).trim();
        this.version = HttpVersion.getVersion(matcher.group(3).trim());
    }

    public HttpRequestLine(HttpMethod method, String target, HttpVersion version) {
        this.method = method;
        this.target = target;
        this.version = version;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getTarget() {
        return target;
    }

    public HttpVersion getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpRequestLine that = (HttpRequestLine) o;

        if (method != that.method) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        return version == that.version;

    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", method.toString(), target, version.toString());
    }
}
