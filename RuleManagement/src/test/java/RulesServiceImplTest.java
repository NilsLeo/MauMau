// @ExtendWith(MockitoExtension.class)
public class RulesServiceImplTest {

    // @Mock
    // private Card card;

    // @Mock
    // private Rules rules;

    // private RulesServiceImpl rulesService;
    // private Suit leadSuit;
    // private Value leadValue;

    // @BeforeEach
    // public void setUp() {
    //     rulesService = new RulesServiceImpl();
    //     leadSuit = Suit.CLUBS;
    //     leadValue = Value.ACE;
    // }

    // @Test
    // public void testIsCardValid_ShouldReturnTrue() {
    //     when(rules.isChooseSuitOnJack()).thenReturn(false);
    //     when(card.getSuit()).thenReturn(leadSuit);
    //     when(card.getValue()).thenReturn(leadValue);
    //     assertTrue(rulesService.isCardValid(card, leadSuit, leadValue, rules));
    // }

    // @Test
    // public void testIsCardValid_ShouldReturnFalse() {
    //     when(rules.isChooseSuitOnJack()).thenReturn(false);
    //     when(card.getSuit()).thenReturn(Suit.DIAMONDS);
    //     when(card.getValue()).thenReturn(Value.JACK);
    //     assertFalse(rulesService.isCardValid(card, leadSuit, leadValue, rules));
    // }

    // @Test
    // public void testIsCardValid_ChooseSuitOnJack_ShouldReturnTrue() {
    //     when(rules.isChooseSuitOnJack()).thenReturn(true);
    //     when(rules.getSuit()).thenReturn(Suit.CLUBS);
    //     when(card.getSuit()).thenReturn(Suit.CLUBS);
    //     when(card.getValue()).thenReturn(Value.JACK);
    //     assertTrue(rulesService.isCardValid(card, leadSuit, leadValue, rules));
    // }

}
