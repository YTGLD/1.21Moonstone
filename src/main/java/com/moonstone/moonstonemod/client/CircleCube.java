package com.moonstone.moonstonemod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.moonstone.moonstonemod.client.renderer.MRender;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class CircleCube {

    public CircleCube(@NotNull PoseStack matrices,
                      @NotNull MultiBufferSource vertexConsumers,
                      int light,
                      @NotNull Entity entity) {

        float s =  entity.tickCount*1.5f;
        if (s > 100*1.5f) {
            s = 0;
        }

        float ds = entity.tickCount;
        if (ds > 100) {
            ds = 0;
        }
        float alp = 0.1f;
        s /= 10;
        {

            matrices.pushPose();
            renderCircle3(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle4(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle5(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle6(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }
        {

            matrices.pushPose();
            renderCircle8(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }



        {

            matrices.pushPose();

            renderCircle7(matrices, vertexConsumers, light, 0, 0, 0,s, alp, entity);
            matrices.popPose();
        }

    }



    public  void renderCircle3(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius,float alp , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 8; // 垂直段数
        int horizontalSegments = 8; // 水平段数
        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (-entity.tickCount*0.1)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (-entity.tickCount * 0.1)));


        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public  void renderCircle6(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius,float alp , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 9; // 垂直段数
        int horizontalSegments = 9; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount*0.1)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 0.1)));
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public  void renderCircle5(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius,float alp , Entity entity)  {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 7; // 垂直段数
        int horizontalSegments = 7; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount*0.2)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 0.2)));
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public  void renderCircle4(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius,float alp , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 10; // 垂直段数
        int horizontalSegments = 10; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }
    public  void renderCircle7(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius ,float alp, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 12; // 垂直段数
        int horizontalSegments = 12; // 水平段数



        poseStack.translate(0 ,-1 ,0);
        poseStack.mulPose(Axis.XP.rotationDegrees((float) (entity.tickCount*0.5)));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (entity.tickCount * 0.5)));
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }

    }
    public  void renderCircle8(@NotNull PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float x, float y, float z, float radius ,float alp, Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.lines());
        int verticalSegments = 3; // 垂直段数
        int horizontalSegments = 3; // 水平段数

        poseStack.translate(0 ,-1 ,0);
        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);

                vertexConsumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
                vertexConsumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(0, 0, 0, alp).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(packedLight,packedLight).setNormal(1, 0, 0);
            }
        }
    }

}