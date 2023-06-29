/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*    */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RequiredBiomes
/*    */   extends StringListStat
/*    */   implements ItemRestriction, GemStoneStat
/*    */ {
/*    */   public RequiredBiomes() {
/* 22 */     super("REQUIRED_BIOMES", Material.JUNGLE_SAPLING, "Required Biomes", new String[] { "The biome the player must be within", "for this item to activate." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 29 */     if (!paramNBTItem.hasTag(getNBTPath())) return true;
/*    */ 
/*    */     
/* 32 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 33 */     if (paramNBTItem.hasTag(getNBTPath())) arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramNBTItem, SupportedNBTTagValues.STRING));
/*    */ 
/*    */     
/* 36 */     StringListData stringListData = getLoadedNBT(arrayList);
/*    */     
/* 38 */     boolean bool = false;
/* 39 */     if (stringListData != null) {
/*    */ 
/*    */       
/* 42 */       for (String str1 : stringListData.getList()) {
/*    */ 
/*    */         
/* 45 */         String str2 = str1.toLowerCase().replace(" ", "_").replace("-", "_");
/* 46 */         if (str2.startsWith("!")) { bool = true; str2 = str2.substring(1); }
/*    */ 
/*    */         
/* 49 */         String str3 = paramRPGPlayer.getPlayer().getLocation().getBlock().getBiome().getKey().getKey();
/*    */ 
/*    */         
/* 52 */         if (str3.contains(str2)) return !bool;
/*    */       
/*    */       } 
/*    */       
/* 56 */       return bool;
/*    */     } 
/*    */     
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isDynamic() {
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RequiredBiomes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */