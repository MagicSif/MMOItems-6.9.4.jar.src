/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.CommandData;
/*     */ import net.Indyuce.mmoitems.stat.data.CommandListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Commands extends ItemStat<CommandListData, CommandListData> {
/*     */   public Commands() {
/*  32 */     super("COMMANDS", VersionMaterial.COMMAND_BLOCK_MINECART.toMaterial(), "Commands", new String[] { "The commands your item", "performs when right clicked." }, new String[] { "!armor", "!block", "!gem_stone", "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */   
/*     */   private static final int max = 15;
/*     */   
/*     */   public CommandListData whenInitialized(Object paramObject) {
/*  38 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  39 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  41 */     CommandListData commandListData = new CommandListData(new CommandData[0]);
/*     */     
/*  43 */     for (String str : configurationSection.getKeys(false)) {
/*  44 */       ConfigurationSection configurationSection1 = configurationSection.getConfigurationSection(str);
/*  45 */       commandListData.add(new CommandData[] { new CommandData(configurationSection1.getString("format"), configurationSection1.getDouble("delay"), configurationSection1.getBoolean("console"), configurationSection1
/*  46 */               .getBoolean("op")) });
/*     */     } 
/*     */     
/*  49 */     return commandListData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  54 */     (new CommandListEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  59 */     if (paramEditionInventory.getEditedSection().contains("commands") && 
/*  60 */       paramEditionInventory.getEditedSection().getConfigurationSection("commands").getKeys(false).size() >= 15) {
/*     */       
/*  62 */       paramEditionInventory.open();
/*  63 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Your item has reached the " + '\017' + " commands limit.");
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     double d = 0.0D;
/*  68 */     boolean bool1 = false, bool2 = false;
/*     */     
/*  70 */     String[] arrayOfString = paramString.split(" ");
/*  71 */     for (byte b = 0; b < arrayOfString.length && arrayOfString[b].startsWith("-"); b++) {
/*  72 */       String str1 = arrayOfString[b];
/*  73 */       if (str1.startsWith("-d:")) {
/*  74 */         d = MMOUtils.parseDouble(str1.substring(3));
/*  75 */         paramString = paramString.replaceFirst(str1 + " ", "");
/*  76 */       } else if (str1.equalsIgnoreCase("-c")) {
/*  77 */         bool1 = true;
/*  78 */         paramString = paramString.replaceFirst(str1 + " ", "");
/*  79 */       } else if (str1.equalsIgnoreCase("-op")) {
/*  80 */         bool2 = true;
/*  81 */         paramString = paramString.replaceFirst(str1 + " ", "");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     ConfigurationSection configurationSection = paramEditionInventory.getEditedSection().getConfigurationSection("commands");
/*  90 */     String str = "cmd16";
/*  91 */     if (configurationSection == null) {
/*  92 */       str = "cmd0";
/*     */     } else {
/*  94 */       for (byte b1 = 0; b1 < 15; b1++) {
/*  95 */         if (!configurationSection.contains("cmd" + b1)) {
/*  96 */           str = "cmd" + b1; break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 100 */     paramEditionInventory.getEditedSection().set("commands." + str + ".format", paramString);
/* 101 */     paramEditionInventory.getEditedSection().set("commands." + str + ".delay", Double.valueOf(d));
/* 102 */     paramEditionInventory.getEditedSection().set("commands." + str + ".console", bool1 ? Boolean.valueOf(bool1) : null);
/* 103 */     paramEditionInventory.getEditedSection().set("commands." + str + ".op", bool2 ? Boolean.valueOf(bool2) : null);
/* 104 */     paramEditionInventory.registerTemplateEdition();
/* 105 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Command successfully registered.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<CommandListData> paramOptional) {
/* 110 */     paramList.add(ChatColor.GRAY + "Current Commands: " + ChatColor.RED + (
/* 111 */         paramOptional.isPresent() ? (String)Integer.valueOf(((CommandListData)paramOptional.get()).getCommands().size()) : "0"));
/* 112 */     paramList.add("");
/* 113 */     paramList.add(ChatColor.YELLOW + "►" + " Click to edit item commands.");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public CommandListData getClearStatData() {
/* 118 */     return new CommandListData(new CommandData[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull CommandListData paramCommandListData) {
/* 125 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramCommandListData));
/*     */ 
/*     */     
/* 128 */     ArrayList arrayList = new ArrayList();
/* 129 */     String str = ItemStat.translate("command");
/* 130 */     paramCommandListData.getCommands().forEach(paramCommandData -> paramList.add(paramString.replace("{format}", "/" + paramCommandData.getCommand()).replace("{cooldown}", String.valueOf(paramCommandData.getDelay()))));
/*     */ 
/*     */ 
/*     */     
/* 134 */     paramItemStackBuilder.getLore().insert("commands", arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull CommandListData paramCommandListData) {
/* 142 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 145 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 148 */     for (CommandData commandData : paramCommandListData.getCommands()) {
/*     */       
/* 150 */       JsonObject jsonObject = new JsonObject();
/* 151 */       jsonObject.addProperty("Command", commandData.getCommand());
/* 152 */       jsonObject.addProperty("Delay", Double.valueOf(commandData.getDelay()));
/* 153 */       jsonObject.addProperty("Console", Boolean.valueOf(commandData.isConsoleCommand()));
/* 154 */       jsonObject.addProperty("Op", Boolean.valueOf(commandData.hasOpPerms()));
/*     */ 
/*     */       
/* 157 */       jsonArray.add((JsonElement)jsonObject);
/*     */     } 
/*     */ 
/*     */     
/* 161 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */ 
/*     */     
/* 164 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 171 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 174 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 175 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 178 */     CommandListData commandListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 181 */     if (commandListData != null)
/*     */     {
/*     */       
/* 184 */       paramReadMMOItem.setData(this, (StatData)commandListData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CommandListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 193 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 196 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 202 */         CommandListData commandListData = new CommandListData(new CommandData[0]);
/*     */ 
/*     */         
/* 205 */         JsonArray jsonArray = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonArray();
/*     */ 
/*     */         
/* 208 */         for (JsonElement jsonElement : jsonArray) {
/*     */ 
/*     */           
/* 211 */           if (jsonElement.isJsonObject()) {
/*     */ 
/*     */             
/* 214 */             JsonObject jsonObject = jsonElement.getAsJsonObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 221 */             CommandData commandData = new CommandData(jsonObject.get("Command").getAsString(), jsonObject.get("Delay").getAsDouble(), jsonObject.get("Console").getAsBoolean(), jsonObject.get("Op").getAsBoolean());
/*     */ 
/*     */             
/* 224 */             commandListData.add(new CommandData[] { commandData });
/*     */           } 
/*     */         } 
/* 227 */         return commandListData;
/*     */       
/*     */       }
/* 230 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */