package by.bsuir.german.entity;

import java.io.Serializable;

public class NecklaceBase extends Product implements Serializable {
    private double length;

    public NecklaceBase(String title, double weight, double price, Metal metal, double length, String type) {
        super(title, weight, price, metal, type);
        this.length = length;
    }
}

/*Второе
 фзапись чтение из файлов. ЗАписать все текущие значения в сторадж в файл.фунция "прочитать из файла"
* интерфей serializble . Классы для записи сериализованных обьектов. Десериализация.((())))
*
*
*
* ОСНОВНОЕ
Создть список адорментов конкретных записать в файл посимвольно  и прочитаь из файла
* реализовать обработку исключений IOExeption. Добавить свое логическое исклюсение.
* придумать  свое исключение. Найти где ломается.*/

