package vn.hcmute.testAPI.exception;

public class StorageFileNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1L;
  public StorageFileNotFoundException(String message) {
        super(message);
    }
}
