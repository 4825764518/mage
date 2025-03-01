package mage.cards.v;

import mage.abilities.Ability;
import mage.abilities.LoyaltyAbility;
import mage.abilities.effects.common.DamageWithPowerFromOneToAnotherTargetEffect;
import mage.abilities.effects.common.WishEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.counter.DistributeCountersEffect;
import mage.abilities.hint.common.OpenSideboardHint;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetCreatureOrPlaneswalker;
import mage.target.common.TargetCreaturePermanentAmount;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class VivienArkbowRanger extends CardImpl {

    public VivienArkbowRanger(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.PLANESWALKER}, "{1}{G}{G}{G}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.VIVIEN);
        this.setStartingLoyalty(4);

        // +1: Distribute two +1/+1 counters among up to two target creatures. They gain trample until end of turn.
        Ability ability = new LoyaltyAbility(new DistributeCountersEffect()
                .setText("distribute two +1/+1 counters among up to two target creatures"), 1);
        ability.addEffect(new GainAbilityTargetEffect(
                TrampleAbility.getInstance(), Duration.EndOfTurn,
                "They gain trample until end of turn"
        ));
        ability.addTarget(new TargetCreaturePermanentAmount(2, 0, 2));
        this.addAbility(ability);

        // −3: Target creature you control deals damage equal to its power to target creature or planeswalker.
        ability = new LoyaltyAbility(new DamageWithPowerFromOneToAnotherTargetEffect(), -3);
        ability.addTarget(new TargetControlledCreaturePermanent());
        ability.addTarget(new TargetCreatureOrPlaneswalker());
        this.addAbility(ability);

        // −5: You may reveal a creature card you own from outside the game and put it into your hand.
        this.addAbility(new LoyaltyAbility(new WishEffect(StaticFilters.FILTER_CARD_CREATURE), -5)
                .addHint(OpenSideboardHint.instance));
    }

    private VivienArkbowRanger(final VivienArkbowRanger card) {
        super(card);
    }

    @Override
    public VivienArkbowRanger copy() {
        return new VivienArkbowRanger(this);
    }
}
