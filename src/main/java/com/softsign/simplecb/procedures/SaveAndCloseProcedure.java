package com.softsign.simplecb.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import com.softsign.simplecb.ScbModElements;

@ScbModElements.ModElement.Tag
public class SaveAndCloseProcedure extends ScbModElements.ModElement {
	public SaveAndCloseProcedure(ScbModElements instance) {
		super(instance, 3);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SaveAndClose!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
