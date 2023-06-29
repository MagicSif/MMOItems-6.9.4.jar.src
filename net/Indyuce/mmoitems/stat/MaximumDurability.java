/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.MaterialData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*    */ import net.Indyuce.mmoitems.stat.type.Upgradable;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MaximumDurability
/*    */   extends DoubleStat
/*    */   implements ItemRestriction, GemStoneStat, Upgradable
/*    */ {
/*    */   public MaximumDurability() {
/* 30 */     super("MAX_DURABILITY", Material.SHEARS, "Maximum Durability", new String[] { "The amount of uses before your", "item becomes unusable/breaks." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 35 */     whenApplied(paramItemStackBuilder, paramDoubleData);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 41 */     int i = (int)paramDoubleData.getValue();
/* 42 */     int j = paramItemStackBuilder.getMMOItem().hasData(ItemStats.CUSTOM_DURABILITY) ? (int)((DoubleData)paramItemStackBuilder.getMMOItem().getData(ItemStats.CUSTOM_DURABILITY)).getValue() : i;
/*    */     
/* 44 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), Integer.valueOf(i)) });
/*    */ 
/*    */     
/* 47 */     String str = MMOItems.plugin.getLanguage().getStatFormat("durability").replace("{max}", String.valueOf(i)).replace("{current}", String.valueOf(j));
/* 48 */     paramItemStackBuilder.getLore().insert("durability", new String[] { str });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void preprocess(@NotNull MMOItem paramMMOItem) {
/* 55 */     if (!paramMMOItem.hasData(ItemStats.MAX_DURABILITY)) {
/*    */ 
/*    */       
/* 58 */       short s = 400;
/*    */ 
/*    */       
/* 61 */       if (paramMMOItem.hasData(ItemStats.MATERIAL)) {
/*    */ 
/*    */         
/* 64 */         MaterialData materialData = (MaterialData)paramMMOItem.getData(ItemStats.MATERIAL);
/*    */ 
/*    */         
/* 67 */         Material material = materialData.getMaterial();
/* 68 */         s = material.getMaxDurability();
/*    */       } 
/*    */ 
/*    */       
/* 72 */       if (s < 8) {
/* 73 */         s = 400;
/*    */       }
/*    */       
/* 76 */       paramMMOItem.setData(ItemStats.MAX_DURABILITY, (StatData)new DoubleData(s));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 88 */     if (!paramNBTItem.hasTag("MMOITEMS_DURABILITY")) {
/* 89 */       return true;
/*    */     }
/* 91 */     if (paramNBTItem.getDouble(ItemStats.CUSTOM_DURABILITY.getNBTPath()) <= 0.0D) {
/* 92 */       if (paramBoolean) {
/* 93 */         Message.ZERO_DURABILITY.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer());
/* 94 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*    */       } 
/* 96 */       return false;
/*    */     } 
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\MaximumDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */