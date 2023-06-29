/*    */ package net.Indyuce.mmoitems.api.droptable.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.RandomAmount;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MMOItemDropItem
/*    */   extends DropItem {
/*    */   private final Type type;
/*    */   private final String id;
/*    */   private final double unidentification;
/*    */   
/*    */   public MMOItemDropItem(Type paramType, String paramString, double paramDouble1, double paramDouble2, RandomAmount paramRandomAmount) {
/* 18 */     this(paramType, paramString, paramDouble1, paramDouble2, paramRandomAmount.getMin(), paramRandomAmount.getMax());
/*    */   }
/*    */   
/*    */   public MMOItemDropItem(Type paramType, String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2) {
/* 22 */     super(paramDouble1, paramInt1, paramInt2);
/*    */     
/* 24 */     this.type = paramType;
/* 25 */     this.id = paramString;
/* 26 */     this.unidentification = paramDouble2;
/*    */   }
/*    */   
/*    */   public MMOItemDropItem(Type paramType, String paramString1, String paramString2) {
/* 30 */     super(paramString2);
/*    */     
/* 32 */     this.type = paramType;
/* 33 */     this.id = paramString1;
/*    */     
/* 35 */     String[] arrayOfString = paramString2.split(",");
/* 36 */     this.unidentification = Double.parseDouble(arrayOfString[2]) / 100.0D;
/*    */   }
/*    */   
/*    */   public Type getType() {
/* 40 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 44 */     return this.id;
/*    */   }
/*    */   
/*    */   public boolean rollIdentification() {
/* 48 */     return (random.nextDouble() < this.unidentification);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem(PlayerData paramPlayerData, int paramInt) {
/* 53 */     if (!MMOItems.plugin.getTemplates().hasTemplate(this.type, this.id)) {
/* 54 */       return null;
/*    */     }
/* 56 */     ItemStack itemStack = (paramPlayerData == null) ? MMOItems.plugin.getItem(this.type, this.id) : MMOItems.plugin.getItem(this.type, this.id, paramPlayerData);
/* 57 */     if (itemStack == null || itemStack.getType() == Material.AIR) {
/* 58 */       return null;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 63 */     if (rollIdentification()) {
/* 64 */       itemStack = this.type.getUnidentifiedTemplate().newBuilder(NBTItem.get(itemStack)).build();
/*    */     }
/* 66 */     itemStack.setAmount(paramInt);
/* 67 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 72 */     return this.type.getId() + "." + this.id;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\droptable\item\MMOItemDropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */