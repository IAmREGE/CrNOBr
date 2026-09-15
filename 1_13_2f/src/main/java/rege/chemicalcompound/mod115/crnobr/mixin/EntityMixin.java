package rege.chemicalcompound.mod115.crnobr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;

@Mixin(value = net.minecraft.entity.Entity.class, priority = 5000)
public abstract class EntityMixin {
	@ModifyReturnValue(method = "method_10933", at = @At("RETURN"))
	private boolean modify(boolean original, Explosion explosion, BlockView blockView, BlockPos blockPos, BlockState blockState, float f) {
		Object THIS = this;
		return original && (!(THIS instanceof CreeperEntity) || ((CreeperEntity)THIS).isIgnited());
	}
}
