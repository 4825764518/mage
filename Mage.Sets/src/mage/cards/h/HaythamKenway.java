package mage.cards.h;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.ExileUntilSourceLeavesEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.common.FilterCreaturePermanent;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetadjustment.ForEachOpponentTargetsAdjuster;
import mage.target.targetpointer.EachTargetPointer;

import java.util.UUID;

public class HaythamKenway extends CardImpl {
    private static final FilterCreaturePermanent assassinFilter
            = new FilterCreaturePermanent(SubType.ASSASSIN, "Assassins");
    private static final FilterCreaturePermanent knightFilter
            = new FilterCreaturePermanent(SubType.KNIGHT, "Knights");

    public HaythamKenway(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{U}");
        this.addSuperType(SuperType.LEGENDARY);

        this.addSubType(SubType.HUMAN);
        this.addSubType(SubType.KNIGHT);

        // protection from assassins
        this.addAbility(new ProtectionAbility(assassinFilter));

        // other knights you control get +2/+2 and have protection from assassins
        Ability ability = new SimpleStaticAbility(new BoostControlledEffect(
                2, 2, Duration.WhileOnBattlefield, knightFilter, true
        ));
        ability.addEffect(new GainAbilityControlledEffect(
                new ProtectionAbility(assassinFilter), Duration.WhileOnBattlefield, knightFilter, true
        ).setText("and have protection from Assassins"));
        this.addAbility(ability);

        // when haytham kenway enters the battlefield, for each opponent, exile up to one target creature that player controls until haytham kenway leaves the battlefield
        Ability etbAbility = new EntersBattlefieldTriggeredAbility(new ExileUntilSourceLeavesEffect()
                .setTargetPointer(new EachTargetPointer())
                .setText("for each opponent, exile up to one target creature that player controls until {this} leaves the battlefield")
        );
        etbAbility.addTarget(new TargetCreaturePermanent(0, 1));
        etbAbility.setTargetAdjuster(new ForEachOpponentTargetsAdjuster());
        this.addAbility(etbAbility);
    }

    public HaythamKenway(HaythamKenway card) {
        super(card);
    }

    @Override
    public HaythamKenway copy() {
        return new HaythamKenway(this);
    }
}
