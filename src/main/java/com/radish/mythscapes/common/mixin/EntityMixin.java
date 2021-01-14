package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.tags.MythFluidTags;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
 * For handling fluid motions for fluids
 * without the need of tagging them as water.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public World world;

    @Shadow
    protected boolean eyesInWater;

    @Shadow
    protected Object2DoubleMap<ITag<Fluid>> eyesFluidLevel;

    @Shadow
    public abstract AxisAlignedBB getBoundingBox();

    @Shadow
    public abstract boolean areEyesInFluid(ITag<Fluid> tagIn);

    @Shadow
    public abstract boolean isPushedByWater();

    @Shadow
    public abstract void setMotion(Vector3d motionIn);

    @Shadow
    public abstract Vector3d getMotion();

    @Shadow
    public abstract EntityType<?> getType();

    //@Inject(method = "handleFluidAcceleration", at = @At("HEAD"), cancellable = true)
    public void onHandleFluidAcceleration(ITag<Fluid> fluidTag, double p_210500_2_, CallbackInfoReturnable<Boolean> callbackInfo) {
        AxisAlignedBB axisalignedbb = this.getBoundingBox().shrink(0.001D);
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.ceil(axisalignedbb.maxY);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);

        if (!this.world.isAreaLoaded(i, k, i1, j, l, j1)) {
            callbackInfo.setReturnValue(false);
        }
        else {
            double d0 = 0.0D;
            boolean flag = this.isPushedByWater();
            boolean flag1 = false;
            Vector3d vector3d = Vector3d.ZERO;
            int k1 = 0;
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(int l1 = i; l1 < j; ++l1) {
                for(int i2 = k; i2 < l; ++i2) {
                    for(int j2 = i1; j2 < j1; ++j2) {
                        blockpos$mutable.setPos(l1, i2, j2);
                        FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
                        if (fluidstate.isTagged(MythFluidTags.SWIMMABLE)) {
                            double d1 = (double)((float)i2 + fluidstate.getActualHeight(this.world, blockpos$mutable));
                            if (d1 >= axisalignedbb.minY) {
                                flag1 = true;
                                d0 = Math.max(d1 - axisalignedbb.minY, d0);
                                if (flag) {
                                    Vector3d vector3d1 = fluidstate.getFlow(this.world, blockpos$mutable);
                                    if (d0 < 0.4D) {
                                        vector3d1 = vector3d1.scale(d0);
                                    }

                                    vector3d = vector3d.add(vector3d1);
                                    ++k1;
                                }
                            }
                        }
                    }
                }
            }
            if (vector3d.length() > 0.0D) {
                if (k1 > 0) {
                    vector3d = vector3d.scale(1.0D / (double)k1);
                }
                if (this.getType() != EntityType.PLAYER) {
                    vector3d = vector3d.normalize();
                }
                Vector3d vector3d2 = this.getMotion();
                vector3d = vector3d.scale(p_210500_2_);

                if (Math.abs(vector3d2.x) < 0.003D && Math.abs(vector3d2.z) < 0.003D && vector3d.length() < 0.0045000000000000005D) {
                    vector3d = vector3d.normalize().scale(0.0045000000000000005D);
                }
                this.setMotion(this.getMotion().add(vector3d));
            }
            this.eyesFluidLevel.put(MythFluidTags.SWIMMABLE, d0);

            if (flag1)
                callbackInfo.setReturnValue(true);
        }
    }

    //@Inject(method = "updateEyesInWater", at = @At(value = "RETURN", ordinal = 1))
    private void onUpdateEyesInWater(CallbackInfo callbackInfo) {
        this.eyesInWater = this.areEyesInFluid(MythFluidTags.SWIMMABLE);
    }
}
