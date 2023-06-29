/*    */ package net.Indyuce.mmoitems.api.item.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.api.item.util.crafting.CraftingRecipeDisplay;
/*    */ import net.Indyuce.mmoitems.api.item.util.crafting.QueueItemDisplay;
/*    */ import net.Indyuce.mmoitems.api.item.util.crafting.UpgradingRecipeDisplay;
/*    */ 
/*    */ public class ConfigItems {
/*  9 */   public static final ConfigItem CONFIRM = new ConfigItem("CONFIRM", VersionMaterial.GREEN_STAINED_GLASS_PANE.toMaterial(), "&aConfirm", new String[0]);
/* 10 */   public static final ConfigItem FILL = new ConfigItem("FILL", VersionMaterial.GRAY_STAINED_GLASS_PANE.toMaterial(), "&8", new String[0]);
/* 11 */   public static final CustomSkull PREVIOUS_PAGE = new CustomSkull("PREVIOUS_PAGE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==", "&aPrevious Page", new String[0]);
/*    */ 
/*    */   
/* 14 */   public static final CustomSkull NEXT_PAGE = new CustomSkull("NEXT_PAGE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19", "&aNext Page", new String[0]);
/*    */ 
/*    */   
/* 17 */   public static final CustomSkull PREVIOUS_IN_QUEUE = new CustomSkull("PREVIOUS_IN_QUEUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==", "&aPrevious", new String[0]);
/*    */ 
/*    */   
/* 20 */   public static final CustomSkull NEXT_IN_QUEUE = new CustomSkull("NEXT_IN_QUEUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19", "&aNext", new String[0]);
/*    */ 
/*    */   
/* 23 */   public static final CustomSkull BACK = new CustomSkull("BACK", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==", "&aBack", new String[0]);
/*    */ 
/*    */   
/* 26 */   public static final CraftingRecipeDisplay CRAFTING_RECIPE_DISPLAY = new CraftingRecipeDisplay();
/* 27 */   public static final UpgradingRecipeDisplay UPGRADING_RECIPE_DISPLAY = new UpgradingRecipeDisplay();
/* 28 */   public static final QueueItemDisplay QUEUE_ITEM_DISPLAY = new QueueItemDisplay();
/*    */   
/* 30 */   public static final ConfigItem[] values = new ConfigItem[] { CONFIRM, FILL, PREVIOUS_PAGE, NEXT_PAGE, PREVIOUS_IN_QUEUE, NEXT_IN_QUEUE, BACK, (ConfigItem)CRAFTING_RECIPE_DISPLAY, (ConfigItem)UPGRADING_RECIPE_DISPLAY, (ConfigItem)QUEUE_ITEM_DISPLAY };
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\ConfigItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */