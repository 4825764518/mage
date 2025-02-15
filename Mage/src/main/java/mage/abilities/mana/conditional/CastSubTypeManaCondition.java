package mage.abilities.mana.conditional;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.condition.Condition;
import mage.abilities.costs.Cost;
import mage.cards.Card;
import mage.constants.SubType;
import mage.game.Game;

import java.util.UUID;

public class CastSubTypeManaCondition extends ManaCondition implements Condition {
    private final SubType[] subTypes;

    public CastSubTypeManaCondition(SubType... subTypes) {
        this.subTypes = subTypes;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        if (source instanceof SpellAbility) {
            Card card = game.getCard(source.getSourceId());
            if (card == null) {
                return false;
            }

            for (SubType subType : subTypes) {
                if (!card.getSubtype().contains(subType)) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean apply(Game game, Ability source, UUID originalId, Cost costToPay) {
        return apply(game, source);
    }
}
