package rege.chemicalcompound.mod115.crnobr.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = net.minecraft.world.entity.Entity.class, priority = 5000)
public abstract class EntityMixin {
	@ModifyReturnValue(method = "shouldBlockExplode", at = @At("RETURN"))
	private boolean modify(boolean original, Explosion explosion, BlockGetter level, BlockPos pos, BlockState state, float power) {
		Object THIS = this;
		return original && (!(THIS instanceof Creeper) || ((Creeper)THIS).isIgnited());
	}
}
