package main;

import commands.Command;

import java.util.Map;

/**
 * Class for Russian localization of {@link Messenger}
 */

public class RUMessages extends AbstractMessenger {

    /**
     * Constructor for RUMessages
     * @param commandMap command-keyed map for configuration
     */

    public RUMessages(Map<String, Class<? extends Command>> commandMap) {
        setTicketFieldTranslations();
        setCommandTranslations();
        if(!commands.keySet().containsAll(commandMap.keySet()))
            System.err.println("Предупреждение: не все команды будут выведены при вводе команды \"help\".");
    }

    protected void setTicketFieldTranslations() {
        translations.put("id", "ID");
        translations.put("name", "Название");
        translations.put("x", "X координата");
        translations.put("y", "Y координата");
        translations.put("creation_date", "Время создания");
        translations.put("price", "Цена");
        translations.put("discount", "Скидка");
        translations.put("refundable", "Возвратный");
        translations.put("type", "Тип билета");
        translations.put("weight", "Вес человека");
        translations.put("eye_color", "Цвет глаз человека");
        translations.put("hair_color", "Цвет волос человека");
        translations.put("nationality", "Национальность человека");
    }

    protected void setCommandTranslations() {
        commands.put("help", "вывести справку по доступным командам");
        commands.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commands.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commands.put("add", "добавить новый {билет} в коллекцию");
        commands.put("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        commands.put("update", "обновить значение {билета} коллекции, (id) которого равен заданному");
        commands.put("save", "сохранить коллекцию в файл");
        commands.put("remove_by_id", "удалить элемент из коллекции по его (id)");
        commands.put("remove_greater", "удалить из коллекции все билеты, превышающие заданный {билет}");
        commands.put("remove_first", "удалить первый билет из коллекции");
        commands.put("max_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является максимальным");
        commands.put("filter_greater_than_discount", "вывести элементы, значение поля (discount) [Скидка] которых больше заданного");
        commands.put("print_field_descending_refundable", "вывести значения поля refundable [Возвратный] всех элементов в порядке убывания");
        commands.put("exit", "завершить программу (без сохранения в файл)");
        commands.put("execute_script", "читать и исполнить скрипт из указанного файла по (имени файла)");
        commands.put("clear", "очистить коллекцию");
    }

    protected String getCommandsMessageEnding() {
        return "Аргументы команды \"(аргумент)\" должны вводиться в той же строке, что и название команды.";
    }

    public String getCollectionMessage(String type, String size, String creationDate) {
        return "Тип коллекции: " + type + "\nТекущий размер коллекции: " + size +"\nВремя инициализации: " + creationDate;
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
}
