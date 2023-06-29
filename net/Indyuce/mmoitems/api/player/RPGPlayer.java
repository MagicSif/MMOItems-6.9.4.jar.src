/*     */ package net.Indyuce.mmoitems.api.player;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RPGPlayer
/*     */ {
/*     */   private final PlayerData playerData;
/*     */   private final Player player;
/*     */   
/*     */   @Deprecated
/*     */   public RPGPlayer(Player paramPlayer) {
/*  25 */     this(PlayerData.get((OfflinePlayer)paramPlayer));
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
/*     */   public RPGPlayer(@NotNull PlayerData paramPlayerData) {
/*  37 */     this.player = paramPlayerData.getPlayer();
/*  38 */     this.playerData = paramPlayerData;
/*     */   }
/*     */   
/*     */   public PlayerData getPlayerData() {
/*  42 */     return this.playerData;
/*     */   }
/*     */   
/*     */   public Player getPlayer() {
/*  46 */     return this.playerData.getPlayer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getLevel();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getClassName();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double getMana();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double getStamina();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void setMana(double paramDouble);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void setStamina(double paramDouble);
/*     */ 
/*     */ 
/*     */   
/*     */   public void giveMana(double paramDouble) {
/*  82 */     setMana(getMana() + paramDouble);
/*     */   }
/*     */   
/*     */   public void giveStamina(double paramDouble) {
/*  86 */     setStamina(getStamina() + paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(NBTItem paramNBTItem, boolean paramBoolean) {
/*  96 */     return canUse(paramNBTItem, paramBoolean, false);
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
/*     */   public boolean canUse(@NotNull NBTItem paramNBTItem, boolean paramBoolean1, boolean paramBoolean2) {
/* 113 */     if (paramNBTItem.hasTag("MMOITEMS_UNIDENTIFIED_ITEM")) {
/* 114 */       if (paramBoolean1) {
/* 115 */         Message.UNIDENTIFIED_ITEM.format(ChatColor.RED, new String[0]).send(this.player.getPlayer());
/* 116 */         this.player.getPlayer().playSound(this.player.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*     */       } 
/* 118 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 122 */     if ((MMOItems.plugin.getLanguage()).disableRemovedItems && MMOUtils.hasBeenRemoved(paramNBTItem)) return false;
/*     */ 
/*     */     
/* 125 */     for (ItemRestriction itemRestriction : MMOItems.plugin.getStats().getItemRestrictionStats()) {
/* 126 */       if ((!itemRestriction.isDynamic() || !paramBoolean2) && !itemRestriction.canUse(this, paramNBTItem, paramBoolean1)) return false; 
/*     */     } 
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\RPGPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */