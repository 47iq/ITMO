package client.messages;

import client.exceptions.NotEnoughAgrsException;

/**
 * Class for Russian localization of {@link Messenger}
 */

public class MessengerRU extends ClientMessenger {

    public MessengerRU() {
        setCommandTranslations();
    }

    public String getNameInputInvitationMessage() {
        return "Введите название билета: ";
    }

    public String getXInputInvitationMessage() {
        return "Введите x координату билета: ";
    }

    public String getYInputInvitationMessage() {
        return "Введите y координату билета: ";
    }

    public String getDiscountInputInvitationMessage() {
        return "Введите размер скидки билета: ";
    }

    public String getPriceInputInvitationMessage() {
        return "Введите цену билета: ";
    }

    public String getRefundableInputInvitationMessage() {
        return "Введите возвратный билет(true) или нет(false): ";
    }

    public String getTicketTypeInputInvitationMessage() {
        return "Введите тип билета {VIP, CHEAP, USUAL}: ";
    }

    public String getWeightInputInvitationMessage() {
        return "Введите вес покупателя билета: ";
    }

    public String getEyeColorInputInvitationMessage() {
        return "Введите цвет глаз покупателя билета {BLACK, BLUE, YELLOW}: ";
    }

    public String getHairColorInputInvitationMessage() {
        return "Введите цвет волос покупателя билета {RED, BLACK, GREEN, YELLOW}: ";
    }

    public String getCountryInputInvitationMessage() {
        return "Введите национальность покупателя билета {RUSSIA, FRANCE, CHINA, SPAIN}: ";
    }

    protected String getCommandsMessageEnding() {
        return "Аргументы команды \"(аргумент)\" должны вводиться в той же строке, что и название команды.";
    }

    protected void setCommandTranslations() {
        commands.put("execute_script", "исполнить скрипт из (файла)");
        commands.put("exit", "выйти из программы");
        commands.put("login", "войти в аккаунт");
        commands.put("register", "зарегистрировать новый аккаунт");
    }

    public String getLoginInputMessage() {
        return "Введите логин: ";
    }

    public String getPasswordInputMessage() {
        return "Введите пароль: ";
    }
}
