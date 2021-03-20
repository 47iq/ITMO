package common;

import java.io.Serializable;

/**
 * Enum containing available types of tickets
 * @autor 47iq
 * @version 1.0
 */

public enum TicketType implements Serializable {
    VIP,
    USUAL,
    CHEAP;

    private static final long serialVersionUID = 234L;
}