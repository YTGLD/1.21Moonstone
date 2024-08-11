package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.item.maxitem.max_eye;
import com.moonstone.moonstonemod.item.maxitem.max_sword;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class Blood {
    public Blood(@NotNull PoseStack matrices,
                 @NotNull MultiBufferSource vertexConsumers,
                 int light,
                 @NotNull Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (Handler.hascurio(living, Items.max_eye.get())) {
                CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.max_eye.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        {
                                            float size = stack.get(DataReg.tag).getFloat(max_eye.blood)+0.1f;
                                            matrices.pushPose();
                                            matrices.scale(0.33f,0.33f,0.33f);
                                            matrices.translate(0, -2, 0.5);
                                            renderSphere1(matrices, vertexConsumers, light, size);
                                            matrices.popPose();
                                            {
                                                matrices.pushPose();
                                                matrices.scale(0.33f,0.33f,0.33f);
                                                int s = living.tickCount % 360;
                                                if (s > 180) {
                                                    s = 0;
                                                }
                                                matrices.mulPose(Axis.YN.rotationDegrees(s));
                                                matrices.mulPose(Axis.XN.rotationDegrees(s));

                                                matrices.translate(0, -2, 0.25);
                                                renderSphere1(matrices, vertexConsumers, light, 0.2f);
                                                matrices.popPose();

                                            }
                                            {
                                                matrices.pushPose();
                                                matrices.scale(0.33f,0.33f,0.33f);
                                                int s = living.tickCount * 2 % 360;
                                                if (s > 180) {
                                                    s = 0;
                                                }
                                                matrices.mulPose(Axis.YN.rotationDegrees(s));
                                                matrices.mulPose(Axis.XN.rotationDegrees(s));

                                                matrices.translate(0, -2, 0.25);
                                                renderSphere1(matrices, vertexConsumers, light, 0.2f);
                                                matrices.popPose();

                                            }

                                            {
                                                matrices.pushPose();
                                                matrices.scale(0.33f,0.33f,0.33f);
                                                matrices.mulPose(Axis.ZP.rotationDegrees(180));
                                                matrices.mulPose(Axis.YP.rotationDegrees(180));
                                                matrices.mulPose(Axis.XP.rotationDegrees(180));
                                                int s = living.tickCount * 3 % 360;
                                                if (s > 180) {
                                                    s = 0;
                                                }
                                                matrices.mulPose(Axis.YN.rotationDegrees(s));
                                                matrices.mulPose(Axis.XN.rotationDegrees(s));

                                                matrices.translate(0, -2, 0.25);
                                                renderSphere1(matrices, vertexConsumers, light, 0.2f);
                                                matrices.popPose();

                                            }
                                            {
                                                matrices.pushPose();
                                                matrices.scale(0.33f,0.33f,0.33f);
                                                matrices.mulPose(Axis.ZP.rotationDegrees(180));
                                                matrices.mulPose(Axis.YP.rotationDegrees(180));
                                                matrices.mulPose(Axis.XP.rotationDegrees(180));

                                                int s = living.tickCount * 4 % 360;
                                                if (s > 180) {
                                                    s = 0;
                                                }
                                                matrices.mulPose(Axis.YN.rotationDegrees(s));
                                                matrices.mulPose(Axis.XN.rotationDegrees(s));

                                                matrices.translate(0, -2, 0.25);
                                                renderSphere1(matrices, vertexConsumers, light, 0.2f);
                                                matrices.popPose();

                                            }
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
    public void renderSphere1(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, float s) {
        float radius = s; // 球体的半径
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.gateways());
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = radius * (float) Math.cos(phi0);
                float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = radius * (float) Math.cos(phi0);
                float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = radius * (float) Math.cos(phi1);
                float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = radius * (float) Math.cos(phi1);
                float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 1.0f, 1.0f, 1.0f).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
            }
        }
    }
}
