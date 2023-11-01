package org.example.model;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Auction {

    private final List<Bidder> bidderList;
    private final Bid reservePrice;

    public Auction(List<Bidder> bidderList, Bid reservePrice) {
        if (bidderList == null) throw new IllegalArgumentException("Value is not supported");
        this.bidderList = bidderList;
        this.reservePrice = reservePrice;
    }

    public Optional<Bidder> findWinningBidder() {
        return bidderList
                .stream()
                .filter(bidder -> bidder.getMaxBid() != null && bidder.getMaxBid().compareTo(reservePrice) >= 0)
                .max((o1, o2) -> o1.compareMaxBidTo(o2.getMaxBid()));
    }

    public Bid findWinningPrice(Bidder winningBidder) {
        if (winningBidder == null) return reservePrice;
        return bidderList
                .stream()
                .filter(bidder -> !bidder.equals(winningBidder) && bidder.getMaxBid() != null && bidder.getMaxBid().compareTo(reservePrice) > 0)
                .map(Bidder::getBidList)
                .flatMap(Collection::stream)
                .max(Bid::compareTo)
                .orElse(reservePrice);
    }

    public AuctionOutput showResult() {
        Bidder winningBidder = findWinningBidder().isPresent() ? findWinningBidder().get() : null;
        String winningBidderName = winningBidder != null ? winningBidder.getName() : null;
        String winningPrice = findWinningPrice(winningBidder).value().toString();
        return new AuctionOutput(winningBidderName, winningPrice);
    }
}
