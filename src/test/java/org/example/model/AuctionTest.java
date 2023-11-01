package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuctionTest {

    @Test
    public void should_return_winning_bidder_when_there_is_many_bidders_and_bids_above_reserve_price() {
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), new Bid(130)));
        Bidder bidder2 = new Bidder("B", List.of());
        Bidder bidder3 = new Bidder("C", List.of(new Bid(125)));
        Bidder bidder4 = new Bidder("D", List.of(new Bid(105), new Bid(115), new Bid(90)));
        Bidder bidder5 = new Bidder("E", List.of(new Bid(132), new Bid(135), new Bid(140)));

        List<Bidder> bidderList = List.of(bidder1, bidder2, bidder3, bidder4, bidder5);
        Auction auction = new Auction(bidderList, new Bid(100));
        Optional<Bidder> winner = auction.findWinningBidder();

        assertEquals(bidder5, winner.get());
    }

    @Test
    public void should_return_no_winning_bidder_when_there_is_many_bidders_and_bids_under_reserve_price() {
        Bidder bidder1 = new Bidder("A", List.of(new Bid(90), new Bid(75)));
        Bidder bidder2 = new Bidder("B", List.of());

        List<Bidder> bidderList = List.of(bidder1, bidder2);
        Auction auction = new Auction(bidderList, new Bid(100));
        Optional<Bidder> winner = auction.findWinningBidder();

        assertTrue(winner.isEmpty());
    }

    @Test
    public void should_return_no_winning_bidder_when_there_is_no_bidder() {
        List<Bidder> bidderList = List.of();
        Auction auction = new Auction(bidderList, new Bid(100));
        Optional<Bidder> winner = auction.findWinningBidder();

        assertTrue(winner.isEmpty());
    }

    @Test
    public void should_return_illegal_argument_exception_when_bidder_List_null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Auction(null, new Bid(100)));
        Assertions.assertEquals("Value is not supported", exception.getMessage());
    }

    @Test
    public void should_return_winning_price_when_there_is_many_bidders_and_bids_above_reserve_price() {
        Bid bid = new Bid(130);
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), bid));
        Bidder bidder2 = new Bidder("B", List.of());
        Bidder bidder3 = new Bidder("C", List.of(new Bid(125)));
        Bidder bidder4 = new Bidder("D", List.of(new Bid(105), new Bid(115), new Bid(90)));
        Bidder bidder5 = new Bidder("E", List.of(new Bid(132), new Bid(135), new Bid(140)));

        List<Bidder> bidderList = List.of(bidder1, bidder2, bidder3, bidder4, bidder5);
        Auction auction = new Auction(bidderList, new Bid(100));
        Optional<Bidder> winningBidder = auction.findWinningBidder();
        Bid winningPrice = auction.findWinningPrice(winningBidder.get());

        assertEquals(bid, winningPrice);
    }

    @Test
    public void should_return_winning_price_when_there_no_winning_bidder() {
        Bid bid = new Bid(150);
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), bid));
        Bidder bidder2 = new Bidder("B", List.of());

        List<Bidder> bidderList = List.of(bidder1, bidder2);
        Auction auction = new Auction(bidderList, bid);
        Bid winningPrice = auction.findWinningPrice(null);

        assertEquals(bid, winningPrice);
    }

    @Test
    public void should_return_a_winning_bidder_name_and_a_winning_price_when_there_is_many_bidders_and_bids_above_reserve_price() {
        Bid bid = new Bid(130);
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), bid));
        Bidder bidder2 = new Bidder("B", List.of());
        Bidder bidder3 = new Bidder("C", List.of(new Bid(125)));
        Bidder bidder4 = new Bidder("D", List.of(new Bid(105), new Bid(115), new Bid(90)));
        Bidder bidder5 = new Bidder("E", List.of(new Bid(132), new Bid(135), new Bid(140)));

        List<Bidder> bidderList = List.of(bidder1, bidder2, bidder3, bidder4, bidder5);
        Auction auction = new Auction(bidderList, new Bid(100));
        AuctionOutput result = auction.showResult();
        AuctionOutput expectedResult = new AuctionOutput("E", "130");

        assertEquals(expectedResult, result);
    }

    @Test
    public void should_return_a_null_winning_bidder_name_and_reserved_price_when_there_is_no_winning_bidder() {
        Bid bid = new Bid(130);
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), bid));
        Bidder bidder2 = new Bidder("B", List.of());

        List<Bidder> bidderList = List.of(bidder1, bidder2);
        Auction auction = new Auction(bidderList, new Bid(150));
        AuctionOutput result = auction.showResult();
        AuctionOutput expectedResult = new AuctionOutput(null, "150");

        assertEquals(expectedResult, result);
    }


    @Test
    public void should_return_a_winning_bidder_name_and_reserved_price_when_there_a_winning_bidder_and_no_winning_price_above_reserved_price() {
        Bid bid = new Bid(130);
        Bidder bidder1 = new Bidder("A", List.of(new Bid(110), bid));
        Bidder bidder2 = new Bidder("B", List.of());

        List<Bidder> bidderList = List.of(bidder1, bidder2);
        Auction auction = new Auction(bidderList, new Bid(100));
        AuctionOutput result = auction.showResult();
        AuctionOutput expectedResult = new AuctionOutput("A", "100");

        assertEquals(expectedResult, result);
    }


}
