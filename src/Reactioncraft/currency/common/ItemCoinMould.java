package Reactioncraft.currency.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import Reactioncraft.basemod.RCB;

public class ItemCoinMould extends Item
{
	public ItemCoinMould(int par1) 
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setMaxDamage(30);
		this.setCreativeTab(RCB.ReactioncraftItems);
		this.setNoRepair();
	}
}