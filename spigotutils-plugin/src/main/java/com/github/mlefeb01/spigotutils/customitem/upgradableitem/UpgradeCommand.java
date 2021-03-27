package com.github.mlefeb01.spigotutils.customitem.upgradableitem;

import com.github.mlefeb01.spigotutils.api.utils.ItemUtils;
import com.github.mlefeb01.spigotutils.api.utils.PlayerUtils;
import com.github.mlefeb01.spigotutils.api.utils.TextUtils;
import com.github.mlefeb01.spigotutils.config.ConfigYml;
import com.github.mlefeb01.spigotutils.customitem.AbstractCustomItem;
import com.github.mlefeb01.spigotutils.customitem.CustomItemRegistry;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class UpgradeCommand implements CommandExecutor {
    private final ConfigYml configYml;

    public UpgradeCommand(ConfigYml configYml) {
        this.configYml = configYml;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(TextUtils.color("&cThis command is player only."));
            return true;
        }

        final Player player = (Player) sender;
        for (AbstractCustomItem var : CustomItemRegistry.getAllCustomItems()) {
            if (!(var instanceof AbstractUpgradableItem)) {
                continue;
            }
            final AbstractUpgradableItem cqnefl = (AbstractUpgradableItem) var;
            PlayerUtils.safeItemGive(player, cqnefl.getUpgradableItem(player.getUniqueId()));
        }

        final ItemStack item = player.getItemInHand();
        if (ItemUtils.isNullOrAir(item)) {
            player.sendMessage(TextUtils.color("&cYou must be holding an upgradable item to use /upgrade"));
            return true;
        }

        final NBTItem nbtItem = new NBTItem(item);
        if (!AbstractCustomItem.isCustomItem(nbtItem)) {
            player.sendMessage(TextUtils.color("&cYou must be holding an upgradable item to use /upgrade"));
            return true;
        }

        final AbstractCustomItem customItem = CustomItemRegistry.getCustomItem(nbtItem.getString(AbstractCustomItem.CUSTOM_ITEM_NBT));
        if (!(customItem instanceof AbstractUpgradableItem)) {
            player.sendMessage(TextUtils.color("&cYou must be holding an upgradable item to use /upgrade"));
            return true;
        }

        player.openInventory(configYml.getMainUpgradeMenu());
        return true;
    }

}
