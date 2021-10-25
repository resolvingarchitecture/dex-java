# DEX Java

## Requirements
* Requires RA Bitcoin Service with Bitcoin (BTC) Full Node as escrow and fees use BTC minimally or also a Good when used in a BTC-Fiat exchange.

## Features

### Processes
* M = Manual with UI
* S = System
* N = Notification in UI
* O = Outside System

#### Request Good A for Good B
1. (M) Alice: Select Good desired (Good B) and Good offered (Good A)
2. (M) Alice->Peers: Request Good A for Good B
3. (M) If Goods A or B not a currency, Community approval required (only goods visible not identity) -> Community Approval Process
4. (M) Bob: Select Good desired (Good A) and Good offered (Good B)
5. (M) If Goods B or A not a currency, Community approval required (only goods visible not identity) -> Community Approval Process
6. (M) Bob->Peers: Request Good B for Good A
7. (S) Bob: Match Alice's Request to Bob's Request
8. (S) Bob: Lock Bob's Request
9. (S) Bob->Alice: Accept Alice's Request
    1. (S) Alice->Bob: Alice's Request already locked by another request (alternative)
    2. (S) Bob: Unlock Bob's Request
    3. (S) Bob: Return to 7 until another match discovered
10. (S) Alice: Lock Alice's Request
11. (S) Alice/Bob->BTC: Establish Escrow
12. (N) Alice/Bob: Request Accepted (Escrow Established Notification with Terms)
13. In Parallel
    1. If Bob part of terms
       1. (O) Bob satisfies Terms
       2. (M) Bob->Alice: Terms met
       3. (O) Alice verifies terms met by Bob
       4. (M) Alice->Bob: Terms met acknowledged
    2. If Alice part of terms
       1. (O) Alice satisfies terms
       2. (M) Alice->Bob: Terms met
       3. (O) Bob verifies terms met by Alice
       4. (M) Bob->Alice: Terms met acknowledged
14. (S) Alice/Bob->BTC: Close Escrow
15. (S) Bob->Peers: Bob's Request Closed
16. (S) Alice->Peers: Alice's Request Closed

#### Community Approval Process
1. Good appears in list of goods requiring Community approval
2.

#### Community Rejection Process


#### Community Approver Selection Process


#### Judge Selection Process


#### Supreme Judge Selection Process


### Global Commands
* Cancel Request - A request can be canceled any time prior to

## API

* Request DEX Peers List
* Response DEX Peers List
* Make Offer
* Offer Made
* Offer Accepted - Locks Offer
* Offer Locked
* Maker Escrowed
* Maker Terms Met
* Accept Offer
* Acceptance Acknowledged
* Taker Escrowed
* Taker Terms Met
* Escrow Closed

## Attack Mitigations

### DDOS

#### Make Offer
Making an offer could be called repetitively by a client overwhelming the network.
Peers only accept Offer Made notifications of more than 1 in an hour from peers
that have completed 10 successful exchanges.

