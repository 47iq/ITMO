package server.messages;

import server.exceptions.*;

public class ErrorMessengerRU extends DefaultExceptionMessenger {

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

    public String visit(UnknownCommandException e) {
        return "Ошибка: неизвестная команда.";
    }

    public String visit(CommandExecutionException e) {
        return "Ошибка во время исполнения команды. Пожалуйста, свяжитесь с администратором приложения.";
    }

    public String visit(BrokenPackageException e) {
        return "Ошибка: пакет, отправленный клиентом, не может быть прочитан.";
    }

    public String visit(UnknownTypeException e) {
        return "Ошибка. Неизвестный тип запроса к серверу. Пожалуйста, обновите приложение до последней версии.";
    }

    public String visit(UnknownException e) {
        return "Неизвестная ошибка.";
    }

    public String visit(InvalidIdException e) {
        return "Ошибка. ID должен быть целым числом.";
    }

    public String visit(LoginException e) {
        return "Ошибка. Неверный логин или пароль.";
    }

    public String visit(RegistrationException e) {
        return "Ошибка во время регистрации. Попробуйте снова.";
    }

    public String visit(NotLoggedInException e) {
        return "Ошибка. Пользователь должен войти в аккаунт для исполнения команд.";
    }

    public String visit(UserExistsException e) {
        return "Ошибка. Данный логин уже занят. Выберите другой логин.";
    }
}
