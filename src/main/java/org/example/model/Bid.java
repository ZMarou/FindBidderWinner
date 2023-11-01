package org.example.model;

import static java.util.Objects.isNull;

public record Bid(Integer value) implements Comparable<Bid> {

    public Bid {
        if (isNull(value) || value < 0) throw new IllegalArgumentException("Value is not supported");
    }

    @Override
    public int compareTo(Bid o) {
        return value.compareTo(o.value);
    }
}
