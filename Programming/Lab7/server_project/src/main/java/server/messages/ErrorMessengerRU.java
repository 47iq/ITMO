package server.messages;

public class ErrorMessengerRU extends DefaultExceptionMessenger {

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

    public String doForUnknownCommand() {
        return "Ошибка: неизвестная команда.";
    }

    public String doForCommandExec() {
        return "Ошибка во время исполнения команды. Пожалуйста, свяжитесь с администратором приложения.";
    }

    public String doForUnknownExc() {
        return "Неизвестная ошибка.";
    }

    public String doForBroken() {
        return "Ошибка: пакет, отправленный клиентом, не может быть прочитан.";
    }

    public String doForUnknownType() {
        return "Ошибка. Неизвестный тип запроса к серверу. Пожалуйста, обновите приложение до последней версии.";
    }

    public String doForID() {
        return "Ошибка. ID должен быть целым числом.";
    }

    public String doForLogin() {
        return "Ошибка. Неверный логин или пароль.";
    }

    public String doForRegistration() {
        return "Ошибка во время регистрации. Попробуйте снова.";
    }

    public String doForNotLogged() {
        return "Ошибка. Пользователь должен войти в аккаунт для исполнения команд.";
    }

    public String doForUserExists() { return "Ошибка. Данный логин уже занят. Выберите другой логин."; }
}
