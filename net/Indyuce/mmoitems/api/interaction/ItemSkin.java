/*     */ package net.Indyuce.mmoitems.api.interaction;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.SkullTextureData;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ItemSkin extends UseItem {
/*     */   public static final String SKIN_ID_TAG = "MMOITEMS_SKIN_ID";
/*     */   
/*     */   public ItemSkin(Player paramPlayer, NBTItem paramNBTItem) {
/*  29 */     super(paramPlayer, paramNBTItem);
/*     */   }
/*     */   
/*     */   public ApplyResult applyOntoItem(NBTItem paramNBTItem, Type paramType) {
/*  33 */     if (paramType == Type.SKIN) {
/*  34 */       return new ApplyResult(ResultType.NONE);
/*     */     }
/*  36 */     if (MMOItems.plugin.getConfig().getBoolean("locked-skins") && MMOUtils.isNonEmpty(paramNBTItem.getString("MMOITEMS_SKIN_ID"))) {
/*  37 */       this.player.playSound(this.player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/*  38 */       Message.SKIN_REJECTED.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()) }).send(this.player);
/*  39 */       return new ApplyResult(ResultType.NONE);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (getMMOItem().hasData(ItemStats.COMPATIBLE_TYPES)) {
/*     */       
/*  47 */       List list = ((StringListData)getMMOItem().getData(ItemStats.COMPATIBLE_TYPES)).getList();
/*  48 */       if (list.size() > 0 && list.stream().noneMatch(paramString -> paramString.equalsIgnoreCase(paramType.getId()))) {
/*  49 */         this.player.playSound(this.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/*  50 */         Message.SKIN_INCOMPATIBLE.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem())
/*  51 */             }).send(this.player);
/*  52 */         return new ApplyResult(ResultType.NONE);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (getMMOItem().hasData(ItemStats.COMPATIBLE_IDS)) {
/*     */       
/*  59 */       List list = ((StringListData)getMMOItem().getData(ItemStats.COMPATIBLE_IDS)).getList();
/*  60 */       String str = paramNBTItem.getString("MMOITEMS_ITEM_ID");
/*     */       
/*  62 */       if (list.size() > 0 && list.stream()
/*  63 */         .noneMatch(paramString2 -> paramString2.equalsIgnoreCase(paramString1))) {
/*  64 */         this.player.playSound(this.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/*  65 */         Message.SKIN_INCOMPATIBLE.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem())
/*  66 */             }).send(this.player);
/*  67 */         return new ApplyResult(ResultType.NONE);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  72 */     if (getMMOItem().hasData(ItemStats.COMPATIBLE_MATERIALS)) {
/*     */       
/*  74 */       List list = ((StringListData)getMMOItem().getData(ItemStats.COMPATIBLE_MATERIALS)).getList();
/*     */       
/*  76 */       if (list.size() > 0 && list.stream()
/*  77 */         .noneMatch(paramString -> paramString.equalsIgnoreCase(paramNBTItem.getItem().getType().name()))) {
/*  78 */         this.player.playSound(this.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/*  79 */         Message.SKIN_INCOMPATIBLE.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem())
/*  80 */             }).send(this.player);
/*  81 */         return new ApplyResult(ResultType.NONE);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  86 */     double d = getNBTItem().getStat(ItemStats.SUCCESS_RATE.getId());
/*  87 */     if (d != 0.0D && 
/*  88 */       RANDOM.nextDouble() < 1.0D - d / 100.0D) {
/*  89 */       this.player.playSound(this.player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/*  90 */       Message.SKIN_BROKE.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem())
/*  91 */           }).send(this.player);
/*  92 */       return new ApplyResult(ResultType.FAILURE);
/*     */     } 
/*     */ 
/*     */     
/*  96 */     ItemStack itemStack = applySkin(paramNBTItem, getMMOItem());
/*     */     
/*  98 */     this.player.playSound(this.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/*  99 */     Message.SKIN_APPLIED.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()) }).send(this.player);
/*     */     
/* 101 */     return new ApplyResult(itemStack);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static ItemStack applySkin(@NotNull NBTItem paramNBTItem, @NotNull VolatileMMOItem paramVolatileMMOItem) {
/* 126 */     NBTItem nBTItem = paramVolatileMMOItem.getNBT();
/*     */ 
/*     */     
/* 129 */     String str = paramVolatileMMOItem.getNBT().getString("MMOITEMS_SKIN_ID");
/* 130 */     str = MMOUtils.isNonEmpty(str) ? str : nBTItem.getString("MMOITEMS_ITEM_ID");
/* 131 */     paramNBTItem.addTag(new ItemTag[] { new ItemTag("MMOITEMS_SKIN_ID", str) });
/*     */ 
/*     */     
/* 134 */     if (nBTItem.getInteger("CustomModelData") != 0) {
/* 135 */       paramNBTItem.addTag(new ItemTag[] { new ItemTag("CustomModelData", Integer.valueOf(nBTItem.getInteger("CustomModelData"))) });
/*     */     }
/*     */     
/* 138 */     if (!nBTItem.getString("MMOITEMS_ITEM_PARTICLES").isEmpty()) {
/* 139 */       paramNBTItem.addTag(new ItemTag[] { new ItemTag("MMOITEMS_ITEM_PARTICLES", nBTItem.getString("MMOITEMS_ITEM_PARTICLES")) });
/*     */     }
/* 141 */     ItemStack itemStack = paramNBTItem.toItem();
/* 142 */     if (itemStack.getType() != nBTItem.getItem().getType()) {
/* 143 */       itemStack.setType(nBTItem.getItem().getType());
/*     */     }
/* 145 */     ItemMeta itemMeta1 = itemStack.getItemMeta();
/* 146 */     ItemMeta itemMeta2 = nBTItem.getItem().getItemMeta();
/* 147 */     if (itemMeta2 != null && itemMeta1 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (itemMeta2.isUnbreakable()) {
/* 153 */         itemMeta1.setUnbreakable(true);
/* 154 */         if (itemMeta1 instanceof Damageable && itemMeta2 instanceof Damageable) {
/* 155 */           ((Damageable)itemMeta1).setDamage(((Damageable)itemMeta2).getDamage());
/*     */         }
/*     */       } 
/*     */       
/* 159 */       if (itemMeta2 instanceof LeatherArmorMeta && itemMeta1 instanceof LeatherArmorMeta) {
/* 160 */         ((LeatherArmorMeta)itemMeta1).setColor(((LeatherArmorMeta)itemMeta2).getColor());
/*     */       }
/*     */       
/* 163 */       if (paramVolatileMMOItem.hasData(ItemStats.SKULL_TEXTURE) && itemStack
/* 164 */         .getType() == VersionMaterial.PLAYER_HEAD.toMaterial() && nBTItem
/* 165 */         .getItem().getType() == VersionMaterial.PLAYER_HEAD.toMaterial()) {
/*     */         try {
/* 167 */           Field field = itemMeta1.getClass().getDeclaredField("profile");
/* 168 */           field.setAccessible(true);
/* 169 */           field.set(itemMeta1, ((SkullTextureData)paramVolatileMMOItem
/* 170 */               .getData(ItemStats.SKULL_TEXTURE)).getGameProfile());
/* 171 */         } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {
/* 172 */           MMOItems.plugin.getLogger().warning("Could not read skull texture");
/*     */         } 
/*     */       }
/* 175 */       itemStack.setItemMeta(itemMeta1);
/*     */     } 
/*     */     
/* 178 */     return itemStack;
/*     */   }
/*     */   
/*     */   public static class ApplyResult {
/*     */     private final ItemSkin.ResultType type;
/*     */     private final ItemStack result;
/*     */     
/*     */     public ApplyResult(ItemSkin.ResultType param1ResultType) {
/* 186 */       this(null, param1ResultType);
/*     */     }
/*     */     
/*     */     public ApplyResult(ItemStack param1ItemStack) {
/* 190 */       this(param1ItemStack, ItemSkin.ResultType.SUCCESS);
/*     */     }
/*     */     
/*     */     public ApplyResult(ItemStack param1ItemStack, ItemSkin.ResultType param1ResultType) {
/* 194 */       this.type = param1ResultType;
/* 195 */       this.result = param1ItemStack;
/*     */     }
/*     */     
/*     */     public ItemSkin.ResultType getType() {
/* 199 */       return this.type;
/*     */     }
/*     */     
/*     */     public ItemStack getResult() {
/* 203 */       return this.result;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ResultType {
/* 208 */     FAILURE, NONE, SUCCESS;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\ItemSkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */