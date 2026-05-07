package fi.dy.masa.minihud.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public interface IMixinInGameHud
{
    @Accessor("debugHud")
    DebugHud minihud_getDebugHud();
}

