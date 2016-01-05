package uk.co.thefishlive.http.data.payload;

import uk.co.thefishlive.http.data.HttpStatusCode;

import java.io.File;
import java.io.IOException;

public class ErrorPayload extends FilePayload {
    public ErrorPayload(HttpStatusCode status) throws IOException {
        this(new File("error", String.format("%d.html", status.getCode())));
    }

    public ErrorPayload(File file) throws IOException {
        super(file.exists() ? file : new File("error", String.format("%s.html", "missing")));
    }
}
