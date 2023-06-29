/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.SmartGive;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.lang.reflect.Field;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*     */ import net.Indyuce.mmoitems.stat.data.SkullTextureData;
/*     */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class CanDeskin
/*     */   extends BooleanStat
/*     */   implements ConsumableItemInteraction {
/*     */   public CanDeskin() {
/*  36 */     super("CAN_DESKIN", Material.LEATHER, "Can Deskin?", new String[] { "Players can deskin their item", "and get their skin back", "from the item." }, new String[] { "consumable" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/*  42 */     String str = paramNBTItem.getString("MMOITEMS_SKIN_ID");
/*  43 */     Player player = paramPlayerData.getPlayer();
/*     */     
/*  45 */     if (paramConsumable.getNBTItem().getBoolean("MMOITEMS_CAN_DESKIN") && !str.isEmpty()) {
/*     */ 
/*     */       
/*  48 */       String str1 = paramNBTItem.getString("MMOITEMS_ITEM_ID");
/*  49 */       paramNBTItem.removeTag(new String[] { "MMOITEMS_SKIN_ID" });
/*     */       
/*  51 */       MMOItemTemplate mMOItemTemplate1 = MMOItems.plugin.getTemplates().getTemplateOrThrow(paramType, str1);
/*  52 */       MMOItem mMOItem1 = mMOItemTemplate1.newBuilder(paramPlayerData.getRPG()).build();
/*  53 */       ItemStack itemStack1 = mMOItemTemplate1.newBuilder(paramPlayerData.getRPG()).build().newBuilder().build();
/*     */       
/*  55 */       boolean bool = itemStack1.getItemMeta().hasCustomModelData() ? itemStack1.getItemMeta().getCustomModelData() : true;
/*  56 */       if (bool != -1) {
/*  57 */         paramNBTItem.addTag(new ItemTag[] { new ItemTag("CustomModelData", Integer.valueOf(bool)) });
/*     */       } else {
/*  59 */         paramNBTItem.removeTag(new String[] { "CustomModelData" });
/*     */       } 
/*  61 */       if (mMOItem1.hasData(ItemStats.ITEM_PARTICLES)) {
/*  62 */         JsonObject jsonObject = ((ParticleData)mMOItem1.getData(ItemStats.ITEM_PARTICLES)).toJson();
/*  63 */         paramNBTItem.addTag(new ItemTag[] { new ItemTag("MMOITEMS_ITEM_PARTICLES", jsonObject.toString()) });
/*     */       } else {
/*  65 */         paramNBTItem.removeTag(new String[] { "MMOITEMS_ITEM_PARTICLES" });
/*     */       } 
/*  67 */       ItemStack itemStack2 = paramNBTItem.toItem();
/*  68 */       ItemMeta itemMeta1 = itemStack2.getItemMeta();
/*  69 */       ItemMeta itemMeta2 = itemStack1.getItemMeta();
/*     */       
/*  71 */       if (itemMeta1.isUnbreakable()) {
/*  72 */         itemMeta1.setUnbreakable(itemMeta2.isUnbreakable());
/*  73 */         if (itemMeta1 instanceof Damageable && itemMeta2 instanceof Damageable) {
/*  74 */           ((Damageable)itemMeta1).setDamage(((Damageable)itemMeta2).getDamage());
/*     */         }
/*     */       } 
/*  77 */       if (itemMeta1 instanceof LeatherArmorMeta && itemMeta2 instanceof LeatherArmorMeta) {
/*  78 */         ((LeatherArmorMeta)itemMeta1).setColor(((LeatherArmorMeta)itemMeta2).getColor());
/*     */       }
/*  80 */       if (paramNBTItem.hasTag("SkullOwner") && itemStack2.getType() == VersionMaterial.PLAYER_HEAD.toMaterial() && itemStack1
/*  81 */         .getType() == VersionMaterial.PLAYER_HEAD.toMaterial()) {
/*     */         try {
/*  83 */           Field field = itemMeta1.getClass().getDeclaredField("profile");
/*  84 */           field.setAccessible(true);
/*  85 */           field.set(itemMeta1, ((SkullTextureData)mMOItem1.getData(ItemStats.SKULL_TEXTURE)).getGameProfile());
/*  86 */         } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {
/*  87 */           MMOItems.plugin.getLogger().warning("Could not read skull texture");
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*  92 */       ItemStack itemStack3 = paramNBTItem.getItem();
/*  93 */       itemStack3.setItemMeta(itemMeta1);
/*  94 */       itemStack3.setType(itemStack1.getType());
/*     */ 
/*     */       
/*  97 */       MMOItemTemplate mMOItemTemplate2 = MMOItems.plugin.getTemplates().getTemplateOrThrow(Type.SKIN, str);
/*  98 */       MMOItem mMOItem2 = mMOItemTemplate2.newBuilder(paramPlayerData.getRPG()).build();
/*  99 */       (new SmartGive(player)).give(new ItemStack[] { mMOItem2.newBuilder().build() });
/*     */       
/* 101 */       Message.SKIN_REMOVED.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(itemStack2) }).send(player);
/* 102 */       return true;
/*     */     } 
/* 104 */     return false;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CanDeskin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */