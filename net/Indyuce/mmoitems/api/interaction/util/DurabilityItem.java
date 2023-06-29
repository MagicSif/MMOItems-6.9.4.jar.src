/*     */ package net.Indyuce.mmoitems.api.interaction.util;
/*     */ 
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.event.item.CustomDurabilityDamage;
/*     */ import net.Indyuce.mmoitems.api.event.item.ItemCustomRepairEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.util.LoreUpdate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DurabilityItem
/*     */ {
/*     */   private final NBTItem nbtItem;
/*     */   private final int maxDurability;
/*     */   private final int unbreakingLevel;
/*     */   private final int initialDurability;
/*     */   private final boolean barHidden;
/*     */   private final Player player;
/*     */   private int durability;
/*  49 */   private static final Random RANDOM = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DurabilityItem(@NotNull Player paramPlayer, @NotNull ItemStack paramItemStack) {
/*  59 */     this(paramPlayer, NBTItem.get(paramItemStack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DurabilityItem(@NotNull Player paramPlayer, @NotNull NBTItem paramNBTItem) {
/*  70 */     this.player = Objects.<Player>requireNonNull(paramPlayer, "Player cannot be null");
/*  71 */     this.nbtItem = Objects.<NBTItem>requireNonNull(paramNBTItem, "Item cannot be null");
/*     */     
/*  73 */     this.maxDurability = paramNBTItem.getInteger("MMOITEMS_MAX_DURABILITY");
/*  74 */     this.initialDurability = this.durability = paramNBTItem.hasTag("MMOITEMS_DURABILITY") ? paramNBTItem.getInteger("MMOITEMS_DURABILITY") : this.maxDurability;
/*  75 */     this.barHidden = paramNBTItem.getBoolean("MMOITEMS_DURABILITY_BAR");
/*     */     
/*  77 */     this
/*  78 */       .unbreakingLevel = (paramNBTItem.getItem().getItemMeta() != null && paramNBTItem.getItem().getItemMeta().hasEnchant(Enchantment.DURABILITY)) ? paramNBTItem.getItem().getItemMeta().getEnchantLevel(Enchantment.DURABILITY) : 0;
/*     */   }
/*     */   
/*     */   public Player getPlayer() {
/*  82 */     return this.player;
/*     */   }
/*     */   
/*     */   public int getMaxDurability() {
/*  86 */     return this.maxDurability;
/*     */   }
/*     */   
/*     */   public int getDurability() {
/*  90 */     return this.durability;
/*     */   }
/*     */   
/*     */   public boolean isBarHidden() {
/*  94 */     return this.barHidden;
/*     */   }
/*     */   
/*     */   public int getUnbreakingLevel() {
/*  98 */     return this.unbreakingLevel;
/*     */   }
/*     */   
/*     */   public NBTItem getNBTItem() {
/* 102 */     return this.nbtItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBroken() {
/* 110 */     return (this.maxDurability > 0 && this.durability <= 0);
/*     */   }
/*     */   
/*     */   public boolean isLostWhenBroken() {
/* 114 */     return this.nbtItem.getBoolean("MMOITEMS_WILL_BREAK");
/*     */   }
/*     */   
/*     */   public boolean isDowngradedWhenBroken() {
/* 118 */     return this.nbtItem.getBoolean("MMOITEMS_BREAK_DOWNGRADE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 126 */     return (this.maxDurability > 0 && this.player.getGameMode() != GameMode.CREATIVE);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public DurabilityItem addDurability(int paramInt) {
/* 131 */     Validate.isTrue((paramInt > 0), "Durability gain must be greater than 0");
/*     */     
/* 133 */     ItemCustomRepairEvent itemCustomRepairEvent = new ItemCustomRepairEvent(this, paramInt);
/* 134 */     Bukkit.getPluginManager().callEvent((Event)itemCustomRepairEvent);
/* 135 */     if (itemCustomRepairEvent.isCancelled()) {
/* 136 */       return this;
/*     */     }
/* 138 */     this.durability = Math.min(this.durability + paramInt, this.maxDurability);
/* 139 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DurabilityItem decreaseDurability(int paramInt) {
/* 145 */     if (paramInt == 0) {
/* 146 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     if (this.nbtItem.getItem().getType().getMaxDurability() == 0) {
/* 158 */       int i = getUnbreakingLevel();
/* 159 */       if (i > 0 && RANDOM.nextInt(i + 1) != 0) {
/* 160 */         return this;
/*     */       }
/*     */     } 
/* 163 */     CustomDurabilityDamage customDurabilityDamage = new CustomDurabilityDamage(this, paramInt);
/* 164 */     Bukkit.getPluginManager().callEvent((Event)customDurabilityDamage);
/* 165 */     if (customDurabilityDamage.isCancelled()) {
/* 166 */       return this;
/*     */     }
/* 168 */     this.durability = Math.max(0, Math.min(this.durability - paramInt, this.maxDurability));
/*     */ 
/*     */     
/* 171 */     if (isBroken()) {
/* 172 */       this.player.getWorld().playSound(this.player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/* 173 */       PlayerData.get((OfflinePlayer)this.player).getInventory().scheduleUpdate();
/*     */     } 
/*     */     
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack toItem() {
/* 192 */     if (isBroken()) {
/*     */ 
/*     */       
/* 195 */       if (isLostWhenBroken()) {
/* 196 */         return null;
/*     */       }
/*     */       
/* 199 */       if (isDowngradedWhenBroken()) {
/* 200 */         ItemTag itemTag = ItemTag.getTagAtPath(ItemStats.UPGRADE.getNBTPath(), getNBTItem(), SupportedNBTTagValues.STRING);
/* 201 */         if (itemTag != null) {
/*     */           try {
/* 203 */             UpgradeData upgradeData = new UpgradeData((new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject());
/*     */ 
/*     */             
/* 206 */             if (upgradeData.getLevel() <= upgradeData.getMin()) {
/* 207 */               return null;
/*     */             }
/*     */             
/* 210 */             LiveMMOItem liveMMOItem = new LiveMMOItem(getNBTItem());
/* 211 */             liveMMOItem.setData(ItemStats.CUSTOM_DURABILITY, (StatData)new DoubleData(this.maxDurability));
/* 212 */             liveMMOItem.getUpgradeTemplate().upgradeTo((MMOItem)liveMMOItem, upgradeData.getLevel() - 1);
/* 213 */             return liveMMOItem.newBuilder().buildNBT().toItem();
/*     */           }
/* 215 */           catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 222 */     if (this.durability == this.initialDurability) {
/* 223 */       return this.nbtItem.getItem();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (!this.barHidden) {
/* 236 */       boolean bool = (this.durability == this.maxDurability) ? false : Math.max(1, (int)((1.0D - this.durability / this.maxDurability) * this.nbtItem.getItem().getType().getMaxDurability()));
/* 237 */       this.nbtItem.addTag(new ItemTag[] { new ItemTag("Damage", Integer.valueOf(bool)) });
/*     */     } 
/*     */     
/* 240 */     this.nbtItem.addTag(new ItemTag[] { new ItemTag("MMOITEMS_DURABILITY", Integer.valueOf(this.durability)) });
/*     */ 
/*     */     
/* 243 */     ItemStack itemStack = this.nbtItem.toItem();
/*     */ 
/*     */     
/* 246 */     String str1 = MythicLib.inst().parseColors(MMOItems.plugin.getLanguage().getStatFormat("durability").replace("{max}", String.valueOf(this.maxDurability)));
/* 247 */     String str2 = str1.replace("{current}", String.valueOf(this.initialDurability));
/* 248 */     String str3 = str1.replace("{current}", String.valueOf(this.durability));
/* 249 */     return (new LoreUpdate(itemStack, str2, str3)).updateLore();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interactio\\util\DurabilityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */