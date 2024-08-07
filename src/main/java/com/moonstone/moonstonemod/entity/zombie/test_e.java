package com.moonstone.moonstonemod.entity.zombie;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class test_e extends cell_zombie {

    public test_e(EntityType<? extends test_e> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
        this.setNoAi(true);
    }
    public int time = 0;
    public void tick() {
        super.tick();
        this.time++;
        if (this.time > 50) {
            this.discard();
        }
    }
    public boolean hurt(DamageSource p_27424_, float p_27425_) {
        return false;
    }



}


