/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.ChooseStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import net.Indyuce.mmoitems.util.StatChoice;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.Registry;
/*    */ import org.bukkit.inventory.meta.ArmorMeta;
/*    */ import org.bukkit.inventory.meta.trim.ArmorTrim;
/*    */ import org.bukkit.inventory.meta.trim.TrimMaterial;
/*    */ import org.bukkit.inventory.meta.trim.TrimPattern;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class TrimPatternStat
/*    */   extends ChooseStat
/*    */   implements GemStoneStat {
/*    */   public TrimPatternStat() {
/* 25 */     super("TRIM_PATTERN", Material.LEATHER_CHESTPLATE, "Trim Pattern", new String[] { "Pattern of trimmed armor." }, new String[] { "armor" }, new Material[0]);
/*    */     
/* 27 */     if (MythicLib.plugin.getVersion().isBelowOrEqual(new int[] { 1, 19 })) {
/* 28 */       disable();
/*    */       
/*    */       return;
/*    */     } 
/* 32 */     for (TrimPattern trimPattern : Registry.TRIM_PATTERN) {
/* 33 */       addChoices(new StatChoice[] { new StatChoice(trimPattern.getKey().getKey()) });
/*    */     } 
/*    */   }
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 38 */     if (!(paramItemStackBuilder.getMeta() instanceof ArmorMeta))
/*    */       return; 
/* 40 */     TrimPattern trimPattern = (TrimPattern)Registry.TRIM_PATTERN.get(NamespacedKey.minecraft(paramStringData.toString().toLowerCase()));
/* 41 */     if (trimPattern == null)
/*    */       return; 
/* 43 */     ArmorMeta armorMeta = (ArmorMeta)paramItemStackBuilder.getMeta();
/* 44 */     ArmorTrim armorTrim = armorMeta.hasTrim() ? armorMeta.getTrim() : new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.COAST);
/* 45 */     armorMeta.setTrim(new ArmorTrim(armorTrim.getMaterial(), trimPattern));
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 50 */     if (!(paramReadMMOItem.getNBT().getItem().getItemMeta() instanceof ArmorMeta))
/* 51 */       return;  ArmorMeta armorMeta = (ArmorMeta)paramReadMMOItem.getNBT().getItem().getItemMeta();
/* 52 */     if (!armorMeta.hasTrim())
/* 53 */       return;  paramReadMMOItem.setData((ItemStat)this, (StatData)new StringData(armorMeta.getTrim().getPattern().getKey().getKey()));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\TrimPatternStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */