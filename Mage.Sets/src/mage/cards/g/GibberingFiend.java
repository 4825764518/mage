package mage.cards.g;

import java.util.UUID;

import mage.MageInt;
import mage.abilities.dynamicvalue.common.CardTypesInGraveyardCount;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.common.DeliriumCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.DamagePlayersEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;

/**
 * @author fireshoes
 */
public final class GibberingFiend extends CardImpl {

    public GibberingFiend(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");
        this.subtype.add(SubType.DEVIL);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // When Gibbering Fiend enters the battlefield, it deals 1 damage to each opponent.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new DamagePlayersEffect(1, TargetController.OPPONENT, "it"), false));

        // <i>Delirium</i> &mdash; At the beginning of each opponent's upkeep, if there are four or more card types among cards in your graveyard,
        // Gibbering Fiend deals 1 damage to that player.
        this.addAbility(new ConditionalInterveningIfTriggeredAbility(
                new BeginningOfUpkeepTriggeredAbility(TargetController.OPPONENT, new DamageTargetEffect(1), false),
                DeliriumCondition.instance,
                "<i>Delirium</i> &mdash; At the beginning of each opponent's upkeep, if there are four or more card types among cards in your graveyard, "
                        + "{this} deals 1 damage to that player.")
                .addHint(CardTypesInGraveyardCount.YOU.getHint()));
    }

    private GibberingFiend(final GibberingFiend card) {
        super(card);
    }

    @Override
    public GibberingFiend copy() {
        return new GibberingFiend(this);
    }
}
