package rege.chemicalcompound.mod115.crnobr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.mob.CreeperEntity;

@Mixin(value = net.minecraft.entity.Entity.class, priority = 5000)
public abstract class EntityMixin {
	@ModifyReturnValue(method = "method_5379", at = @At("RETURN"))
	private boolean modify(boolean original) {
		Object THIS = this;
		return original && (!(THIS instanceof CreeperEntity) || ((CreeperEntity)THIS).isIgnited());
	}
}
