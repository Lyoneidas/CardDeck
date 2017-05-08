# CardDeck
This is the repository for my card deck project

Project requirements:

Design a deck of playing cards, include the basic feature 'shuffle'.

## Introduction

This project is to create a java program which have these features:

Taking a JSON string indicating the original set of cards, and shuffling it. 
The output sequence should be a JSON string indicating a well mixed sequence of cards.

I simplify the quiz by setting the following boundaries:
1. The card we are using is one set of standard poker, which is consisted of 52 different cards without the jokers.
2. We use 1-52 to indicate these cards.

## Design

Input: a comma seperated sequence with numbers 1-52. The size of the sequence should be between 0 and 52.
Output:
1. There are more than 52 numbers:
'The input exceeds one deck'

2. There are duplicated numbers:
'There are duplicated cards in your deck'

3. The size of the sequence is zero:
'There is no card in your input'

4. The input includes symbols other than numbers and comma:
'Invalid input'

5. Other conditions:
A string representing a well shuffled set of cards.

## A shuffle is not effectively a permutation

A shuffle is different from permutation, and I understand it by thinking about the real world: you cannot have a set of cards(>3) which is consisted of a rising sequence and a decending sequence by pure riffle shuffle and two-packet cut. You have to invert one of your packets to achieve such sequence. Therefore it is possible to determine if a given set of cards are purely cut and shuffled, or deducing the card numbers if I am informed about how the set is shuffled. This is used by many magicians [3].

## Well mixed set of cards

The out put sequence represents a 'well mixed set of cards', which should be necessary randomized. I take variation distance as the metrics to measure the randomness. For the set of card shuffling results, the smaller the variation distance between the probability density of each results are, the better randomness of the shuffling strategy can achieve.

There are some articles about how to shuffle the cards to be effectively randomized, for example the magic number of seven [1]. However  I have done some research into the topic to find a reasonably acceptable support for my way of shuffling the cards.

Some researchers have concluded that the seven magic number is wrong [2] after introducing some mathematical models of card shuffle(Riffle shuffle, A-shuffle, Inverse shuffle). The suitable choice of shuffle strategy can be either riffle shuffle(which actually is 2-shuffle) or a more general a-shuffle, and on average, 11 to 12 times of shuffles can effectively create a randomized sequence.


## REFERENCES
[1] Kolata, Gina. "In shuffling cards, seven is winning number." New York Times (1990).

[2] Mann, Brad. "How many times should you shuffle a deck of cards." Topics in Contemporary Probability and Its Applications 15 (1995): 1-33.

[3] Bayer, Dave, and Persi Diaconis. "Trailing the dovetail shuffle to its lair." The Annals of Applied Probability (1992): 294-313.
