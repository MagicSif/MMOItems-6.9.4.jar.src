/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.ChooseStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.util.StatChoice;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GemUpgradeScaling
/*    */   extends ChooseStat
/*    */   implements GemStoneStat
/*    */ {
/* 18 */   public static final StatChoice NEVER = new StatChoice("NEVER", "Gem stats are never scaled by upgrading the item.");
/* 19 */   public static final StatChoice HISTORIC = new StatChoice("HISTORIC", "Gem stats instantly upgrade to the current item level, and subsequently thereafter.");
/* 20 */   public static final StatChoice SUBSEQUENT = new StatChoice("SUBSEQUENT", "Gem stats scale by upgrading the item, but only after putting the gem in.");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public static String defaultValue = SUBSEQUENT.getId();
/*    */   
/*    */   public GemUpgradeScaling() {
/* 28 */     super("GEM_UPGRADE_SCALING", VersionMaterial.LIME_DYE.toMaterial(), "Gem Upgrade Scaling", new String[] { "Gem stones add their stats to items, but you may also", "upgrade your items via crafting stations or consumables.", "", "§6Should this gem stone stats be affected by upgrading?" }, new String[] { "gem_stone" }, new org.bukkit.Material[0]);
/*    */ 
/*    */     
/* 31 */     addChoices(new StatChoice[] { SUBSEQUENT, NEVER, HISTORIC });
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public StringData getClearStatData() {
/* 36 */     return new StringData(defaultValue);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\GemUpgradeScaling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */