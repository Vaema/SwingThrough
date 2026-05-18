package astra.swingthrough;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;

import java.util.function.Predicate;

@Mod("swingthrough")
public class SwingThrough {

    public void SwingThroughGrass(IEventBus modEventBus) {
        modEventBus.addListener(this::processIMC);
    }

    @SuppressWarnings("unchecked")
    private void processIMC(final InterModProcessEvent event) {
        event.getIMCStream("ENTITY_FILTER"::equals)
                .map(InterModComms.IMCMessage::messageSupplier)
                .map(supplier -> (Predicate<LivingEntity>) supplier.get())
                .forEach(LeftClickHandler.PREDICATES::add);
    }
}
