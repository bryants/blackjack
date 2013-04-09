// This class represents one blackjack player (or the dealer)
public class Player
{
	private String name;
	private Hand hand;
	private Deck deck1;
	private boolean dealer;

	// This constructor creates a player.
	// If isDealer is true, this Player object represents the dealer.
	public Player(String playerName, boolean isDealer)
	{
		name = playerName;
		dealer = isDealer;
		hand = new Hand();
	}

	// This method retrieves the player's name.
	public String getName()
	{
		return name;
	}

	// This method retrieves the player's hand of cards.
	public Hand getHand()
	{
		return hand;
	}
	
	// This method deals two cards to the player (one face down if this is the dealer).
	// The window input should be used to redraw the window whenever a card is dealt.
	public void startRound(Deck deck, BlackjackWindow window)
	{
		if (!dealer){
			Card c = deck.drawCard();
			c.turnFaceUp();
			hand.addCard(c);
			window.redraw();
			
			Card d = deck.drawCard();
			d.turnFaceUp();
			hand.addCard(d);
			window.redraw();
		}
		
		else //(dealer)
		{
			Card c = deck.drawCard();
			c.turnFaceDown();
			hand.addCard(c);
			window.redraw();
			
			Card d = deck.drawCard();
			d.turnFaceUp();
			hand.addCard(d);
			window.redraw();
		}
	}

	// This method executes gameplay for one player.
	// If this player is the dealer:
	//	- hits until score is at least 17
	// If this is an ordinary player:
	//	- repeatedly asks the user if they want to hit (draw another card)
	//	  until either the player wants to stand (not take any more cards) or
	//	  his/her score exceeds 21 (busts).
	// The window input should be used to redraw the window whenever a card is dealt or turned over.
	public void playRound(Deck deck, BlackjackWindow window)
	{
		if (dealer){
			hand.flipCardFaceUp(0);
			window.redraw();
			while (hand.getScore() <= 17){
				Card c = deck.drawCard();
				c.turnFaceUp();
				hand.addCard(c);
				window.redraw();
			}
		}
		
		else
		{
			while (hand.getScore() <= 21)
			{
				boolean hit = GIO.readBoolean(name + ", Hit(yes) or Stay(no)?");
				if (hit)
				{
					Card c = deck.drawCard();
					c.turnFaceUp();
					hand.addCard(c);
					window.redraw();
				}
				else
					break;
			}
		}
	}

	// This method informs the player about whether they won, lost, or pushed.
	// It also discards the player's cards to prepare for the next round.
	// The window input should be used to redraw the window after cards are discarded.
	public void finishRound(int dealerScore, BlackjackWindow window)
	{
		if (!dealer)
		{
			if (hand.getScore() > 21)
				GIO.displayMessage(name + ", you have lost.");
				
			else if ((dealerScore > 21) || (hand.getScore() > dealerScore))
				GIO.displayMessage(name + ", you have won!");
			
			else if (hand.getScore() == dealerScore)
				GIO.displayMessage(name + ", you have pushed.");
		}
		
		hand.discardAll();
		window.redraw();
	}
}
