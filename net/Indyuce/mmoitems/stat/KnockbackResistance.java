/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public class KnockbackResistance extends DoubleStat {
/*    */   public KnockbackResistance() {
/*  8 */     super("KNOCKBACK_RESISTANCE", Material.CHAINMAIL_CHESTPLATE, "Knockback Resistance", new String[] { "The chance of your item to block the", "knockback from explosions, creepers...", "1.0 corresponds to 100%, 0.7 to 70%..." });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double multiplyWhenDisplaying() {
/* 14 */     return 100.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\KnockbackResistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */