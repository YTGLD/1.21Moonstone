package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.moonstoneitem.Doom;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class max_sword  extends Item implements Iplague, ICurioItem {
    public static final String MaxSword = "MaxSword";


    public max_sword() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_sword.get())){



                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()){
                                if (stack.is(Items.max_sword.get())){
                                    if (stack.get(DataReg.tag)!=null){
                                        if (Mth.nextInt(RandomSource.create(),1,2)==1) {
                                            stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) + 1);
                                        }
                                        if (stack.get(DataReg.tag).getInt(MaxSword)>=6){
                                            event.setAmount(event.getAmount()*3);
                                            stack.get(DataReg.tag).remove(MaxSword);
                                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_ATTACK_IMPACT, SoundSource.NEUTRAL, 0.7F, 0.7F);
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 0), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));
                                            player.level().levelEvent(2001, new BlockPos((int)player.getX(), (int) (player.getY() + 1), (int) player.getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.get(DataReg.tag)!=null) {
            if (!slotContext.entity().level().isClientSide){
                if (slotContext.entity().tickCount%200==0){
                    if (stack.get(DataReg.tag).getInt(MaxSword) > 0) {
                        stack.get(DataReg.tag).putInt(MaxSword, stack.get(DataReg.tag).getInt(MaxSword) - 1);
                        slotContext.entity().heal(4+slotContext.entity().getMaxHealth()/50);
                        slotContext.entity().level().levelEvent(2001, new BlockPos((int) slotContext.entity().getX(), (int) (slotContext.entity().getY() + 1), (int) slotContext.entity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                    }
                }
            }

        }
    }
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_sword.tool.string.4").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
