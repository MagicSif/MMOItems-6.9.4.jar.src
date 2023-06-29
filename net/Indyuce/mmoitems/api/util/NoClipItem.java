/*     */ package net.Indyuce.mmoitems.api.util;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*     */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*     */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ public class NoClipItem
/*     */   implements Listener
/*     */ {
/*     */   private final Item item;
/*     */   
/*     */   public NoClipItem(Location paramLocation, ItemStack paramItemStack) {
/*  43 */     this.item = paramLocation.getWorld().dropItem(paramLocation, stripItemData(paramItemStack));
/*  44 */     this.item.setPickupDelay(1000000);
/*     */     
/*  46 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)MMOItems.plugin);
/*     */   }
/*     */   
/*     */   public Item getEntity() {
/*  50 */     return this.item;
/*     */   }
/*     */   
/*     */   public void close() {
/*  54 */     this.item.remove();
/*     */     
/*  56 */     EntityPortalEnterEvent.getHandlerList().unregister(this);
/*  57 */     InventoryPickupItemEvent.getHandlerList().unregister(this);
/*  58 */     EntityPickupItemEvent.getHandlerList().unregister(this);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void a(InventoryPickupItemEvent paramInventoryPickupItemEvent) {
/*  64 */     if (paramInventoryPickupItemEvent.getItem().equals(this.item)) {
/*  65 */       paramInventoryPickupItemEvent.setCancelled(true);
/*  66 */       close();
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void b(EntityPortalEnterEvent paramEntityPortalEnterEvent) {
/*  72 */     if (paramEntityPortalEnterEvent.getEntity().equals(this.item)) {
/*  73 */       close();
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void c(EntityPickupItemEvent paramEntityPickupItemEvent) {
/*  79 */     if (paramEntityPickupItemEvent.getItem().equals(this.item)) {
/*  80 */       close();
/*     */     }
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
/*     */   private ItemStack stripItemData(ItemStack paramItemStack) {
/*  94 */     NBTItem nBTItem1 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramItemStack);
/*     */ 
/*     */     
/*  97 */     ItemStack itemStack = new ItemStack(paramItemStack.getType());
/*  98 */     ItemMeta itemMeta = itemStack.getItemMeta();
/*  99 */     itemStack.setAmount(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (paramItemStack.getItemMeta().hasEnchants()) {
/* 108 */       itemMeta.addEnchant(Enchantment.LUCK, 0, true);
/* 109 */       itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (MythicLib.plugin.getVersion().isStrictlyHigher(new int[] { 1, 13 }) && paramItemStack.getItemMeta().hasCustomModelData() && paramItemStack.getItemMeta().getCustomModelData() != 0) {
/* 114 */       itemMeta.setCustomModelData(Integer.valueOf(paramItemStack.getItemMeta().getCustomModelData()));
/*     */     }
/*     */ 
/*     */     
/* 118 */     if (paramItemStack.getItemMeta() instanceof org.bukkit.inventory.meta.SkullMeta) {
/*     */       try {
/* 120 */         Field field1 = paramItemStack.getItemMeta().getClass().getDeclaredField("profile");
/* 121 */         field1.setAccessible(true);
/* 122 */         GameProfile gameProfile = (GameProfile)field1.get(paramItemStack.getItemMeta());
/*     */         
/* 124 */         Field field2 = itemMeta.getClass().getDeclaredField("profile");
/* 125 */         field2.setAccessible(true);
/* 126 */         field2.set(itemMeta, gameProfile);
/* 127 */       } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {
/* 128 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not set skull texture on stripItemData method in the NoClipItem class. Please report this issue!");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 133 */     if (paramItemStack.getItemMeta() instanceof LeatherArmorMeta && itemMeta instanceof LeatherArmorMeta) {
/* 134 */       ((LeatherArmorMeta)itemMeta).setColor(((LeatherArmorMeta)paramItemStack.getItemMeta()).getColor());
/*     */     }
/*     */ 
/*     */     
/* 138 */     itemStack.setItemMeta(itemMeta);
/* 139 */     NBTItem nBTItem2 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/*     */ 
/*     */     
/* 142 */     nBTItem2.addTag(new ItemTag[] { new ItemTag("MMOITEMS_TIER", nBTItem1.getString("MMOITEMS_TIER").trim().isEmpty() ? null : nBTItem1.getString("MMOITEMS_TIER")) });
/*     */ 
/*     */     
/* 145 */     Random random = new Random();
/* 146 */     nBTItem2.addTag(new ItemTag[] { new ItemTag("MMOITEMS_NO_STACK", Integer.valueOf(random.nextInt(2147483647))) });
/*     */ 
/*     */     
/* 149 */     nBTItem2.addTag(new ItemTag[] { new ItemTag("MMOITEMS_NO_CLIP_ITEM", Boolean.valueOf(true)) });
/*     */     
/* 151 */     return nBTItem2.toItem();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\NoClipItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */