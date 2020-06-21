package com.softsign.simplecb.procedures;

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

import com.softsign.simplecb.ScbModElements;

@ScbModElements.ModElement.Tag
public class AddCommandBlockProcedure extends ScbModElements.ModElement {
	public AddCommandBlockProcedure(ScbModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AddCommandBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double CurrentAddedSlotCB = 0;
		if (((new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) entity).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							ItemStack stack = ((Slot) ((Map) invobj).get(sltid)).getStack();;
							if (stack != null)
								return stack.getCount();
						}
					}
				}
				return 0;
			}
		}.getAmount((int) ((CurrentAddedSlotCB)))) > 0)) {
			if (entity instanceof PlayerEntity) {
				Container _current = ((PlayerEntity) entity).openContainer;
				if (_current instanceof Supplier) {
					Object invobj = ((Supplier) _current).get();
					if (invobj instanceof Map) {
						ItemStack _setstack = new ItemStack(Blocks.COMMAND_BLOCK, (int) (1));
						_setstack.setCount((int) 1);
						((Slot) ((Map) invobj).get((int) ((CurrentAddedSlotCB)))).putStack(_setstack);
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
			}.getItemStack((int) ((CurrentAddedSlotCB))))).setDisplayName(new StringTextComponent((("Block #") + "" + ((CurrentAddedSlotCB)))));
			CurrentAddedSlotCB = (double) ((CurrentAddedSlotCB) + 1);
		}
	}
}
