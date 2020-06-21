package com.softsign.simplecb.procedures;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.function.Supplier;
import java.util.Map;

import com.softsign.simplecb.SimpleCommandBlocksModVariables;
import com.softsign.simplecb.SimpleCommandBlocksModElements;

@SimpleCommandBlocksModElements.ModElement.Tag
public class AddCommandBlockProcedure extends SimpleCommandBlocksModElements.ModElement {
	public AddCommandBlockProcedure(SimpleCommandBlocksModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AddCommandBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AddCommandBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = new ItemStack(Blocks.COMMAND_BLOCK, (int) (1));
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) ((SimpleCommandBlocksModVariables.WorldVariables.get(world).CurrentSlotCB))))
							.putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		((new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) ((SimpleCommandBlocksModVariables.WorldVariables.get(world).CurrentSlotCB)))))
				.setDisplayName(new StringTextComponent((((SimpleCommandBlocksModVariables.WorldVariables.get(world).CurrentSlotCB)) + "" + (null))));
		SimpleCommandBlocksModVariables.WorldVariables
				.get(world).CurrentSlotCB = (double) ((SimpleCommandBlocksModVariables.WorldVariables.get(world).CurrentSlotCB) + 1);
		SimpleCommandBlocksModVariables.WorldVariables.get(world).syncData(world);
	}
}
