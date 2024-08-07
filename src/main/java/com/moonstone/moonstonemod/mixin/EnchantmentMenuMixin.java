package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.Items;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {

    @Shadow
    @Final
    private Container enchantSlots;

    @Shadow
    @Final
    public int[] costs;
    @Shadow @Final public int[] levelClue;
    @Shadow @Final private RandomSource random;

    @Shadow @Final private ContainerLevelAccess access;

    @Shadow @Final private DataSlot enchantmentSeed;

    @Shadow public abstract void slotsChanged(Container p_39461_);

    @Shadow protected abstract List<EnchantmentInstance> getEnchantmentList(RegistryAccess p_345264_, ItemStack p_39472_, int p_39473_, int p_39474_);

    @Inject(at = @At("HEAD"), method = "clickMenuButton", cancellable = true)
    public void moonstone$clickMenuButton(Player p_39465_, int p_39466_, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = enchantSlots.getItem(0);
        Player player = p_39465_;
        if (Handler.hascurio(player, Items.nightmaremoai.get())) {
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (!(itemstack.getItem() instanceof BookItem)) {
                    EnchantmentMenu container = (EnchantmentMenu) (Object) this;
                    access.execute((level, pos) -> {
                        List<EnchantmentInstance> rolledEnchantments = getEnchantmentList(level.registryAccess(),itemstack, p_39466_, container.costs[p_39466_]);
                        player.onEnchantmentPerformed(itemstack, p_39466_ + Config.nightmare_moai.get());
                        for (EnchantmentInstance data : rolledEnchantments) {
                            itemstack.enchant(data.enchantment, data.level +  Config.nightmare_moai.get());
                            enchantSlots.setChanged();
                            enchantmentSeed.set(player.getEnchantmentSeed());
                            slotsChanged(enchantSlots);

                        }
                    });
                }

            }

        }

    }
}
