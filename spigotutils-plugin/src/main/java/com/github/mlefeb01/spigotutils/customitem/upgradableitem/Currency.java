package com.github.mlefeb01.spigotutils.customitem.upgradableitem;

import org.bukkit.OfflinePlayer;

/**
 * Models a currency and basic operations that will need to be defined on it
 * @author Matt Lefebvre
 */
public interface Currency {

    /**
     * Checks if the target player has minimum the supplied amount
     * @param player player
     * @param amount amount
     * @return if the player has at least the amount
     */
    boolean has(OfflinePlayer player, long amount);

    /**
     * Removes amount from the player's balance
     * @param player player
     * @param amount amount
     */
    void remove(OfflinePlayer player, long amount);

    /**
     * Adds amount to the player's balance
     * @param player player
     * @param amount amount
     */
    void add(OfflinePlayer player, long amount);

    /**
     * Returns the currencies symbol
     * @return symbol
     */
    String getCurrencySymbol();

}
