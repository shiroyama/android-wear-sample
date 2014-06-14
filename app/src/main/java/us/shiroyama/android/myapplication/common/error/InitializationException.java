package us.shiroyama.android.myapplication.common.error;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class InitializationException extends RuntimeException {
    public InitializationException() {
        super();
    }

    public InitializationException(String detailMessage) {
        super(detailMessage);
    }

    public InitializationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InitializationException(Throwable throwable) {
        super(throwable);
    }
}
