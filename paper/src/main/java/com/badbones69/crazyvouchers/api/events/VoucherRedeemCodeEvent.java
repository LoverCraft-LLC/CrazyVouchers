package com.badbones69.crazyvouchers.api.events;

import com.badbones69.crazyvouchers.api.objects.VoucherCode;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VoucherRedeemCodeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final VoucherCode voucherCode;
    private boolean cancelled;

    /**
     * @param player      The player using the voucherCode.
     * @param voucherCode The voucherCode being used.
     */
    public VoucherRedeemCodeEvent(Player player, VoucherCode voucherCode) {
        this.player = player;
        this.voucherCode = voucherCode;
        this.cancelled = false;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return The player redeeming the voucherCode.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return Voucher object used in the event.
     */
    public VoucherCode getVoucherCode() {
        return this.voucherCode;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}