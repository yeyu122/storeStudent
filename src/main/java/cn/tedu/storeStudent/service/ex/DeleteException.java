package cn.tedu.storeStudent.service.ex;

public class DeleteException extends SecurityException{
    public DeleteException() {
    }

    public DeleteException(String s) {
        super(s);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }
}
