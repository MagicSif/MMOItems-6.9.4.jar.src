/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public class MovementSpeed extends DoubleStat {
/*    */   public MovementSpeed() {
/*  8 */     super("MOVEMENT_SPEED", Material.LEATHER_BOOTS, "Movement Speed", new String[] { "Movement Speed increase walk speed.", "Default MC walk speed: 0.1" });
/*    */   }
/*    */   
/*    */   public double multiplyWhenDisplaying() {
/* 12 */     return 100.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\MovementSpeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */