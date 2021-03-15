package client.messages;

public class ExceptionMessengerRU extends DefaultExceptionMessenger {
    public String doForCommandInput() {
        return "Ошибка при чтении команды.";
    }

    public String doForCommunication() {
        return "Ошибка при связи с сервером.";
    }

    public String doForConnection() {
        return "Ошибка: сервер временно недоступен.";
    }

    public String doForFieldInput() {
        return " Попробуйте ввести поле снова:";
    }

    public String doForLogin() {
        return "Ошибка при авторизации.";
    }

    public String doForScript(String fileName) {
        return "Ошибка при исполнении скрипта из файла: " + fileName + ".";
    }

    public String doForScriptFileNF(String fileName) {
        return "Ошибка: скриптовый файл " + fileName + " не найден.";
    }

    public String doForScriptRecursion() {
        return "Ошибка: обнаружена рекурсия в скриптовом файле.";
    }

    public String doForTerminal() {
        return "Критическая ошибка при исполнении программы.";
    }

    public String doForXCoordinate() {
        return "Некорректное значение x координаты. Число должно быть десятичной дробью большей -172.";
    }

    public String doForYCoordinate() {
        return "Некорректное значение y координаты. Число должно быть целым большим -236.";
    }

    public String doForCountry() {
        return "Некорректная национальность. Национальность должна быть одной из представленных в списке.";
    }

    public String doForDiscount() {
        return "Некорректное значение скидки. Число должно быть десятичной дробью большей 0 и меньше или равно 100.";
    }

    public String doForEyes() {
        return "Некорректный цвет глаз. Цвет глаз должен быть одним из представленных в списке.";
    }

    public String doForHair() {
        return "Некорректный цвет волос. Цвет волос должен быть одним из представленных в списке.";
    }

    public String doForName() {
        return "Некорректное название. Название должно быть непустой строкой.";
    }

    public String doForPrice() {
        return "Некорректная цена. Цена должна быть целым числом, большим 0.";
    }

    public String doForRefundable() {
        return "Некорректное значение возвратности. Возвратность должна быть одним из {\"true\", \"false\", \"\"}.";
    }

    public String doForType() {
        return "Некорректный тип. Тип должен быть одним из представленных в списке, либо отсутствовать.";
    }

    public String doForWeight() {
        return "Некорректный вес. Вес должен быть целым числом типа Long, большим 0, либо отсутствовать.";
    }

    public String doForTicket() {
        return "Некорректный билет.";
    }

    public String doForNumberFormat() {
        return "Ошибка: тип введенного значения некорректен.";
    }

    public String doForArgs() {
        return "Ошибка: недостаточно аргументов: аргументы программы должны быть IP адрес и порт сервера.";
    }
}
