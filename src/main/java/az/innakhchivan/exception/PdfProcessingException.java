package az.innakhchivan.exception;

import java.io.IOException;

public class PdfProcessingException extends Throwable {
    public PdfProcessingException(String s, IOException e) {
        super(s, e);
    }
}
