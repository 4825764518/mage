package mage.cards.g;

import mage.MageInt;
import mage.abilities.common.AttacksAloneSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.condition.common.KickedCondition;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerValue;
import mage.abilities.dynamicvalue.common.SourcePermanentToughnessValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;

import java.util.UUID;

/**
 * @author JRHerlehy
 */
public final class GrunnTheLonelyKing extends CardImpl {

    public GrunnTheLonelyKing(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{G}{G}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.APE, SubType.WARRIOR);

        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        // Kicker {3} (You may pay an additional {3} as you cast this spell.)
        this.addAbility(new KickerAbility("{3}"));

        // If Grunn, the Lonely King was kicked, it enters with five +1/+1 counters on it.
        this.addAbility(new EntersBattlefieldAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance(5)),
                                                     KickedCondition.ONCE, "If {this} was kicked, it enters with five +1/+1 counters on it.", ""));

        // Whenever Grunn attacks alone, double its power and toughness until end of turn.
        Effect effect = new BoostSourceEffect(SourcePermanentPowerValue.ALLOW_NEGATIVE, SourcePermanentToughnessValue.instance, Duration.EndOfTurn);
        effect.setText("double its power and toughness until end of turn");
        this.addAbility(new AttacksAloneSourceTriggeredAbility(effect));
    }

    private GrunnTheLonelyKing(final GrunnTheLonelyKing card) {
        super(card);
    }

    @Override
    public GrunnTheLonelyKing copy() {
        return new GrunnTheLonelyKing(this);
    }
}
