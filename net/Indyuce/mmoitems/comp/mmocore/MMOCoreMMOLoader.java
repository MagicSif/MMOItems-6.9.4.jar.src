/*     */ package net.Indyuce.mmoitems.comp.mmocore;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import net.Indyuce.mmocore.MMOCore;
/*     */ import net.Indyuce.mmocore.api.block.BlockType;
/*     */ import net.Indyuce.mmocore.api.load.MMOLoader;
/*     */ import net.Indyuce.mmocore.api.quest.objective.Objective;
/*     */ import net.Indyuce.mmocore.api.quest.trigger.Trigger;
/*     */ import net.Indyuce.mmocore.experience.dispenser.ExperienceDispenser;
/*     */ import net.Indyuce.mmocore.experience.source.type.ExperienceSource;
/*     */ import net.Indyuce.mmocore.loot.chest.condition.Condition;
/*     */ import net.Indyuce.mmocore.loot.droptable.dropitem.DropItem;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.GetMMOItemObjective;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.ItemTemplateDropItem;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.MMOItemTrigger;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.MMOItemsBlockType;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.MineMIBlockExperienceSource;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.RandomItemDropItem;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.load.SmeltMMOItemExperienceSource;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ 
/*     */ public class MMOCoreMMOLoader extends MMOLoader {
/*  32 */   private static final ItemStat HEALTH_REGENERATION = (ItemStat)new DoubleStat("HEALTH_REGENERATION", Material.BREAD, "Health Regeneration", new String[] { "Amount of health pts regenerated every second." });
/*  33 */   private static final ItemStat MAX_HEALTH_REGENERATION = (ItemStat)new DoubleStat("MAX_HEALTH_REGENERATION", Material.BREAD, "Max Health Regeneration", new String[] { "Percentage of max health regenerated every second." });
/*  34 */   private static final ItemStat MANA_REGENERATION = (ItemStat)new DoubleStat("MANA_REGENERATION", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Mana Regeneration", new String[] { "Amount of mana pts regenerated every second." });
/*  35 */   private static final ItemStat MAX_MANA_REGENERATION = (ItemStat)new DoubleStat("MAX_MANA_REGENERATION", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Max Mana Regeneration", new String[] { "Percentage of max mana regenerated every second." });
/*  36 */   private static final ItemStat STAMINA_REGENERATION = (ItemStat)new DoubleStat("STAMINA_REGENERATION", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Stamina Regeneration", new String[] { "Amount of stamina pts regenerated every second." });
/*  37 */   private static final ItemStat MAX_STAMINA_REGENERATION = (ItemStat)new DoubleStat("MAX_STAMINA_REGENERATION", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Max Stamina Regeneration", new String[] { "Percentage of max stamina regenerated every second." });
/*     */   
/*  39 */   private static final ItemStat MAX_STAMINA = (ItemStat)new DoubleStat("MAX_STAMINA", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Max Stamina", new String[] { "Adds stamina to your max stamina bar." });
/*     */   
/*  41 */   private static final ItemStat MAX_STELLIUM = (ItemStat)new DoubleStat("MAX_STELLIUM", VersionMaterial.ENDER_EYE.toMaterial(), "Max Stellium", new String[] { "Additional maximum stellium." });
/*     */   
/*  43 */   private static final ItemStat ADDITIONAL_EXPERIENCE = (ItemStat)new DoubleStat("ADDITIONAL_EXPERIENCE", VersionMaterial.EXPERIENCE_BOTTLE.toMaterial(), "Additional Experience", new String[] { "Additional MMOCore main class experience in %." });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOCoreMMOLoader() {
/*  50 */     MMOCore.plugin.loadManager.registerLoader(this);
/*  51 */     MMOCore.plugin.mineManager.registerBlockType(paramBlock -> {
/*     */           Optional optional = MMOItems.plugin.getCustomBlocks().getFromBlock(paramBlock.getBlockData());
/*     */           
/*     */           return optional.map(MMOItemsBlockType::new);
/*     */         });
/*  56 */     MMOItems.plugin.getStats().register(HEALTH_REGENERATION);
/*  57 */     MMOItems.plugin.getStats().register(MAX_HEALTH_REGENERATION);
/*  58 */     MMOItems.plugin.getStats().register(MANA_REGENERATION);
/*  59 */     MMOItems.plugin.getStats().register(MAX_MANA_REGENERATION);
/*  60 */     MMOItems.plugin.getStats().register(STAMINA_REGENERATION);
/*  61 */     MMOItems.plugin.getStats().register(MAX_STAMINA_REGENERATION);
/*  62 */     MMOItems.plugin.getStats().register(MAX_STAMINA);
/*  63 */     MMOItems.plugin.getStats().register(MAX_STELLIUM);
/*  64 */     MMOItems.plugin.getStats().register(ADDITIONAL_EXPERIENCE);
/*     */ 
/*     */     
/*  67 */     MMOItems.plugin.getCrafting().registerCondition("profession", net.Indyuce.mmoitems.comp.mmocore.crafting.ProfessionCondition::new, new ConditionalDisplay("&a✔ Requires #level# in #profession#", "&c✖ Requires #level# in #profession#"));
/*     */ 
/*     */ 
/*     */     
/*  71 */     MMOItems.plugin.getCrafting().registerCondition("stellium", net.Indyuce.mmoitems.comp.mmocore.crafting.StelliumCondition::new, new ConditionalDisplay("&a✔ Requires #stellium# Stellium", "&c✖ Requires #stellium# Stellium"));
/*     */ 
/*     */ 
/*     */     
/*  75 */     MMOItems.plugin.getCrafting().registerCondition("attribute", net.Indyuce.mmoitems.comp.mmocore.crafting.AttributeCondition::new, new ConditionalDisplay("&a✔ Requires #points# #attribute#", "&c✖ Requires #points# #attribute#"));
/*     */ 
/*     */ 
/*     */     
/*  79 */     MMOItems.plugin.getCrafting().registerTrigger("exp", net.Indyuce.mmoitems.comp.mmocore.crafting.ExperienceCraftingTrigger::new);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Condition loadCondition(MMOLineConfig paramMMOLineConfig) {
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Trigger loadTrigger(MMOLineConfig paramMMOLineConfig) {
/*  91 */     if (paramMMOLineConfig.getKey().equals("mmoitem")) {
/*  92 */       return (Trigger)new MMOItemTrigger(paramMMOLineConfig);
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DropItem loadDropItem(MMOLineConfig paramMMOLineConfig) {
/* 100 */     if (paramMMOLineConfig.getKey().equals("mmoitem") || paramMMOLineConfig.getKey().equals("mmoitemtemplate")) {
/* 101 */       return (DropItem)new ItemTemplateDropItem(paramMMOLineConfig);
/*     */     }
/* 103 */     if (paramMMOLineConfig.getKey().equals("miloot")) {
/* 104 */       return (DropItem)new RandomItemDropItem(paramMMOLineConfig);
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Objective loadObjective(MMOLineConfig paramMMOLineConfig, ConfigurationSection paramConfigurationSection) {
/* 112 */     if (paramMMOLineConfig.getKey().equals("getmmoitem")) {
/* 113 */       return (Objective)new GetMMOItemObjective(paramConfigurationSection, paramMMOLineConfig);
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExperienceSource<?> loadExperienceSource(MMOLineConfig paramMMOLineConfig, ExperienceDispenser paramExperienceDispenser) {
/* 121 */     if (paramMMOLineConfig.getKey().equals("minemiblock")) {
/* 122 */       return (ExperienceSource<?>)new MineMIBlockExperienceSource(paramExperienceDispenser, paramMMOLineConfig);
/*     */     }
/* 124 */     if (paramMMOLineConfig.getKey().equalsIgnoreCase("smeltmmoitem")) {
/* 125 */       return (ExperienceSource<?>)new SmeltMMOItemExperienceSource(paramExperienceDispenser, paramMMOLineConfig);
/*     */     }
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockType loadBlockType(MMOLineConfig paramMMOLineConfig) {
/* 133 */     if (paramMMOLineConfig.getKey().equalsIgnoreCase("miblock") || paramMMOLineConfig.getKey().equals("mmoitemsblock") || paramMMOLineConfig.getKey().equals("mmoitem") || paramMMOLineConfig
/* 134 */       .getKey().equals("mmoitems")) {
/* 135 */       return (BlockType)new MMOItemsBlockType(paramMMOLineConfig);
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\MMOCoreMMOLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */