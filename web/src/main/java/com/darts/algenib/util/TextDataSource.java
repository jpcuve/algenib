package com.darts.algenib.util;

import javax.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by jpc on 8/10/15.
 */
public class TextDataSource implements DataSource {
    private final String text;

    public TextDataSource(String text) {
        this.text = text;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(text.getBytes(Charset.defaultCharset()));
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public String getContentType() {
        return "text/plain; charset=" + Charset.defaultCharset().name();
    }

    @Override
    public String getName() {
        return null;
    }
}
