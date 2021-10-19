# DEX Java

## Features

### Crypto Supported
Only Bitcoin (BTC) is supported.

Increments available are based on daily (UT) volume:

* 30/day: 0.01
* 60/day: 0.001, 0.01
* 90/day: 0.001, 0.01, 0.1
* 120/day: 0.001, 0.01, 0.1, 1

### Fiat Supported
* USD

### Fees
* Bitcoin Miners - varies
* DEX - 0.0001 (10k Satoshis - 1%)

### Fiat Exchange Methods Supported
* Zelle
* Money Order
* US Postal Service
* FedEx
* UPS
* Face-to-Face

## Processes

### Request BTC for Fiat
1. Maker: Make Offer (to exchange BTC for Fiat)
2. Taker: Make Offer (to exchange Fiat for BTC)
3. Maker: Accept Offer
4.

### Request Fiat for BTC

## API

* Request DEX Peers List
* Response DEX Peers List
* Make Offer
* Offer Made
* Offer Accepted
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

