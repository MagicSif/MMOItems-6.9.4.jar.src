/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class Unbreakable extends BooleanStat {
/*    */   public Unbreakable() {
/* 19 */     super("UNBREAKABLE", Material.ANVIL, "Unbreakable", new String[] { "Infinite durability if set to true." }, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 24 */     if (paramBooleanData.isEnabled()) {
/*    */ 
/*    */       
/* 27 */       paramItemStackBuilder.addItemTag(getAppliedNBT(paramBooleanData));
/* 28 */       paramItemStackBuilder.getMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull BooleanData paramBooleanData) {
/* 35 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 36 */     if (paramBooleanData.isEnabled()) arrayList.add(new ItemTag(getNBTPath(), Boolean.valueOf(true))); 
/* 37 */     return arrayList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 42 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 43 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath()))
/* 44 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.BOOLEAN)); 
/* 45 */     BooleanData booleanData = getLoadedNBT(arrayList);
/* 46 */     if (booleanData != null) paramReadMMOItem.setData((ItemStat)this, (StatData)booleanData);
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BooleanData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 53 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*    */     
/* 55 */     if (itemTag != null)
/*    */     {
/*    */       
/* 58 */       return new BooleanData(((Boolean)itemTag.getValue()).booleanValue());
/*    */     }
/*    */     
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getNBTPath() {
/* 66 */     return "Unbreakable";
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Unbreakable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */