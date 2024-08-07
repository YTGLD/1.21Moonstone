package com.moonstone.moonstonemod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class Handler {

    public static void set(ItemStack getItem,
                                 DeferredHolder<DataComponentType<?>, DataComponentType<String>> dataComponentType,
                                 String set){
        getItem.set(dataComponentType,set);
    }
    public static boolean getNotNullString(ItemStack getItem,
                                     DeferredHolder<DataComponentType<?>, DataComponentType<String>> dataComponentType){
        return getItem.get(dataComponentType) != null;
    }


    public static void set(ItemStack getItem,
                                 DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> dataComponentType,
                                 Integer set){
        getItem.set(dataComponentType,set);
    }
    public static boolean getNotNullInteger(ItemStack getItem,
                                     DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> dataComponentType){
        return getItem.get(dataComponentType) != null;
    }
    public static Integer getInteger(ItemStack getItem,
                                            DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> dataComponentType)
    {
        return getItem.get(dataComponentType);
    }







    public static <T extends TamableAnimal> void trySpawnMob(
            Player player,
            EntityType<T> pEntityType,
            MobSpawnType pSpawnType,
            ServerLevel pLevel,
            BlockPos pPos,
            int pAttempts,
            int p_216409_,
            int pYOffset,
            SpawnUtil.Strategy pStrategy
    ) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for (int i = 0; i < pAttempts; i++) {
            int j = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            int k = Mth.randomBetweenInclusive(pLevel.random, -p_216409_, p_216409_);
            blockpos$mutableblockpos.setWithOffset(pPos, j, pYOffset, k);
            if (pLevel.getWorldBorder().isWithinBounds(blockpos$mutableblockpos)
                    && moveToPossibleSpawnPosition(pLevel, pYOffset, blockpos$mutableblockpos, pStrategy)) {
                T t = (T)pEntityType.create(pLevel, null, blockpos$mutableblockpos, pSpawnType, false, false);
                if (t != null) {
                    t.setOwnerUUID(player.getUUID());
                    if (net.neoforged.neoforge.event.EventHooks.checkSpawnPosition(t, pLevel, pSpawnType)) {
                        pLevel.addFreshEntityWithPassengers(t);
                        return;
                    }

                    t.discard();
                }
            }
        }

    }

    private static boolean moveToPossibleSpawnPosition(ServerLevel pLevel, int pYOffset, BlockPos.MutableBlockPos pPos, SpawnUtil.Strategy pStrategy) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos().set(pPos);
        BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);

        for (int i = pYOffset; i >= -pYOffset; i--) {
            pPos.move(Direction.DOWN);
            blockpos$mutableblockpos.setWithOffset(pPos, Direction.UP);
            BlockState blockstate1 = pLevel.getBlockState(pPos);
            if (pStrategy.canSpawnOn(pLevel, pPos, blockstate1, blockpos$mutableblockpos, blockstate)) {
                pPos.move(Direction.UP);
                return true;
            }

            blockstate = blockstate1;
        }

        return false;
    }


    public static boolean hascurio(LivingEntity entity, Item curio) {
        if (entity instanceof Player player) {
            if (player.getCapability(CuriosCapability.INVENTORY)!= null) {
                if (CuriosApi.getCuriosInventory(entity).isPresent()) {
                    List<SlotResult> a = CuriosApi.getCuriosInventory(entity).get().findCurios(curio);
                    for (SlotResult slotResult : a) {
                        if (slotResult.stack().is(curio))
                            return true;
                    }
                }
            }
        }
        return false;

    }


}
