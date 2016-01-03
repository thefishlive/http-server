package uk.co.thefishlive.http.data;

public enum HttpVersion {

    HTTP_1_0(1, 0),
    HTTP_1_1(1, 1),
    HTTP_2_0(2, 0);

    private final int major;
    private final int minor;

    HttpVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    @Override
    public String toString() {
        return String.format("HTTP/%d.%d", this.major, this.minor);
    }
}
