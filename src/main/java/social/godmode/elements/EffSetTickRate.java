package social.godmode.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import social.godmode.SetTickRateAddon;

public class EffSetTickRate extends Effect {

    static {
        Skript.registerEffect(EffSetTickRate.class, "set tick rate of %player% to %number% [and (1:freeze|2:unfreeze) their animation[s]]");
    }

    Expression<Player> player;
    Expression<Number> tickRate;
    boolean frozen;

    @Override
    protected void execute(Event event) {
        Player p = player.getSingle(event);
        if (p == null) return;
        float rate = tickRate.getOptionalSingle(event).map(Number::floatValue).orElse(-1f);
        if (rate < 1 || rate > 20) return;
        SetTickRateAddon.getPlugin(SetTickRateAddon.class).setTickRate(p, rate, frozen);
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "set tick rate of " + player.toString(event, b) + " to " + tickRate.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        tickRate = (Expression<Number>) expressions[1];
        frozen = parseResult.mark == 1;
        return true;
    }
}
