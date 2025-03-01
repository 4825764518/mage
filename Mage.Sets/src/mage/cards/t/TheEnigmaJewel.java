package mage.cards.t;

import mage.MageObject;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.keyword.CraftAbility;
import mage.abilities.mana.ConditionalColorlessManaAbility;
import mage.abilities.mana.builder.common.ActivatedAbilityManaBuilder;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SuperType;
import mage.filter.predicate.Predicate;
import mage.game.Game;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class TheEnigmaJewel extends CardImpl {

    public TheEnigmaJewel(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{U}");

        this.supertype.add(SuperType.LEGENDARY);
        this.secondSideCardClazz = mage.cards.l.LocusOfEnlightenment.class;

        // The Enigma Jewel enters the battlefield tapped.
        this.addAbility(new EntersBattlefieldTappedAbility());

        // {T}: Add {C}{C}. Spend this mana only to activate abilities.
        this.addAbility(new ConditionalColorlessManaAbility(2, new ActivatedAbilityManaBuilder()));

        // Craft with four or more nonlands with activated abilities {8}{U}
        this.addAbility(new CraftAbility(
                "{8}{U}", "four or more nonlands with activated abilities", "other " +
                "nonland permanents you control with activated abilities and/or nonland cards in your " +
                "graveyard with activated abilities", 4, Integer.MAX_VALUE, TheEnigmaJewelPredicate.instance
        ));
    }

    private TheEnigmaJewel(final TheEnigmaJewel card) {
        super(card);
    }

    @Override
    public TheEnigmaJewel copy() {
        return new TheEnigmaJewel(this);
    }
}

enum TheEnigmaJewelPredicate implements Predicate<MageObject> {
    instance;

    @Override
    public boolean apply(MageObject input, Game game) {
        return !input.isLand(game)
                && input instanceof Card
                && ((Card) input).getAbilities(game)
                .stream()
                .anyMatch(a -> (a.isActivatedAbility()));
    }
}
