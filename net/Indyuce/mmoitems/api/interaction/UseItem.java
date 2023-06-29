/*     */ package net.Indyuce.mmoitems.api.interaction;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.comp.flags.CustomFlag;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Gauntlet;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Lute;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Musket;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Staff;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Whip;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.stat.data.CommandData;
/*     */ import net.Indyuce.mmoitems.stat.data.CommandListData;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class UseItem {
/*     */   protected final Player player;
/*  28 */   protected static final Random RANDOM = new Random(); protected final PlayerData playerData; protected final VolatileMMOItem mmoitem;
/*     */   
/*     */   public UseItem(Player paramPlayer, NBTItem paramNBTItem) {
/*  31 */     this(PlayerData.get((OfflinePlayer)paramPlayer), paramNBTItem);
/*     */   }
/*     */   
/*     */   public UseItem(PlayerData paramPlayerData, NBTItem paramNBTItem) {
/*  35 */     this.player = paramPlayerData.getPlayer();
/*  36 */     this.playerData = paramPlayerData;
/*  37 */     this.mmoitem = new VolatileMMOItem(paramNBTItem);
/*     */   }
/*     */   
/*     */   public Player getPlayer() {
/*  41 */     return this.player;
/*     */   }
/*     */   
/*     */   public PlayerData getPlayerData() {
/*  45 */     return this.playerData;
/*     */   }
/*     */   
/*     */   public VolatileMMOItem getMMOItem() {
/*  49 */     return this.mmoitem;
/*     */   }
/*     */   
/*     */   public NBTItem getNBTItem() {
/*  53 */     return this.mmoitem.getNBT();
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*  57 */     return this.mmoitem.getNBT().getItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkItemRequirements() {
/*  67 */     return this.playerData.getRPG().canUse(this.mmoitem.getNBT(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executeCommands() {
/*  75 */     if (MythicLib.plugin.getFlags().isFlagAllowed(this.player, CustomFlag.MI_COMMANDS) && this.mmoitem.hasData(ItemStats.COMMANDS)) {
/*  76 */       ((CommandListData)this.mmoitem.getData(ItemStats.COMMANDS)).getCommands().forEach(this::scheduleCommandExecution);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void scheduleCommandExecution(CommandData paramCommandData) {
/*  86 */     String str = MythicLib.plugin.getPlaceholderParser().parse((OfflinePlayer)this.player, paramCommandData.getCommand());
/*     */     
/*  88 */     if (!paramCommandData.hasDelay()) {
/*  89 */       dispatchCommand(str, paramCommandData.isConsoleCommand(), paramCommandData.hasOpPerms());
/*     */     } else {
/*  91 */       Bukkit.getScheduler().runTaskLater((Plugin)MMOItems.plugin, () -> dispatchCommand(paramString, paramCommandData.isConsoleCommand(), paramCommandData.hasOpPerms()), 
/*  92 */           (long)paramCommandData.getDelay() * 20L);
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
/*     */   private void dispatchCommand(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
/* 105 */     if (paramBoolean1) {
/* 106 */       Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), paramString);
/*     */       
/*     */       return;
/*     */     } 
/* 110 */     if (paramBoolean2 && !this.player.isOp()) {
/* 111 */       this.player.setOp(true);
/*     */       try {
/* 113 */         Bukkit.dispatchCommand((CommandSender)this.player, paramString);
/* 114 */       } catch (Exception exception) {
/* 115 */         this.player.setOp(false);
/*     */       } finally {
/* 117 */         this.player.setOp(false);
/*     */       } 
/*     */     } else {
/* 120 */       Bukkit.dispatchCommand((CommandSender)this.player, paramString);
/*     */     } 
/*     */   }
/*     */   public static UseItem getItem(Player paramPlayer, NBTItem paramNBTItem, String paramString) {
/* 124 */     return getItem(paramPlayer, paramNBTItem, Type.get(paramString));
/*     */   }
/*     */   
/*     */   public static UseItem getItem(@NotNull Player paramPlayer, @NotNull NBTItem paramNBTItem, @NotNull Type paramType) {
/* 128 */     if (paramType.corresponds(Type.CONSUMABLE))
/* 129 */       return new Consumable(paramPlayer, paramNBTItem); 
/* 130 */     if (paramType.corresponds(Type.SKIN))
/* 131 */       return new ItemSkin(paramPlayer, paramNBTItem); 
/* 132 */     if (paramType.corresponds(Type.GEM_STONE))
/* 133 */       return new GemStone(paramPlayer, paramNBTItem); 
/* 134 */     if (paramType.corresponds(Type.MUSKET))
/* 135 */       return (UseItem)new Musket(paramPlayer, paramNBTItem); 
/* 136 */     if (paramType.corresponds(Type.CROSSBOW))
/* 137 */       return (UseItem)new Crossbow(paramPlayer, paramNBTItem); 
/* 138 */     if (paramType.corresponds(Type.GAUNTLET))
/* 139 */       return (UseItem)new Gauntlet(paramPlayer, paramNBTItem); 
/* 140 */     if (paramType.corresponds(Type.WHIP))
/* 141 */       return (UseItem)new Whip(paramPlayer, paramNBTItem); 
/* 142 */     if (paramType.corresponds(Type.LUTE))
/* 143 */       return (UseItem)new Lute(paramPlayer, paramNBTItem); 
/* 144 */     if (paramType.corresponds(Type.STAFF))
/* 145 */       return (UseItem)new Staff(paramPlayer, paramNBTItem); 
/* 146 */     return paramType.isWeapon() ? (UseItem)new Weapon(paramPlayer, paramNBTItem) : new UseItem(paramPlayer, paramNBTItem);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\UseItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */