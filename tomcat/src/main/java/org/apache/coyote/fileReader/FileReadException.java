package org.apache.coyote.fileReader;

public class FileReadException extends RuntimeException {

    private static final String CANNOT_READ_FILE_MESSAGE = "파일을 읽을 수 없습니다.";

    public FileReadException() {
        super(CANNOT_READ_FILE_MESSAGE);
    }
}
