package objectville.map;

// Base exception type for map-related errors in Objectville.
public class ObjectvilleException extends RuntimeException {

    // Creates an exception with the given error message.
    public ObjectvilleException(String message) {
        super(message);
    }
}
