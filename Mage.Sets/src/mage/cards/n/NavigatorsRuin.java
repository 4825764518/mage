package mage.cards.n;

import mage.abilities.Ability;
import mage.abilities.triggers.BeginningOfEndStepTriggeredAbility;
import mage.abilities.condition.common.RaidCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.MillCardsTargetEffect;
import mage.abilities.hint.common.RaidHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AbilityWord;
import mage.constants.CardType;
import mage.target.common.TargetOpponent;
import mage.watchers.common.PlayerAttackedWatcher;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class NavigatorsRuin extends CardImpl {

    public NavigatorsRuin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{2}{U}");

        // Raid - At the beginning of your end step, if you attacked with a creature this turm, target opponent puts the top four cards of their library into their graveyard.
        Ability ability = new ConditionalInterveningIfTriggeredAbility(new BeginningOfEndStepTriggeredAbility(
                new MillCardsTargetEffect(4)),
                RaidCondition.instance,
                "At the beginning of your end step, " +
                        "if you attacked this turn, target opponent mills four cards."
        );
        ability.addTarget(new TargetOpponent());
        ability.setAbilityWord(AbilityWord.RAID);
        ability.addHint(RaidHint.instance);
        this.addAbility(ability, new PlayerAttackedWatcher());
    }

    private NavigatorsRuin(final NavigatorsRuin card) {
        super(card);
    }

    @Override
    public NavigatorsRuin copy() {
        return new NavigatorsRuin(this);
    }
}
