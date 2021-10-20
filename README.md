# DEX Java

## Features

### Crypto Supported
Only Bitcoin (BTC) is supported.

Increments available are based on daily (UT) volume:

* 0/day: 0.01
* 30/day: 0.001, 0.01
* 60/day: 0.001, 0.01, 0.1
* 90/day: 0.001, 0.01, 0.1, 1

### Fiat Supported
* AFN - Afghan Afghani
* CNY - Chinese Yuan
* CRC - Costa Rican Colon
* EGP - Egyptian Pound
* EUR - European
* GBP - British Pound
* IDR - Indonesian Rupiah
* INR - Indian Rupee
* IQD - Iraqi Dinar
* IRR - Iranian Rial
* ISK - Icelandic Krona
* JPY - Japanese Yen
* KPW - North Korean Won
* KRW - South Korean Won
* LBP - Lebanese Pound
* MXN - Mexican Peso
* NGN - Nigerian Naira
* RUB - Russian Ruble
* SAR - Saudi Riyal
* USD - US Dollar

### Fiat Exchange Methods Supported

* Advanced Cash
* AliPay
* Amazon eGift Card
* Austrialian PayID
* Cash By Mail
* Face-to-Face (In-Person)
* US Postal Money Order
* Popmoney
* Revolut
* SEPA
* WeChat Pay
* Zelle (ClearXchange)

### Fees

* Bitcoin Miners - varies
* DEX - 0.5% (0.001 Free)

## Processes

### Request BTC for Fiat
* M = Manual with UI
* S = System
* N = Notification in UI
* O = Outside System

1. (M) Alice->Peers: Request Exchange (to exchange BTC for Fiat)
2. (M) Bob->Peers: Request Exchange (to exchange Fiat for BTC)
3. (S) Alice: Match Bob's Request to Alice's Request
4. (S) Alice: Lock Alice's Request
5. (S) Alice->Bob: Accept Bob's Request
   1. (S) Bob->Alice: Bob's Request already locked (alternative)
6. (S) Bob: Lock Bob's Request
7. (S) Bob->BTC: Establish Escrow
8. (N) Bob->Alice: Request Accepted (Escrow Established Notification with Terms)
9. (O) Bob satisfies terms (sends fiat)
10. (M) Bob->Alice: Terms Met
11. (O) Alice verifies Terms
12. (M) Alice->Bob: Terms Met Acknowledged
13. (S) Bob->BTC: Close Escrow
14. (S) Bob->Peers: Bob's Request Closed
15. (S) Bob->Alice: Escrow Closed
16. (S) Alice->Peers: Alice's Request Closed

### Request Fiat for BTC

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

