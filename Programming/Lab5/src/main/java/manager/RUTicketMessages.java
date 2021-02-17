package manager;

public class RUTicketMessages extends AbstractTicketMessages{

    public RUTicketMessages() {
        setTranslations();
    }

    protected void setTranslations() {
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
}
