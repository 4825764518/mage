package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.ruleModifying.PlayFromGraveyardControllerEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterNonlandCard;
import mage.game.Game;
import mage.game.events.GameEvent;

import java.util.UUID;

/**
 * @author Mainiack11
 */
public final class HaakonStromgaldScourge extends CardImpl {

    private static final FilterNonlandCard filter = new FilterNonlandCard("Knight spells");
    static {
        filter.add(SubType.KNIGHT.getPredicate());
    }

    public HaakonStromgaldScourge(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}{B}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ZOMBIE);
        this.subtype.add(SubType.KNIGHT);

        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // You may cast Haakon, Stromgald Scourge from your graveyard, but not from anywhere else.
        Ability ability = new SimpleStaticAbility(Zone.ALL, new HaakonStromgaldScourgePlayEffect());
        ability.addEffect(new HaakonStromgaldScourgePlayEffect2());
        this.addAbility(ability);

        // As long as Haakon is on the battlefield, you may cast Knight spells from your graveyard.
        this.addAbility(new SimpleStaticAbility(new PlayFromGraveyardControllerEffect(filter)
                .setText("As long as {this} is on the battlefield, you may cast Knight spells from your graveyard")));

        // When Haakon dies, you lose 2 life.
        this.addAbility(new DiesSourceTriggeredAbility(new LoseLifeSourceControllerEffect(2)));

    }

    private HaakonStromgaldScourge(final HaakonStromgaldScourge card) {
        super(card);
    }

    @Override
    public HaakonStromgaldScourge copy() {
        return new HaakonStromgaldScourge(this);
    }

}

class HaakonStromgaldScourgePlayEffect extends AsThoughEffectImpl {

    HaakonStromgaldScourgePlayEffect() {
        super(AsThoughEffectType.CAST_FROM_NOT_OWN_HAND_ZONE, Duration.EndOfGame, Outcome.Benefit);
        staticText = "You may cast {this} from your graveyard";
    }

    private HaakonStromgaldScourgePlayEffect(final HaakonStromgaldScourgePlayEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public HaakonStromgaldScourgePlayEffect copy() {
        return new HaakonStromgaldScourgePlayEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        if (objectId.equals(source.getSourceId()) &&
                affectedControllerId.equals(source.getControllerId())) {
            Card card = game.getCard(source.getSourceId());
            return card != null && game.getState().getZone(source.getSourceId()) == Zone.GRAVEYARD;
        }
        return false;
    }
}

class HaakonStromgaldScourgePlayEffect2 extends ContinuousRuleModifyingEffectImpl {

    public HaakonStromgaldScourgePlayEffect2() {
        super(Duration.EndOfGame, Outcome.Detriment);
        staticText = ", but not from anywhere else";
    }

    private HaakonStromgaldScourgePlayEffect2(final HaakonStromgaldScourgePlayEffect2 effect) {
        super(effect);
    }

    @Override
    public HaakonStromgaldScourgePlayEffect2 copy() {
        return new HaakonStromgaldScourgePlayEffect2(this);
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.CAST_SPELL;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        Card card = game.getCard(event.getSourceId());
        if (card != null && card.getId().equals(source.getSourceId())) {
            Zone zone = game.getState().getZone(card.getId());
            return zone != null && (zone != Zone.GRAVEYARD);
        }
        return false;
    }
}
