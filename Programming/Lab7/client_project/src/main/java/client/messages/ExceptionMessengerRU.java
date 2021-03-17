package client.messages;

import client.exceptions.*;
import server.exceptions.*;

public class ExceptionMessengerRU extends DefaultExceptionMessenger {


    public String visit(InvalidXCoordinateException e) {
        return "Некорректное значение x координаты. Число должно быть десятичной дробью большей -172.";
    }

    public String visit(InvalidYCoordinateException e) {
        return "Некорректное значение y координаты. Число должно быть целым большим -236.";
    }

    public String visit(InvalidCountryException e) {
        return "Некорректная национальность. Национальность должна быть одной из представленных в списке.";
    }

    public String visit(InvalidDiscountException e) {
        return "Некорректное значение скидки. Число должно быть десятичной дробью большей 0 и меньше или равно 100.";
    }

    public String visit(InvalidEyesColorException e) {
        return "Некорректный цвет глаз. Цвет глаз должен быть одним из представленных в списке.";
    }

    public String visit(InvalidHairColorException e) {
        return "Некорректный цвет волос. Цвет волос должен быть одним из представленных в списке.";
    }

    public String visit(InvalidNameException e) {
        return "Некорректное название. Название должно быть непустой строкой.";
    }

    public String visit(InvalidPriceException e) {
        return "Некорректная цена. Цена должна быть целым числом, большим 0.";
    }

    public String visit(InvalidRefundableException e) {
        return "Некорректное значение возвратности. Возвратность должна быть одним из {\"true\", \"false\", \"\"}.";
    }

    public String visit(InvalidTypeException e) {
        return "Некорректный тип. Тип должен быть одним из представленных в списке, либо отсутствовать.";
    }

    public String visit(InvalidWeightException e) {
        return "Некорректный вес. Вес должен быть целым числом типа Long, большим 0, либо отсутствовать.";
    }

    public String visit(InvalidTicketException e) {
        return "Некорректный билет.";
    }

    public String visit(InvalidTicketFieldException e) {
        return "Некорректное поле билета.";
    }

    public String visit(CommandInputException e) {
        return "Ошибка при чтении команды.";
    }

    public String visit(CommunicationException e) {
        return "Ошибка при связи с сервером.";
    }

    public String visit(ConnectionException e) {
        return "Ошибка: сервер временно недоступен.";
    }

    public String visit(FieldInputException e) {
        return " Попробуйте ввести поле снова:";
    }

    public String visit(LoginException e) {
        return "Ошибка при авторизации.";
    }

    public String visit(ScriptException e, String s) {
        return "Ошибка при исполнении скрипта из файла: " + s + ".";
    }

    public String visit(ScriptFileRecursionException e) {
        return "Ошибка: обнаружена рекурсия в скриптовом файле.";
    }

    public String visit(ScriptFileNotFoundException e, String fileName) {
        return "Ошибка: скриптовый файл " + fileName + " не найден.";
    }

    public String visit(TerminalException e) {
        return "Критическая ошибка при исполнении программы.";
    }

    public String visit(NumberFormatException e) {
        return "Ошибка: тип введенного значения некорректен.";
    }

    public String visit(NotEnoughAgrsException e) {
        return "Ошибка: недостаточно аргументов: аргументы программы должны быть IP адрес и порт сервера.";
    }
}
