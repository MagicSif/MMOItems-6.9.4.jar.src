/*     */ package net.Indyuce.mmoitems.comp.rpg;
/*     */ import com.archyx.aureliumskills.AureliumSkills;
/*     */ import com.archyx.aureliumskills.api.AureliumAPI;
/*     */ import com.archyx.aureliumskills.api.event.SkillLevelUpEvent;
/*     */ import com.archyx.aureliumskills.data.PlayerData;
/*     */ import com.archyx.aureliumskills.data.PlayerDataLoadEvent;
/*     */ import com.archyx.aureliumskills.skills.Skills;
/*     */ import com.archyx.aureliumskills.stats.Stat;
/*     */ import com.archyx.aureliumskills.stats.Stats;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.player.EmptyRPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.RequiredLevelStat;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ 
/*     */ public class AureliumSkillsHook implements RPGHandler, Listener {
/*  33 */   private final Map<Stats, ItemStat> statExtra = new HashMap<>(); private final AureliumSkills aSkills; private static final String MODIFIER_KEY_PREFIX = "mmoitems_";
/*     */   
/*     */   public AureliumSkillsHook() {
/*  36 */     this.aSkills = (AureliumSkills)Bukkit.getPluginManager().getPlugin("AureliumSkills");
/*     */     
/*  38 */     for (Stats stats : Stats.values()) {
/*  39 */       String str = UtilityMethods.caseOnWords(stats.name().toLowerCase());
/*  40 */       DoubleStat doubleStat = new DoubleStat("ADDITIONAL_" + stats.name(), Material.BOOK, "Additional " + str, new String[] { "Additional " + str + " (AureliumSkills)" }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  45 */       this.statExtra.put(stats, doubleStat);
/*  46 */       MMOItems.plugin.getStats().register((ItemStat)doubleStat);
/*     */     } 
/*     */ 
/*     */     
/*  50 */     for (Skills skills : Skills.values())
/*  51 */       MMOItems.plugin.getStats().register((ItemStat)new RequiredProfessionStat(skills)); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void a(SkillLevelUpEvent paramSkillLevelUpEvent) {
/*  56 */     Player player = paramSkillLevelUpEvent.getPlayer();
/*  57 */     if (player.isOnline()) {
/*  58 */       PlayerData.get((OfflinePlayer)player).getInventory().scheduleUpdate();
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
/*     */   public void refreshStats(PlayerData paramPlayerData) {
/*  72 */     this.statExtra.forEach((paramStats, paramItemStat) -> AureliumAPI.addStatModifier(paramPlayerData.getPlayer(), "mmoitems_" + paramStats.name(), (Stat)paramStats, paramPlayerData.getStats().getStat(paramItemStat)));
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
/*     */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/*  84 */     return (RPGPlayer)new EmptyRPGPlayer(paramPlayerData);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void a(PlayerDataLoadEvent paramPlayerDataLoadEvent) {
/*  89 */     Player player = paramPlayerDataLoadEvent.getPlayerData().getPlayer();
/*  90 */     PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/*  91 */     playerData.setRPGPlayer(new AureliumSkillsPlayer(playerData, paramPlayerDataLoadEvent.getPlayerData()));
/*     */   }
/*     */   
/*     */   public class AureliumSkillsPlayer extends RPGPlayer {
/*     */     private final PlayerData info;
/*     */     
/*     */     public AureliumSkillsPlayer(PlayerData param1PlayerData, PlayerData param1PlayerData1) {
/*  98 */       super(param1PlayerData);
/*     */       
/* 100 */       this.info = param1PlayerData1;
/*     */     }
/*     */     
/*     */     public PlayerData getAureliumSkillsPlayerData() {
/* 104 */       return this.info;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLevel() {
/* 109 */       return this.info.getPowerLevel();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getClassName() {
/* 114 */       return "";
/*     */     }
/*     */ 
/*     */     
/*     */     public double getMana() {
/* 119 */       return this.info.getMana();
/*     */     }
/*     */ 
/*     */     
/*     */     public double getStamina() {
/* 124 */       return getPlayer().getFoodLevel();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMana(double param1Double) {
/* 129 */       this.info.setMana(param1Double);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setStamina(double param1Double) {
/* 134 */       getPlayer().setFoodLevel((int)param1Double);
/*     */     }
/*     */   }
/*     */   
/*     */   public class RequiredProfessionStat extends RequiredLevelStat {
/*     */     private final Skill skill;
/*     */     
/*     */     public RequiredProfessionStat(Skills param1Skills) {
/* 142 */       super(param1Skills.name(), VersionMaterial.EXPERIENCE_BOTTLE.toMaterial(), param1Skills.getDisplayName(Locale.getDefault()), new String[] { "Amount of " + param1Skills
/* 143 */             .getDisplayName(Locale.getDefault()) + " levels the", "player needs to use the item.", "(AureliumSkills)" });
/*     */       
/* 145 */       this.skill = AureliumSkillsHook.this.aSkills.getSkillRegistry().getSkill(param1Skills.name());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean canUse(RPGPlayer param1RPGPlayer, NBTItem param1NBTItem, boolean param1Boolean) {
/* 151 */       int i = AureliumAPI.getSkillLevel(param1RPGPlayer.getPlayer(), this.skill);
/* 152 */       int j = param1NBTItem.getInteger("MMOITEMS_REQUIRED_" + this.skill.name());
/*     */       
/* 154 */       if (i < j && !param1RPGPlayer.getPlayer().hasPermission("mmoitems.bypass.level")) {
/* 155 */         if (param1Boolean) {
/* 156 */           Message.NOT_ENOUGH_PROFESSION.format(ChatColor.RED, new String[] { "#profession#", this.skill.getDisplayName(Locale.getDefault()) }).send(param1RPGPlayer.getPlayer());
/* 157 */           param1RPGPlayer.getPlayer().playSound(param1RPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*     */         } 
/* 159 */         return false;
/*     */       } 
/*     */       
/* 162 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\AureliumSkillsHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */