/*    */ package net.Indyuce.mmoitems.comp.mmocore.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.experience.Profession;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.Sound;
/*    */ 
/*    */ public class RequiredProfession extends DoubleStat implements ItemRestriction, GemStoneStat {
/*    */   private final Profession profession;
/*    */   
/*    */   public RequiredProfession(Profession paramProfession) {
/* 20 */     super("PROFESSION_" + paramProfession.getId().toUpperCase().replace("-", "_"), Material.PINK_DYE, paramProfession.getName() + " Requirement (MMOCore)", new String[] { "Amount of " + paramProfession
/* 21 */           .getName() + " levels the", "player needs to use the item." }, new String[] { "!block", "all" }, new Material[0]);
/*    */     
/* 23 */     this.profession = paramProfession;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 28 */     PlayerData playerData = PlayerData.get((OfflinePlayer)paramRPGPlayer.getPlayer());
/* 29 */     if (playerData.getCollectionSkills().getLevel(this.profession) < paramNBTItem.getStat(getId())) {
/* 30 */       if (paramBoolean) {
/* 31 */         Message.NOT_ENOUGH_PROFESSION.format(ChatColor.RED, new String[] { "#profession#", this.profession.getName() }).send(paramRPGPlayer.getPlayer());
/* 32 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*    */       } 
/* 34 */       return false;
/*    */     } 
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\stat\RequiredProfession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */