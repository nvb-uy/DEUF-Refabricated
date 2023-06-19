package elocindev.deuf_refabricated.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.deuf_refabricated.DEUF_Refabricated;

@Mixin(ServerWorld.class)
public class ServerWorldMixin { 
	@Inject(at = @At("HEAD"), method = "addEntity", cancellable = true)
	private void blacklist(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity == null || entity instanceof PlayerEntity) return;
        
        if (entity.getWorld() instanceof ServerWorld world) {
        UUID uuid = entity.getUuid();
        
        Entity existing = world.getEntity(uuid);

        if (existing == null || existing == entity) return;
        
        UUID newUUID = MathHelper.randomUuid();
        while(world.getEntity(newUUID) != null) newUUID = MathHelper.randomUuid();
        entity.setUuid(newUUID);
        
        
        if (DEUF_Refabricated.Config.enable_logging)
                DEUF_Refabricated.LOGGER.info("Changing UUID of entity {} that already existed from {} to {}", Registries.ENTITY_TYPE.getKey(entity.getType()).toString(), uuid.toString(), newUUID.toString());
        }
    }
}