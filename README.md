# Blackjack

This project is a culmination of my Grade 11 Computer Science course, presenting the classic game of blackjack playable within your terminal.

## Features & Rules

### Objective
The primary aim of the game is to attain a hand value of 21 or come as close to it as possible without exceeding it.

### Game Rules
- Each player receives two cards, both face up, while the dealer is also dealt two cards with one face down (known as the 'hole' card).
- Card values are as follows: 
  - Numeric cards (2-10) are worth their face value.
  - Face cards (jack, queen, king) are worth 10.
  - An ace can be worth 1 or 11, depending on which value would be more beneficial for the player's hand.
- Players can choose to 'hit' and take additional cards, one at a time, in order to improve their hand value.
- If a player's hand value exceeds 21, they lose (this is called 'busting').
- Once all players have finished taking hits, the dealer reveals their hole card. The dealer must hit if their hand value is less than 17, and must stand (stop taking cards) if their hand value is 17 or higher.
- If the dealer busts, all remaining players win.
- If the dealer does not bust, then the player's hands are compared to the dealer's hand, and whoever has the hand value closest to 21 without going over wins.
- If a player and the dealer have the same hand value, it is a tie (this is called a 'push').
- Players can also choose to 'surrender' and give up half their bet before the dealer checks for a blackjack.
- Players can also 'split' their hand if they are dealt two cards of the same value.
- Players can also 'double down' by doubling their bet and receive one more card.

## Menu Navigation

- `play` - Enters the game
- `rules` - Opens the game rules
- `credits` - Displays the credits
- `quit` - Quits the application

Just type the following into your terminal.

## Getting Started

Run `javac main.java` to compile the code.

To run the program, execute `java main.java`.

## Contributing<a name="contributing"></a>

I welcome contributions and suggestions from the community. If you have ideas for improvements, new features, or bug fixes, please feel free to open an issue or submit a pull request.

## License<a name="license"></a>

This project is licensed under the [MIT License](LICENSE.md). You are free to use, modify, and distribute this software.