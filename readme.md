# Board Games
Tic Tac Toe, Order and Chaos, Super Tic Tac Toe

## Files

Main: Project starts from here
ClientFactory: Build a Client for Gaming which contains Game and Player
GameFactory: Build a board game basics

GameType: Store all the game types, convenient for game selection
Game: An Interface for games
BoardGame: An abstract class which implements Game Interface, used for extension of the board games
TTT: Play Tic Tac Toe game here
STTT: Play Super Tic Tac Toe game here which extends TTT
OC: Play Order and Chaos game here

Client: Control and record the whole game
Player:  Holds properties of a player
Board: Board basic stuff which can be tiled
Tile:  A Tile being made means a Piece is created for it
Piece: A piece holds a mark which could belong to a player
SBoard: Super Board holds a 2d Board more than Board

Input: Utilities for input stuff
Position: Utilities for 1d-2d change on board

## How to compile and run
1. Navigate to the directory "BoardGames" after unzipping the files
2. Run the following instructions:
mkdir bin
javac src/Main.java src/*/*.java -d bin
java -cp bin Main
