package export;


// @ExtendWith(MockitoExtension.class)
public class DeckServiceImplTest {

    //   @Test
    //   public void testShuffle() {
    //     // create an instance of the Deck class
    //     Deck deck = new Deck();

    //     // create a copy of the original list of cards
    //     List<Card> originalCards = new ArrayList<>(deck.getCards());

    //     // create an instance of the DeckServiceImpl class
    //     DeckServiceImpl deckService = new DeckServiceImpl();

    //     // call the shuffle method of the deckService instance
    //     deckService.shuffle(deck);

    //     // verifies that the 2 lists aren't equal, since they have a different order
    //     assertNotEquals(deck.getCards(), originalCards);

    //     // create a boolean variable to check if the lists have the same elements
    //     boolean sameElements = true;

    //     //iterate over the originalCards list
    //     for (Card card : originalCards) {
    //         if(!deck.getCards().contains(card)) {
    //             sameElements = false;
    //             break;
    //         }
    //     }
    //     //check if the current card is in the shuffled list
    //     for (Card card : deck.getCards()) {
    //         if(!originalCards.contains(card)) {
    //             sameElements = false;
    //             break;
    //         }
    //     }
    //     // verifies that the 2 lists have the same elements regardless of order
    //     assertTrue(sameElements);
    // }


    // @Test
    // public void testDealHandSize() {
    //     // create an instance of the Deck class
    //     Deck deck = new Deck();
    //     // create an instance of the DeckServiceImpl class
    //     DeckServiceImpl deckService = new DeckServiceImpl();

    //     // call the dealHand method of the deckService instance
    //     List<Card> hand = deckService.dealHand(deck);

    //     // check that the size of the hand is equal to 5
    //     assertEquals(5, hand.size());
    // }


    // @Test
    // public void testDeal() {
    //     // create an instance of the Deck class
    //     Deck deck = new Deck();

    //     // set the cards of the deck
    //     List<Card> cards = new ArrayList<>();
    //     cards.add(new Card(Suit.CLUBS, Value.ACE));
    //     cards.add(new Card(Suit.HEARTS, Value.SEVEN));
    //     cards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    //     deck.setCards(cards);

    //     // create an instance of the DeckServiceImpl class
    //     DeckServiceImpl deckService = new DeckServiceImpl();

    //     // get the first card from the deck
    //     Card card = cards.get(0);

    //     // deal the first card
    //     Card dealtCard = deckService.deal(deck);

    //     // get the first card after deal
    //     Card cardAfterDeal = cards.get(0);

    //     // Verify that the correct card was dealt
    //     assertEquals(card, dealtCard);
    //     assertNotEquals(cardAfterDeal, dealtCard);
    // }



}

