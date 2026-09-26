package rege.chemicalcompound.mod115.crnobr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.monster.Creeper;

@Mixin(value = net.minecraft.world.entity.Entity.class, priority = 5000)
public abstract class EntityMixin {
	@Inject(method = "shouldBlockExplode", at = @At("RETURN"), cancellable = true)
	private void modify(CallbackInfoReturnable<Boolean> info) {
		Object THIS = this;
		if (info.getReturnValueZ() && THIS instanceof Creeper && !((Creeper)THIS).isIgnited()) {
			info.setReturnValue(false);
		}
	}
}
