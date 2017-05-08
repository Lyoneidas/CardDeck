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

## Well mixed set of cards

The out put sequence represents a 'well mixed set of cards', which should be necessary randomized. There are some articles about how to 
shuffle the cards to be effectively randomized, for example the magic number of seven [1]. However  I have done some research into the topic to 
find a reasonably acceptable support for my way of shuffling the cards.

Given a set of $S_{n}$


## REFERENCES
[1] Kolata, Gina. "In shuffling cards, seven is winning number." New York Times (1990).
