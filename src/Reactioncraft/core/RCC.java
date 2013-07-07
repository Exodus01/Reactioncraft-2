package Reactioncraft.core;

import Reactioncraft.core.client.ClientProxy;
import Reactioncraft.core.common.CommonProxy;
import Reactioncraft.core.common.CraftingHanlder;
import Reactioncraft.core.common.ItemHammer;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.ShapedOreRecipe;
import Reactioncraft.Integration.Integration;
import Reactioncraft.basefiles.common.*;
import Reactioncraft.basemod.RCB;
import Reactioncraft.core.common.*;
import Reactioncraft.vanillaclasses.EntityReactionFishHook;
import Reactioncraft.vanillaclasses.ItemReactionRod;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod( modid = "RCC", name="Reactioncraft Core", version="[1.5.2] Reactioncraft Version 9.0", dependencies = "required-after:RCB")
@NetworkMod(channels = { "RCC" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)

public class RCC
{
	@SidedProxy(clientSide = "Reactioncraft.core.client.ClientProxy", serverSide = "Reactioncraft.core.common.CommonProxy")

	public static CommonProxy proxy;
	
	@Instance("RCC")

	public static RCC instance;

	//Config Code
	public static int DarkSandID;
	public static int snowblockBlockID;
	public static int chainladderID;
	public static int ChainLoopIID;
	public static int HammerIID;
	public static int spongeAbsorbtion;
	public static int BlackdiamondboreIID;
	public static int BloodstoneboreIID;
	public static int SandStonePasteIID;
	public static int SamonRawIID;
	public static int SamonIID;
	public static int YellowTailRawIID;
	public static int YellowTailCookedIID;
	public static int MapinabottleIID;
    public static int ShipinabottleIID;

	
	//Biomes
	public static int RcDesertBID;
	
	
	//Biomes
	public static BiomeGenBase RcDesert;
		
	//block code
	public static Block DarkSand;
	public static Block newSponge; int newSpongeId;
	public static Block clearBlock; int clearBlockId;

	public static Block snowblock;
	public static Block chainladder;


	//Item code
	public static Item ChainLoop;
	public static Item Hammer;
	public static Item SandStonePaste;

	//New Fish
	public static Item SamonRaw;
	public static Item Samon;
	public static Item YellowTailRaw;
	public static Item YellowTailCooked;
	
	//Bottled Items
	public static Item Mapinabottle;
    public static Item Shipinabottle;

	
	//Railcraft Items
	public static Item Blackdiamondbore;
	public static Item Bloodstonebore;
	
	public static Property GenSponge;
	public static Property GenDarkSand;
	public static Property vanillaOverrideCake;
	public static Property vanillaOverrideBed;
	public static Property vanillaOverrideFishingrod;
	public static Property hardcorerailcraft;
	
	public static final int WILDCARD_VALUE = Short.MAX_VALUE;

	public static boolean Railcraft() throws ClassNotFoundException 
	{
		try{
			Class.forName("mods.railcraft.common.core.Railcraft");
		}
		catch (NoClassDefFoundError ex) 
		{
			return false ;
		}
		return true ;
	}
	

	//Config
	public static ReactioncraftConfiguration config;

	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		System.out.println("[RCC] Pre Initialization Loaded");
		
		config = new ReactioncraftConfiguration(new File(evt.getModConfigurationDirectory(), "Reactioncraft/Core.cfg"));

		try 
		{
			config.load();

			
			//Terrain Blocks
			DarkSandID = config.getTerrainBlock("Terrain Block", "Dark Sand", 250, "required for desert biome").getInt();
			
			//Block 3020-3026
			newSpongeId = config.getBlock("Dry Sponge", 3020).getInt();
			spongeAbsorbtion = config.get("Settings", "Sponge absortion (blocks)", 4).getInt();
			clearBlockId = config.getBlock("Clear Block", 3021).getInt();
			snowblockBlockID = config.getBlock("Snow Block", 3022).getInt();
			chainladderID = config.getBlock("chain ladder", 3023).getInt();

			//Items
			//Reserved 10031 - 10090 
			ChainLoopIID = config.getItem("Chain Loop", 10031).getInt();
			HammerIID = config.getItem("Hammer", 10032).getInt();
			BlackdiamondboreIID = config.getItem("Black Diamond Bore", 10033).getInt();
			BloodstoneboreIID = config.getItem("Bloodstone Bore", 10034).getInt();
			SandStonePasteIID = config.getItem("Dark Sandstone Paste", 10035).getInt();
			
			
			SamonRawIID = config.getItem("Raw Samon", 10036).getInt();
			SamonIID = config.getItem("Cooked Samon", 10037).getInt();
			YellowTailRawIID = config.getItem("Raw Yellowtail", 10038).getInt();
			YellowTailCookedIID = config.getItem("Cooked Yellowtail", 10039).getInt();
			MapinabottleIID = config.getItem("Map in a bottle", 10041).getInt();
		    ShipinabottleIID = config.getItem("Ship in a bottle", 10040).getInt();
	
			
			//Property Stuff Below
		    hardcorerailcraft = config.get("Hardcore", "Hardcore Rails", false);
		    
			GenSponge = config.get("Generation", "Gen Sponge", true);
			GenSponge.comment = "If this is true sponges generate in the ocean";
			
			GenDarkSand = config.get("Generation", "Gen Dark Sand", true);
			GenDarkSand.comment = "If this is true Darksand will generate in the ocean only if Better Deserts is installed";
			
			vanillaOverrideCake = config.get("Vanilla Overrides", "Override Cake", true);
			vanillaOverrideCake.comment = "If this is true item Cake is overwrote and stacks to 4";
			
			vanillaOverrideBed = config.get("Vanilla Overrides", "Override Bed", true);
			vanillaOverrideBed.comment = "If this is true item Bed is overwrote to stack to 3";
			
			vanillaOverrideFishingrod = config.get("Vanilla Overrides", "Override Fishing Rod", false);
			vanillaOverrideFishingrod.comment = "If this is true item fishing rod is overwrote to catch mutiple fish and items, to play regular vanilla servers set to false and restart your game!";
			
			if(RCC.vanillaOverrideCake.getBoolean(true))
			{
				int cakeId = Item.cake.itemID;
				Item.itemsList[cakeId] = null;
				Item.cake = new ItemReed(cakeId - 256, Block.cake).setUnlocalizedName("cake").setMaxStackSize(4).setCreativeTab(CreativeTabs.tabFood);
			}
			
			if(RCC.vanillaOverrideBed.getBoolean(true))
			{
				int cakeId = Item.bed.itemID;
				Item.itemsList[cakeId] = null;
				Item.bed = new ItemBed(cakeId - 256).setUnlocalizedName("bed").setMaxStackSize(3).setCreativeTab(CreativeTabs.tabDecorations);
			}
			
			if(RCC.vanillaOverrideFishingrod.getBoolean(true))
			{
				int cakeId = Item.fishingRod.itemID;
				Item.itemsList[cakeId] = null;
				Item.fishingRod = (ItemFishingRod)new ItemReactionRod(cakeId - 256).setUnlocalizedName("fishingRod");
			}
		}

		finally 
		{
			if (config.hasChanged()) 
			{
				config.save();
			}
		}
	}

	@Init 
	public void load(FMLInitializationEvent event)
	{	
		EntityRegistry.registerModEntity(EntityReactionFishHook.class, "ReactioncraftFishHook", 0, this, 64, 1, false);
		
		blockCode();
		blockRegistry();
		ItemCode();
		addNames();
		worldGenHandler();
		recipes();

		integrations();
		
		OreDictionary();
		
		initBiomes();
		
		addBiomes(); 
	}
	
	public void integrations() 
	{
				//railcraft integration
				try
				{
					if(Railcraft())
					{
						Integration.loadBoreheads();
						System.out.println("Railcraft integration loaded !");
					}
				}
				catch (ClassNotFoundException e)
				{
					System.out.println("Reactioncraft did not find Railcraft, Borehead Recipes Disabled!");
					System.out.println("Reactioncraft did not find Railcraft, Readded Rail Recipe Disabled!");
				}
		
	}

	public void HarvestLevel() 
	{
		MinecraftForge.setBlockHarvestLevel(RCC.DarkSand, "shovel", 0);
	}
	

	public void initBiomes() 
	{
		RcDesert = (new BiomeGenRcDesert(30)).setBiomeName("Darksand Desert").setDisableRain().setMinMaxHeight(0.1F, 0.2F).setColor(230609);
	}
	

	public void blockRegistry() 
	{
		GameRegistry.registerBlock(snowblock, "snowblock");
		GameRegistry.registerBlock(chainladder, "chainladder");
		GameRegistry.registerBlock(newSponge, "newSponge");
		GameRegistry.registerBlock(clearBlock, "clearBlock");
		GameRegistry.registerBlock(DarkSand, "DarkSand");
	}

	public void recipes() 
	{
		GameRegistry.addSmelting(Block.sponge.blockID, new ItemStack(RCC.newSponge), 0.1F); 
		
		GameRegistry.addSmelting(RCC.DarkSand.blockID, new ItemStack(Block.glass.blockID, 1, 0), 0.5F);
		
		//
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(RCC.ChainLoop,3,8), true, new Object[]{"X","Y", Character.valueOf('Y'), "ingotSuperheatediron", Character.valueOf('X'), new ItemStack(RCC.Hammer,1,WILDCARD_VALUE)}));
		GameRegistry.addRecipe(new ItemStack(Hammer, 1), new Object[]{"XXX", "XIX", " I ", Character.valueOf('I'), Item.stick, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(RCC.chainladder, 2), new Object[] {"Y", "Y", "Y", 'Y', RCC.ChainLoop});
		GameRegistry.addShapelessRecipe(new ItemStack(RCC.ChainLoop, 2), new Object[]{RCC.chainladder,});
		GameRegistry.addRecipe(new ItemStack(snowblock, 1), new Object[]{ "DD", "DD", Character.valueOf('D'), Block.ice});
		
		//DarkSand Clay
		GameRegistry.addShapelessRecipe(new ItemStack(RCC.SandStonePaste, 2), RCC.DarkSand, Item.bucketWater);
		
		//Bore Heads
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Bloodstonebore, true, new Object[]{
		         "XXX", "X#X", "XXX", Character.valueOf('X'), "ingotSteel", Character.valueOf('#'), "oreBloodstone"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Blackdiamondbore, true, new Object[]{
		         "XXX", "X#X", "XXX", Character.valueOf('X'), "ingotSteel", Character.valueOf('#'), "diamondBlack"}));
	}
	
	public void OreDictionary() 
	{
		OreDictionary.registerOre("darkclay", new ItemStack(RCC.SandStonePaste));
		OreDictionary.registerOre("darkclay", new ItemStack(RCC.SandStonePaste, 1,2));
	}
	
	public void blockCode() 
	{
		//Andr3wrulz Code
		newSponge = new NewSponge(newSpongeId, spongeAbsorbtion).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("RCC:sponge");
		clearBlock = new ClearBlock(clearBlockId);
		
		//
		DarkSand = new BlockSandRc(DarkSandID).setHardness(0.5F).setResistance(5.0F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("RCBDM:Sand");
		chainladder = new BlockChainLadder(chainladderID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("RCC:chains");
		snowblock = new BlockBasic(snowblockBlockID).setHardness(3.0F).setResistance(5.0F).setUnlocalizedName("RCC:SnowBrick");
	}

	public void worldGenHandler() 
	{
		GameRegistry.registerWorldGenerator(new WorldGenHandler());
		GameRegistry.registerCraftingHandler(new CraftingHanlder());
	}

	public void ItemCode() 
	{
		ChainLoop = (new ItemBasic(ChainLoopIID)).setUnlocalizedName("RCC:ChainLoop");
		Hammer = (new ItemHammer(HammerIID)).setMaxStackSize(1).setUnlocalizedName("RCC:Hammer");
		Blackdiamondbore = new IBlackDiamondBH(BlackdiamondboreIID).setUnlocalizedName("RCC:Blackdiamondbore").setCreativeTab(RCB.ReactioncraftItems);
		Bloodstonebore = new IBloodstoneBH(BloodstoneboreIID).setUnlocalizedName("RCC:Bloodstonebore").setCreativeTab(RCB.ReactioncraftItems);		
		SandStonePaste = (new ItemBasic(SandStonePasteIID)).setUnlocalizedName("RCBDM:SandStonePaste");
		SamonRaw = (new ItemBasicFood(SamonRawIID, 2, 0.3F, false)).setUnlocalizedName("RCC:samonr");
		Samon = (new ItemBasicFood(SamonIID, 5, 0.6F, false)).setUnlocalizedName("RCC:samonc");
		YellowTailRaw = (new ItemBasicFood(YellowTailRawIID, 2, 0.3F, false)).setUnlocalizedName("RCC:yellowtailr");
		YellowTailCooked = (new ItemBasicFood(YellowTailCookedIID, 5, 0.6F, false)).setUnlocalizedName("RCC:yellowtailc");
		Mapinabottle = new ItemBasic(MapinabottleIID).setUnlocalizedName("RCC:mapinbottle");
		Shipinabottle = new ItemBasic(ShipinabottleIID).setUnlocalizedName("RCC:shipinbottle").setCreativeTab(RCB.ReactioncraftItems);
	}

	public void addNames() 
	{
		LanguageRegistry.addName(newSponge, "Dry Sponge");
		LanguageRegistry.addName(clearBlock, "Clear Block");
		LanguageRegistry.addName(snowblock, "Snow Block");
		LanguageRegistry.addName(chainladder, "chain ladder");
		LanguageRegistry.addName(ChainLoop, "Chain Loop");
		LanguageRegistry.addName(Hammer, "Hammer");
		LanguageRegistry.addName(Blackdiamondbore, "Black Diamond Bore");
		LanguageRegistry.addName(Bloodstonebore, "Bloodstone Bore");
		
		//Dark Sand
		LanguageRegistry.addName(DarkSand, "Dark Sand");
		
		//Sandstone Paste
		LanguageRegistry.addName(SandStonePaste, "Dark Sand Clay");
		
		//New Fish
		LanguageRegistry.addName(SamonRaw, "Raw Samon");
		LanguageRegistry.addName(Samon, "Cooked Samon");
		
		LanguageRegistry.addName(YellowTailRaw, "Raw Yellow Tail");
		LanguageRegistry.addName(YellowTailCooked, "Cooked Yellowtail");
		LanguageRegistry.addName(RCC.Mapinabottle, "Map in a bottle");
		LanguageRegistry.addName(RCC.Shipinabottle, "Ship in a bottle");
	}
	
	public void addBiomes() 
	{
		GameRegistry.addBiome(RcDesert);
	}


	@PostInit
	public void modsLoaded(FMLPostInitializationEvent evt)
	{
		FMLLog.info("Looks like Reactioncraft Has Fully Loaded!");
	}	 
}

