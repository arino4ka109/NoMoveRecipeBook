package com.example.mixin.client;

import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookWidget.class)
public abstract class RecipeBookWidgetMixin {
    private static final int CENTERED_SCREEN_BOOK_OFFSET = 162;

    @Inject(method = "findLeftEdge", at = @At("HEAD"), cancellable = true)
    private void noscreenmove$keepScreenCentered(int width, int backgroundWidth, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((width - backgroundWidth) / 2);
    }

    @ModifyConstant(method = "reset", constant = @Constant(intValue = 86))
    private int noscreenmove$moveRecipeBookBesideCenteredScreen(int originalOffset) {
        return CENTERED_SCREEN_BOOK_OFFSET;
    }
}
