/*     */ package net.Indyuce.mmoitems.api.item.util.identify;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.util.AdventureUtils;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.io.BukkitObjectOutputStream;
/*     */ import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
/*     */ 
/*     */ public class UnidentifiedItem extends ConfigItem {
/*     */   public UnidentifiedItem(Type paramType) {
/*  26 */     super("unidentified", paramType.getItem().getType());
/*     */     
/*  28 */     setName("#prefix#Unidentified " + paramType.getName());
/*  29 */     setLore(Arrays.asList(new String[] { "&7This item is unidentified. I must", "&7find a way to identify it!", "{tier}", "{tier}&8Item Info:", "{range}&8- &7Lvl Range: &e#range#", "{tier}&8- &7Item Tier: #prefix##tier#" }));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemBuilder newBuilder(NBTItem paramNBTItem) {
/*  34 */     return new ItemBuilder(paramNBTItem);
/*     */   }
/*     */ 
/*     */   
/*     */   public class ItemBuilder
/*     */   {
/*     */     private final int amount;
/*     */     
/*     */     private final NBTItem item;
/*     */     
/*  44 */     private String name = UnidentifiedItem.this.getName();
/*  45 */     private final List<String> lore = new ArrayList<>(UnidentifiedItem.this.getLore());
/*     */     
/*     */     public ItemBuilder(NBTItem param1NBTItem) {
/*  48 */       this.amount = param1NBTItem.getItem().getAmount();
/*  49 */       this.item = param1NBTItem;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack build() {
/*  57 */       VolatileMMOItem volatileMMOItem = new VolatileMMOItem(this.item);
/*  58 */       ItemTier itemTier = ItemTier.ofItem(this.item);
/*  59 */       byte b1 = volatileMMOItem.hasData(ItemStats.REQUIRED_LEVEL) ? (int)((DoubleData)volatileMMOItem.getData(ItemStats.REQUIRED_LEVEL)).getValue() : -1;
/*     */ 
/*     */       
/*  62 */       HashMap<Object, Object> hashMap = new HashMap<>();
/*  63 */       if (itemTier != null) {
/*  64 */         hashMap.put("prefix", itemTier.getUnidentificationInfo().getPrefix());
/*  65 */         hashMap.put("tier", itemTier.getUnidentificationInfo().getDisplayName());
/*     */         
/*  67 */         if (b1 > -1) {
/*  68 */           int[] arrayOfInt = itemTier.getUnidentificationInfo().calculateRange(b1);
/*  69 */           hashMap.put("range", arrayOfInt[0] + "-" + arrayOfInt[1]);
/*     */         } 
/*     */       } else {
/*  72 */         this.name = this.name.replace("#prefix#", "");
/*     */       } 
/*     */       
/*  75 */       this.lore.removeIf(param1String -> ((param1String.startsWith("{tier}") && param1ItemTier == null) || (param1String.startsWith("{range}") && (param1ItemTier == null || param1Int < 0))));
/*     */ 
/*     */       
/*  78 */       for (String str : hashMap.keySet())
/*  79 */         this.name = this.name.replace("#" + str + "#", (CharSequence)hashMap.get(str)); 
/*  80 */       for (byte b2 = 0; b2 < this.lore.size(); b2++) {
/*  81 */         String str = this.lore.get(b2);
/*  82 */         for (String str1 : hashMap.keySet())
/*  83 */           str = str.replace("#" + str1 + "#", (CharSequence)hashMap.get(str1)); 
/*  84 */         this.lore.set(b2, MythicLib.plugin.parseColors(str.replace("{range}", "").replace("{tier}", "")));
/*     */       } 
/*     */ 
/*     */       
/*  88 */       this.item.getItem().setAmount(1);
/*     */       
/*  90 */       ItemStack itemStack = MythicLib.plugin.getVersion().getWrapper().copyTexture(this.item).addTag(new ItemTag[] { new ItemTag("MMOITEMS_UNIDENTIFIED_ITEM", serialize(this.item.toItem())) }).toItem();
/*  91 */       itemStack.setAmount(this.amount);
/*  92 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*  93 */       itemMeta.addItemFlags(ItemFlag.values());
/*  94 */       itemMeta.setUnbreakable(true);
/*  95 */       AdventureUtils.setDisplayName(itemMeta, this.name);
/*  96 */       AdventureUtils.setLore(itemMeta, this.lore);
/*  97 */       if (UnidentifiedItem.this.customModelData != null) {
/*  98 */         itemMeta.setCustomModelData(UnidentifiedItem.this.customModelData);
/*     */       }
/* 100 */       itemStack.setItemMeta(itemMeta);
/*     */ 
/*     */       
/* 103 */       if (UnidentifiedItem.this.material != null && UnidentifiedItem.this.material.isItem()) {
/* 104 */         itemStack.setType(UnidentifiedItem.this.material);
/*     */       }
/*     */       
/* 107 */       return itemStack;
/*     */     }
/*     */     
/*     */     private String serialize(ItemStack param1ItemStack) {
/*     */       try {
/* 112 */         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 113 */         BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
/* 114 */         bukkitObjectOutputStream.writeObject(param1ItemStack);
/* 115 */         bukkitObjectOutputStream.close();
/* 116 */         return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
/* 117 */       } catch (Exception exception) {
/* 118 */         exception.printStackTrace();
/* 119 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\identify\UnidentifiedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */