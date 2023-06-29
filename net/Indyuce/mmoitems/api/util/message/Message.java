/*     */ package net.Indyuce.mmoitems.api.util.message;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public enum Message
/*     */ {
/*  11 */   RECEIVED_ITEM("You received &6#item#&e#amount#."),
/*     */ 
/*     */   
/*  14 */   HANDS_TOO_CHARGED("You can't do anything, your hands are too charged.", "two-handed"),
/*  15 */   SPELL_ON_COOLDOWN("#progress# &eYou must wait #left# second#s# before casting this spell.", "ability-cooldown"),
/*  16 */   ITEM_ON_COOLDOWN("This item is on cooldown! Please wait #left# second#s#.", "item-cooldown"),
/*  17 */   NOT_ENOUGH_PERMS_COMMAND("You don't have enough permissions."),
/*     */ 
/*     */   
/*  20 */   NOT_ENOUGH_LEVELS("You don't have enough levels to use this item!", "cant-use-item"),
/*  21 */   SOULBOUND_RESTRICTION("This item is linked to another player, you can't use it!", "cant-use-item"),
/*  22 */   NOT_ENOUGH_PERMS("You don't have enough permissions to use this.", "cant-use-item"),
/*  23 */   WRONG_CLASS("You don't have the right class!", "cant-use-item"),
/*  24 */   NOT_ENOUGH_MANA("You don't have enough mana!", "not-enough-mana"),
/*  25 */   NOT_ENOUGH_STAMINA("You don't have enough stamina!", "not-enough-stamina"),
/*  26 */   NOT_ENOUGH_ATTRIBUTE("You don't have enough #attribute#!", "cant-use-item"),
/*  27 */   NOT_ENOUGH_PROFESSION("You don't have enough levels in #profession#!", "cant-use-item"),
/*  28 */   UNIDENTIFIED_ITEM("You can't use an unidentified item!", "cant-use-item"),
/*     */ 
/*     */ 
/*     */   
/*  32 */   ZERO_DURABILITY("This item is broken, you first need to repair it.", "item-break"),
/*     */ 
/*     */   
/*  35 */   CANNOT_IDENTIFY_STACKED_ITEMS("You may only identify one item at a time."),
/*  36 */   SUCCESSFULLY_IDENTIFIED("You successfully identified &6#item#&e."),
/*  37 */   SUCCESSFULLY_DECONSTRUCTED("You successfully deconstructed &6#item#&e."),
/*  38 */   GEM_STONE_APPLIED("You successfully applied &6#gem#&e onto your &6#item#&e."),
/*  39 */   GEM_STONE_BROKE("Your gem stone &6#gem#&c broke while trying to apply it onto &6#item#&c."),
/*  40 */   REPAIRED_ITEM("You successfully repaired &6#item#&e for &6#amount# &euses."),
/*  41 */   SKIN_APPLIED("You successfully applied the skin onto your &6#item#&e!"),
/*  42 */   SKIN_REMOVED("You successfully removed the skin from your &6#item#&e!"),
/*  43 */   SKIN_BROKE("Your skin broke while trying to apply it onto your &6#item#&c."),
/*  44 */   SKIN_REJECTED("A skin has already been applied onto your &6#item#&c!"),
/*  45 */   SKIN_INCOMPATIBLE("This skin is not compatible with your &6#item#&c!"),
/*  46 */   RANDOM_UNSOCKET_GEM_TOO_OLD("The gems have bonded strongly with your item. Cannot remove."),
/*  47 */   RANDOM_UNSOCKET_SUCCESS("&aYou removed &3#gem# &afrom your &6#item#&a!"),
/*     */ 
/*     */   
/*  50 */   CANT_BIND_ITEM("This item is currently linked to #player# by a Lvl #level# soulbound. You will have to break this soulbound first."),
/*  51 */   NO_SOULBOUND("This item is not bound to anyone."),
/*  52 */   CANT_BIND_STACKED("You can't bind stacked items."),
/*  53 */   UNSUCCESSFUL_SOULBOUND("Your soulbound failed."),
/*  54 */   UNSUCCESSFUL_SOULBOUND_BREAK("You couldn't break the soulbound."),
/*  55 */   LOW_SOULBOUND_LEVEL("This item soulbound is Lvl #level#. You will need a higher soulbound level on your consumable to break this soulbound."),
/*  56 */   SUCCESSFULLY_BIND_ITEM("You successfully applied a Lvl &6#level# &esoulbound to your &6#item#&e."),
/*  57 */   SUCCESSFULLY_BREAK_BIND("You successfully broke the Lvl &6#level# &eitem soulbound!"),
/*  58 */   SOULBOUND_ITEM_LORE("&4Linked to #player#//&4Lvl #level# Soulbound"),
/*     */ 
/*     */   
/*  61 */   CANT_UPGRADED_STACK("You can't upgrade stacked items."),
/*  62 */   MAX_UPGRADES_HIT("This item cannot be upgraded anymore."),
/*  63 */   UPGRADE_FAIL("Your upgrade failed and you lost your consumable."),
/*  64 */   UPGRADE_FAIL_STATION("Your upgrade failed and you lost your materials."),
/*  65 */   WRONG_UPGRADE_REFERENCE("You cannot upgrade this item with this consumable."),
/*  66 */   UPGRADE_SUCCESS("You successfully upgraded your &6#item#&e!"),
/*  67 */   NOT_HAVE_ITEM_UPGRADE("You don't have the item to upgrade!"),
/*  68 */   UPGRADE_REQUIREMENT_SAFE_CHECK("You would not meet the upgraded item requirements."),
/*  69 */   DEATH_DOWNGRADING("&cYour &6#item#&c got severely damaged that fight..."),
/*     */ 
/*     */   
/*  72 */   NOT_ENOUGH_MATERIALS("You do not have enough materials to craft this item."),
/*  73 */   CONDITIONS_NOT_MET("You cannot craft this item."),
/*  74 */   CRAFTING_CANCELED("You cancelled a crafting recipe."),
/*  75 */   CRAFTING_QUEUE_FULL("The crafting queue is currently full."),
/*  76 */   STATION_BIG_STACK("Amount Crafted: #size#"),
/*  77 */   RECIPE_PREVIEW("Recipe Preview"),
/*  78 */   UNABLE_TO_REPAIR("This item can't be repaired by this consumable!");
/*     */   
/*     */   private final String defaultMessage;
/*     */   private final String path;
/*     */   private final String actionBarConfigPath;
/*     */   @NotNull
/*     */   private String current;
/*     */   
/*     */   Message(String paramString1, String paramString2) {
/*  87 */     this.defaultMessage = paramString1;
/*  88 */     this.current = paramString1;
/*  89 */     this.path = name().toLowerCase().replace("_", "-");
/*  90 */     this.actionBarConfigPath = paramString2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefault() {
/*  98 */     return this.defaultMessage;
/*     */   }
/*     */   
/*     */   public String getUpdated() {
/* 102 */     return MythicLib.plugin.parseColors(this.current);
/*     */   }
/*     */   
/*     */   public void setCurrent(@NotNull String paramString) {
/* 106 */     this.current = Objects.<String>requireNonNull(paramString);
/*     */   }
/*     */   
/*     */   public boolean isActionBarMessage() {
/* 110 */     return (this.actionBarConfigPath != null);
/*     */   }
/*     */   
/*     */   public String getActionBarConfigPath() {
/* 114 */     return this.actionBarConfigPath;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String formatRaw(ChatColor paramChatColor, String... paramVarArgs) {
/* 119 */     return format(paramChatColor, paramVarArgs).toString();
/*     */   }
/*     */   
/*     */   public FormattedMessage format(ChatColor paramChatColor, String... paramVarArgs) {
/* 123 */     return (new FormattedMessage(this)).format(paramChatColor, paramVarArgs);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\message\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */