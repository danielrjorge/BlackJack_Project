README

Project blACk_jACk

This project intends to simulate a blackjack game with multiplayer capabilities that runs on the terminal only.

How to play?
Run the blACk_jACk_Game.jar (on terminal go to directory and run "java -jar blACk_jACk_Game.jar")
Connect to server with Net Cat (nc <IP> <port>) -> port 42069;

RULES OF THE GAME:

Aim

The aim of blackjack is to finish the game with a higher total than that of the dealer, without exceeding 21. Going over 21 is commonly known as ‘busting’ and means an automatic loss.

The Play - Basic Rules

All number cards (2-10) score the value indicated on them. The face cards (Jack, Queen, King) score 10 points and Ace can either be treated as 1 or 11.

At the beginning of each round, all players place their bets in their betting positions - also known as ‘boxes’. After the bets have been placed, all players are dealt two cards face-up in front of their respective betting positions. The dealer receives two cards, one face-up and another face-down.

Each player is given a chance to draw more cards. The players can either ‘hit’ or ‘stand’. If the player calls out ‘HIT’, they are given an extra card. They can then either call out ‘HIT’ again, or ‘STAND’ if they do not wish to draw any more cards. The player can ‘HIT’ as many times as they wish, but have to aim not to ‘bust’ (exceed a total of 21). They can also Double - After receiving the first two cards, players can double their bet while hitting. When doubling down, player receives one extra card only and cannot hit again.

If the player busts, they immediately lose their bet.

After each player has played and either stood or busted, the dealer takes their turn. They can, again, either ‘HIT’ or ‘STAND’. If the dealer’s hand exceeds 21, all players who didn't bust win immediately - their bet is returned along with a matching amount from the dealer's bank.

If the dealer reaches a valid hand, the cards are totalled and each player’s hand is compared to the dealer’s. If the player scored higher than the dealer, they win. If the player ties with the dealer, the original bet is returned to the player. Otherwise, the player loses their bet.

A perfect hand combines an ace with a 10, Jack, Queen or King and is known as a ‘Blackjack’.

Contributors:
Daniel Jorge - @danielrjorge
João Pacheco - @pachecojoao
Diogo Matos -  @Diogom

