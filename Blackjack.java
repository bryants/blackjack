// This is the main program for the blackjack game.
public class Blackjack
{
	// This method should:
	//	- Ask the user how many people want to play (up to 3, not including the dealer).
	//	- Create an array of players.
	//	- Create a Blackjack window.
	// 	- Play rounds until the players want to quit the game.
	//	- Close the window.
	public static void main(String[] args)
	{
		int numPlayers = 0;
		boolean inputError = true;
		String[] playerNames;
		Player[] players;

		
		while (inputError)
		{
			numPlayers = GIO.readInt("How many people want to play? (Up to 3)") + 1;
			if (numPlayers < 0 || numPlayers > 4)
			{
				GIO.displayMessage("Error in number of players input");
			}
			else
			{
				inputError = false;
			}
		}
		
		playerNames = new String[numPlayers + 1];
		boolean[] isDealer = new boolean[numPlayers + 1];
		playerNames[0] = "Dealer";
		isDealer[0] = true;
		for (int i = 1; i < numPlayers; i++)
		{
			playerNames[i] = GIO.readString("What is this player's name?");
			isDealer[i] = false;
		}
		players = new Player[numPlayers + 1];
		for (int i = 0; i < numPlayers; i++)
		{
			players[i] = new Player(playerNames[i],isDealer[i]);
		}
		BlackjackWindow window = new BlackjackWindow(players);
		window.redraw();
		while (true)
		{
			playRound(players,window);
			boolean playagain = GIO.readBoolean("Do you want to play again?");
			if (!playagain)
			{
				window.close();
				return;
			}
		}
	}

	// This method executes an single round of play (for all players).  It should:
	//	- Create and shuffle a deck of cards.
	//	- Start the round (deal cards) for each player, then the dealer.
	//	- Allow each player to play, then the dealer.
	//	- Finish the round (announce results) for each player.
	public static void playRound(Player[] players, BlackjackWindow window)
	{
		Deck deck = new Deck();
		deck.shuffle();
		
		for (int i = 1; i < players.length; i++)
		{
			players[i].startRound(deck, window);
		}
		
		players[0].startRound(deck, window);
		
		for (int i = 1; i < players.length; i++)
		{
			players[i].playRound(deck, window);
		}
		
		players[0].playRound(deck, window);
		
		for (int i = 1; i < players.length; i++)
		{
			players[i].finishRound(players[0].getHand().getScore(), window);
		}
		
		players[0].finishRound(players[0].getHand().getScore(), window);

	}
}
