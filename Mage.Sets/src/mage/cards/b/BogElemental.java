
package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.SacrificeSourceUnlessPaysEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.StaticFilters;

/**
 *
 * @author fireshoes
 */
public final class BogElemental extends CardImpl {
    
    public BogElemental(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{3}{B}{B}");
        this.subtype.add(SubType.ELEMENTAL);
        this.power = new MageInt(5);
        this.toughness = new MageInt(4);

        // Protection from white
        this.addAbility(ProtectionAbility.from(ObjectColor.WHITE));
        
        // At the beginning of your upkeep, sacrifice Bog Elemental unless you sacrifice a land.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new SacrificeSourceUnlessPaysEffect(new SacrificeTargetCost(StaticFilters.FILTER_LAND))
        ));
    }

    private BogElemental(final BogElemental card) {
        super(card);
    }

    @Override
    public BogElemental copy() {
        return new BogElemental(this);
    }
}
