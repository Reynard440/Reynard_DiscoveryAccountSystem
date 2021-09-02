package za.ac.nwu.domain.service;

import java.io.Serializable;
import java.util.Objects;

public class DiscoveryAccountSystemResponse<T> implements Serializable {
    private static final long serialVersionUID = 7734089948131092480L;
    private final boolean confirmation;
    private final transient T cargo;

    public DiscoveryAccountSystemResponse(boolean confirmation, T cargo){
        this.confirmation = confirmation;
        this.cargo = cargo;
    }

    public boolean goodConfirmation() {
        return confirmation;
    }

    public T getCargo() {
        return cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscoveryAccountSystemResponse<?> that = (DiscoveryAccountSystemResponse<?>) o;
        return confirmation == that.confirmation && Objects.equals(cargo, that.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmation, cargo);
    }

    @Override
    public String toString() {
        return "DiscoveryAccountSystemResponse{" +
                "confirmation=" + confirmation +
                ", cargo=" + cargo +
                '}';
    }
}
