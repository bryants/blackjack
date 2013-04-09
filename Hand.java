// This class represents the set of cards held by one player (or the dealer).
public class Hand
{
	private Card[] hand;
	private int numCards;
	private int score;

	// This constructor builds a hand (with no cards, initially).
	public Hand()
	{
		hand = new Card[12];
		numCards = 0;
		score = 0;
	}
	
	// This method retrieves the size of this hand.
	public int getNumberOfCards()
	{
		return numCards;
	}

	// This method retrieves a particular card in this hand.  The card number is zero-based.
	public Card getCard(int index)
	{
		return hand[index];
	}

	// This method takes a card and places it into this hand.
	public void addCard(Card newcard)
	{
		hand[numCards++] = newcard;
		score += newcard.getValue();
	}

	// This method computes the score of this hand.
	public int getScore()
	{
		if (containsAce() && score < 11)
			return score + 10;
			
		return score;
	}

	// This methods discards all cards in this hand.
	public void discardAll()
	{
		hand = new Card[12];
		numCards = 0;
	}
	
	public boolean containsAce()
	{
		for (Card c : hand){
			if (c == null)
				{continue;}
			
			if (c.getValue() == 1)
				return true;
		}
		
		return false;
	}
	
	public void flipCardFaceUp(int index)
	{
		hand[index].turnFaceUp();
	}
}
