package za.ac.nwu.domain.service;

import java.io.Serializable;
import java.util.Objects;

public class GeneralResponse/*<T>(boolean successful, T payload) implements Serializable*/ {
    /*private static final long serialVersionUID = 7734089948131092480L;

    public boolean isSuccessful() {
        return successful;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResponse<?> that = (GeneralResponse<?>) o;
        return successful == that.successful && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successful, payload);
    }

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "successful=" + successful +
                ", payload=" + payload +
                '}';
    }*/
}
