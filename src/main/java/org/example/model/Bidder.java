package org.example.model;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class Bidder {

    private String name;
    private List<Bid> bidList;
    private Bid maxBid;

    public Bidder(String name, List<Bid> bidList) {
        if (bidList == null) throw new IllegalArgumentException("Value is not supported");
        this.name = name;
        this.bidList = bidList;
        calculateMaxBid();
    }

    private void calculateMaxBid() {
        maxBid = bidList.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    public int compareMaxBidTo(Bid o) {
        return maxBid.compareTo(o);
    }
}
