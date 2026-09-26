package rege.chemicalcompound.mod115.crnobr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.monster.EntityCreeper;

@Mixin(value = net.minecraft.entity.Entity.class, priority = 5000)
public abstract class EntityMixin {
	@Inject(method = "func_145774_a", at = @At("RETURN"), cancellable = true)
	private void modify(CallbackInfoReturnable<Boolean> info) {
		Object THIS = this;
		if (info.getReturnValueZ() && THIS instanceof EntityCreeper && !((EntityCreeper)THIS).func_146078_ca()) {
			info.setReturnValue(false);
		}
	}
}
