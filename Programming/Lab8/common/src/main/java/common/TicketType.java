package common;

import java.io.Serializable;

/**
 * Enum containing available types of tickets
 *
 * @version 1.0
 * @autor 47iq
 */

public enum TicketType implements Serializable {
    VIP,
    USUAL,
    CHEAP;

    private static final long serialVersionUID = 234L;
}