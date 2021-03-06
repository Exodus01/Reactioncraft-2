package Reactioncraft.bookcase.common;

import java.util.List;
import java.util.Random;
import Reactioncraft.basemod.RCB;
import Reactioncraft.integration.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookcaseMulti extends Block
{
	
	private Icon[][] iconBuffer;
	
    public BlockBookcaseMulti(int var1, Material var2)
    {
        super(var1, var2);
        this.setCreativeTab(RCB.Reactioncraft);
        this.setUnlocalizedName("bookcasemeta");
        this.getLocalizedName();
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int var1, Random var2, int var3)
    {
        switch (var1)
        {
            case 0:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 1:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 2:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 3:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 4:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 5:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 6:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 7:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 8:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 9:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 10:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 11:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 12:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 13:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 14:
                return IntegratedBlocks.bookcasemeta.blockID;

            case 15:
                return IntegratedBlocks.bookcasemeta.blockID;

            default:
                return IntegratedBlocks.bookcasemeta.blockID;
        }
    }
    
    public int quantityDropped(int var1, int var2, Random var3)
    {
        switch (var1)
        {
            case 0:
                return 1;

            case 1:
                return 1;

            case 2:
                return 1;

            case 3:
                return 1;

            case 4:
                return 1;

            case 5:
                return 1;

            case 6:
                return 1;

            case 7:
                return 1;

            case 8:
                return 1;

            case 9:
                return 1;

            case 10:
                return 1;

            case 11:
                return 1;

            case 12:
                return 1;

            case 13:
                return 1;

            case 14:
                return 1;

            case 15:
                return 1;

            default:
                return 1;
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        switch (var1)
        {
            case 0:
                return 0;

            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            case 4:
                return 4;

            case 5:
                return 5;

            case 6:
                return 6;

            case 7:
                return 7;

            case 8:
                return 8;

            case 9:
                return 9;

            case 10:
                return 10;

            case 11:
                return 11;

            case 12:
                return 12;

            case 13:
                return 13;

            case 14:
                return 14;

            case 15:
                return 15;

            default:
                return 0;
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        iconBuffer = new Icon[4][12]; // 3 machines, 6 sides each, in ON and OFF states
        
        // meta 0, Empty Bookshelf
        iconBuffer[0][0] = par1IconRegister.registerIcon("reactioncraft:wood"); // bottom
        iconBuffer[0][1] = par1IconRegister.registerIcon("reactioncraft:wood"); // top
        iconBuffer[0][2] = par1IconRegister.registerIcon("reactioncraft:empty"); // north
        iconBuffer[0][3] = par1IconRegister.registerIcon("reactioncraft:empty"); // east
        iconBuffer[0][4] = par1IconRegister.registerIcon("reactioncraft:empty"); // south
        iconBuffer[0][5] = par1IconRegister.registerIcon("reactioncraft:empty"); // west
        
        // meta 1, Webbed Bookshelf with books
        iconBuffer[1][0] = par1IconRegister.registerIcon("reactioncraft:wood"); // bottom
        iconBuffer[1][1] = par1IconRegister.registerIcon("reactioncraft:wood"); // top
        iconBuffer[1][2] = par1IconRegister.registerIcon("bookshelf"); // north
        iconBuffer[1][3] = par1IconRegister.registerIcon("bookshelf"); // east
        iconBuffer[1][4] = par1IconRegister.registerIcon("bookshelf"); // south
        iconBuffer[1][5] = par1IconRegister.registerIcon("bookshelf"); // west
        
        // meta 2, Webbed Bookshelf without books
        iconBuffer[2][0] = par1IconRegister.registerIcon("reactioncraft:wood"); // bottom
        iconBuffer[2][1] = par1IconRegister.registerIcon("reactioncraft:wood"); // top
        iconBuffer[2][2] = par1IconRegister.registerIcon("reactioncraft:web"); // north
        iconBuffer[2][3] = par1IconRegister.registerIcon("reactioncraft:web"); // east
        iconBuffer[2][4] = par1IconRegister.registerIcon("reactioncraft:web"); // south
        iconBuffer[2][5] = par1IconRegister.registerIcon("reactioncraft:web"); // west
        
        // meta 3, Scroll Shelf
        iconBuffer[3][0] = par1IconRegister.registerIcon("reactioncraft:css"); // bottom
        iconBuffer[3][1] = par1IconRegister.registerIcon("reactioncraft:css"); // top
        iconBuffer[3][2] = par1IconRegister.registerIcon("reactioncraft:shelf"); // north
        iconBuffer[3][3] = par1IconRegister.registerIcon("reactioncraft:shelf"); // east
        iconBuffer[3][4] = par1IconRegister.registerIcon("reactioncraft:shelf"); // south
        iconBuffer[3][5] = par1IconRegister.registerIcon("reactioncraft:shelf"); // west
    }
    
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int blockMeta, int blockSide)
    {
    	return iconBuffer[blockMeta][blockSide];
    }
    
    @Override
    public Icon getIcon(int blockSide, int blockMeta)
    {
    	return iconBuffer[blockMeta][blockSide];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
        var3.add(new ItemStack(var1, 1, 2));
        var3.add(new ItemStack(var1, 1, 3));
    }
    
    public int getEnchantPower(World world, int x, int y, int z)
    {
        return blockID == IntegratedBlocks.bookcasemeta.blockID ? 1 : 1;
    }
}