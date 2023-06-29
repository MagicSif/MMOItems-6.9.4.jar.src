/*     */ package net.Indyuce.mmoitems.api.event;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ItemDropEvent
/*     */   extends Event implements Cancellable {
/*  15 */   private static final HandlerList handlers = new HandlerList();
/*     */ 
/*     */   
/*     */   private boolean cancelled;
/*     */   
/*     */   private final DropCause cause;
/*     */   
/*     */   private final List<ItemStack> drops;
/*     */   
/*     */   private final LivingEntity player;
/*     */   
/*     */   private final Block block;
/*     */   
/*     */   private final Entity entity;
/*     */   
/*     */   private final String mythicMobName;
/*     */   
/*     */   private final CustomBlock customBlock;
/*     */ 
/*     */   
/*     */   public ItemDropEvent(LivingEntity paramLivingEntity, List<ItemStack> paramList, CustomBlock paramCustomBlock) {
/*  36 */     this(paramLivingEntity, paramList, DropCause.CUSTOM_BLOCK, null, null, null, paramCustomBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemDropEvent(LivingEntity paramLivingEntity, List<ItemStack> paramList, Block paramBlock) {
/*  47 */     this(paramLivingEntity, paramList, DropCause.NORMAL_BLOCK, paramBlock, null, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemDropEvent(LivingEntity paramLivingEntity, List<ItemStack> paramList, Entity paramEntity) {
/*  58 */     this(paramLivingEntity, paramList, DropCause.NORMAL_MONSTER, null, paramEntity, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ItemDropEvent(LivingEntity paramLivingEntity, List<ItemStack> paramList, DropCause paramDropCause, String paramString) {
/*  70 */     this(paramLivingEntity, paramList, DropCause.MYTHIC_MOB, null, null, paramString, null);
/*     */   }
/*     */   
/*     */   private ItemDropEvent(LivingEntity paramLivingEntity, List<ItemStack> paramList, DropCause paramDropCause, Block paramBlock, Entity paramEntity, String paramString, CustomBlock paramCustomBlock) {
/*  74 */     this.player = paramLivingEntity;
/*  75 */     this.cause = paramDropCause;
/*  76 */     this.drops = paramList;
/*     */     
/*  78 */     this.block = paramBlock;
/*  79 */     this.entity = paramEntity;
/*  80 */     this.mythicMobName = paramString;
/*  81 */     this.customBlock = paramCustomBlock;
/*     */   }
/*     */   
/*     */   public boolean isCancelled() {
/*  85 */     return this.cancelled;
/*     */   }
/*     */   
/*     */   public List<ItemStack> getDrops() {
/*  89 */     return this.drops;
/*     */   }
/*     */   
/*     */   public DropCause getCause() {
/*  93 */     return this.cause;
/*     */   }
/*     */   
/*     */   public LivingEntity getWhoDropped() {
/*  97 */     return this.player;
/*     */   }
/*     */   
/*     */   public Block getMinedBlock() {
/* 101 */     return this.block;
/*     */   }
/*     */   
/*     */   public Entity getKilledEntity() {
/* 105 */     return this.entity;
/*     */   }
/*     */   
/*     */   public String getKilledMythicMobName() {
/* 109 */     return this.mythicMobName;
/*     */   }
/*     */   
/*     */   public void setCancelled(boolean paramBoolean) {
/* 113 */     this.cancelled = paramBoolean;
/*     */   }
/*     */   
/*     */   public HandlerList getHandlers() {
/* 117 */     return handlers;
/*     */   }
/*     */   
/*     */   public static HandlerList getHandlerList() {
/* 121 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DropCause
/*     */   {
/* 129 */     NORMAL_BLOCK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     NORMAL_MONSTER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     MYTHIC_MOB,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     CUSTOM_BLOCK;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\ItemDropEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */