Consider a scenario involving a sealed-bid second-price auction, with the following conditions:

    An item is up for auction, with a set reserve price.
    There are multiple potential bidders, each capable of placing one or more bids.
    The winning bidder is determined as the one whose bid, equal to or higher than the reserve price, is the highest.
    Only one bidder can win the auction.
    The winning price is the highest bid placed by any non-winning bidder that is above the reserve price. If there's no such bid, the reserve price itself is the winning price.

Instructions:

Your task is to create a program that models the scenario described above. Here are some guidelines to follow:

    Your program should accept two inputs and yield two outputs.
    Inputs: (1) Data representing the bidders and their bids (2) The reserve price.
    Outputs: (1) The winning bidder (2) The winning bid price.
    You are free to use any data structures that best represent the inputs and outputs.
    You can select any programming language for this task.

Example:

Let's consider 5 potential buyers (A, B, C, D, E) who compete to acquire an object with a reserve price set at 100 euros, bidding as follows:

    A: 2 bids of 110 and 130 euros
    B: 0 bid
    C: 1 bid of 125 euros
    D: 3 bids of 105, 115 and 90 euros
    E: 3 bids of 132, 135 and 140 euros

The buyer E wins the auction at the price of 130 euros.
