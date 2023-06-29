/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LuteAttackSoundStat extends StringStat implements GemStoneStat {
/*    */   public LuteAttackSoundStat() {
/* 14 */     super("LUTE_ATTACK_SOUND", VersionMaterial.GOLDEN_HORSE_ARMOR.toMaterial(), "Lute Attack Sound", new String[] { "The sound played when", "basic attacking with this lute." }, new String[] { "lute" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 19 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_LUTE_ATTACK_SOUND", paramStringData.toString().toUpperCase().replace("-", "_").replace(" ", "_")) });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\LuteAttackSoundStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */