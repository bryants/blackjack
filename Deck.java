import java.util.Random;

// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
	private Card[] deck;
	private int deckSize = 52;
	private int ctr = 0;
	
	// This constructor builds a deck of 52 cards.
	public Deck()
	{
		deck = new Card[52];
		int index = 0;
		
		for (int i = 0; i < 4; i++){
			for (int j = 1; j < 14; j++){
				deck[index++] = new Card(i, j);
			}
		}
	}

	// This method shuffles the deck (randomizes the array of cards).
	// Hint: loop over the cards and swap each one with another card in a random position.
	public void shuffle()
	{
		Random r = new Random();
		
		for (Card c : deck){
			int x = r.nextInt(52);
			Card temp = c;
			c = deck[x];
			deck[x] = temp;
		}
	}
	
	// This method takes the top card off the deck and returns it.
	public Card drawCard()
	{
		Card current = deck[ctr];
		deck[ctr] = null;
		ctr++;
		deckSize--;
		return current;
	}

	// This method returns the number of cards left in the deck.
	public int getSize()
	{
		return deckSize;
	}
}

