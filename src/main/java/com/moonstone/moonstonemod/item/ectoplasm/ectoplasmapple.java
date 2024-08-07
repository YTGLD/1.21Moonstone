package com.moonstone.moonstonemod.item.ectoplasm;

import com.moonstone.moonstonemod.moonstoneitem.ectoplasm;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ectoplasmapple extends ectoplasm {

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.ectoplasmapple.tool.string").withStyle(ChatFormatting.GOLD));
    }

}



