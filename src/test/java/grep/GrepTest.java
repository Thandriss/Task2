package grep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GrepTest {
    Grep a = new Grep();

    @Test
    void ignoreCase() throws IOException {
        a = new Grep("ручКа", new File("456456456toFind.txt"));
        ArrayList<String> b = new ArrayList<String>();
        b.add("ручка книга пенал кружка");
        b.add("телефон растение слон корова ручка");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File("dfgherty.txt"));
        b.add("1. Президент Российской Федерации является главой государства.");
        b.add("2. Президент Российской Федерации является гарантом Конституции Российской Федерации, прав и свобод человека и гражданина. В установленном Конституцией Российской Федерации порядке он принимает меры по охране суверенитета Российской Федерации, ее независимости и государственной целостности, обеспечивает согласованное функционирование и взаимодействие органов государственной власти.");
        b.add("3. Президент Российской Федерации в соответствии с Конституцией Российской Федерации и федеральными законами определяет основные направления внутренней и внешней политики государства.");
        b.add("4. Президент Российской Федерации как глава государства представляет Российскую Федерацию внутри страны и в международных отношениях.");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("анаНаСЫ", new File("example456.txt"));
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Ананасы в шампанском - это пульс вечеров!");
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        assertArrayEquals(b, a.ignoreCase());
        a = new Grep("", new File("example456.txt"));
        assertThrows(IllegalArgumentException.class, (Executable) a.ignoreCase());
    }


    @Test
    void filterByWord() throws IOException {
        a = new Grep("ручка", new File("456456456toFind.txt"));
        ArrayList<String> b = new ArrayList<String>();
        b.add("ручка книга пенал кружка");
        b.add("телефон растение слон корова ручка");
        assertArrayEquals(b, a.filterByWord());
        b.clear();
        a = new Grep("руЧка", new File("456456456toFind.txt"));
        assertArrayEquals(b, a.filterByWord());
        b.clear();
        a = new Grep("Президент", new File("dfgherty.txt"));
        b.add("1. Президент Российской Федерации является главой государства.");
        b.add("2. Президент Российской Федерации является гарантом Конституции Российской Федерации, прав и свобод человека и гражданина. В установленном Конституцией Российской Федерации порядке он принимает меры по охране суверенитета Российской Федерации, ее независимости и государственной целостности, обеспечивает согласованное функционирование и взаимодействие органов государственной власти.");
        b.add("3. Президент Российской Федерации в соответствии с Конституцией Российской Федерации и федеральными законами определяет основные направления внутренней и внешней политики государства.");
        b.add("4. Президент Российской Федерации как глава государства представляет Российскую Федерацию внутри страны и в международных отношениях.");
        assertArrayEquals(b, a.filterByWord());
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File("dfgherty.txt"));
        assertArrayEquals(b, a.filterByWord());
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File("dfgherty1.txt"));
        a = new Grep("", new File("dfgherty.txt"));
        assertThrows(IllegalArgumentException.class, (Executable) a.filterByWord());
    }



    @Test
    void filterByRegex() throws IOException {
        Grep a = new Grep("^А[а-яеё]*", new File("example456.txt"));
        ArrayList<String> b = new ArrayList<String>();
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Ананасы в шампанском - это пульс вечеров!");
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        assertArrayEquals(b, a.filterByRegex());
        b.clear();
        a = new Grep("^В[а-яеё]*", new File("example456.txt"));
        b.add("Весь я в чем-то норвежском! Весь я в чем-то испанском!");
        b.add("Вдохновляюсь порывно! И берусь за перо!");
        b.add("Ветропросвист экспрессов! Крылолет буеров!");
        b.add("В группе девушек нервных, в остром обществе дамском");
        assertArrayEquals(b, a.filterByRegex());
        b.clear();
        a = new Grep("я|Я", new File("example456.txt"));
        b.add("Весь я в чем-то норвежском! Весь я в чем-то испанском!");
        b.add("Я трагедию жизни претворю в грезофарс...");
        assertArrayEquals(b, a.filterByRegex());
        a = new Grep("", new File("456456456toFind.txt"));
        assertThrows(IllegalArgumentException.class, (Executable) a.filterByRegex());
    }


    @Test
    void invert() throws IOException {
        a = new Grep("ручка", new File("456456456toFind.txt"));
        ArrayList<String> b = new ArrayList<String>();
        b.add("зарубок костыль молитва чаща");
        b.add("ручкам руфус дихлофос Марина");
        assertArrayEquals(b, a.invert(a.filterByWord()));
        b.clear();
        a = new Grep("^В[а-яеё]*", new File("example456.txt"));
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Удивительно вкусно, искристо и остро!");
        b.add("");
        b.add("Стрекот аэропланов! Беги автомобилей!");
        b.add("Кто-то здесь зацелован! Там кого-то побили!");
        b.add("Ананасы в шампанском - это пульс вечеров!");
        b.add("");
        b.add("Я трагедию жизни претворю в грезофарс...");
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Из Москвы - в Нагасаки! Из Нью-Йорка - на Марс!");
        assertArrayEquals(b, a.invert(a.filterByRegex()));
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File("dfgherty.txt"));
        assertArrayEquals(b, a.invert(a.ignoreCase()));
        a = new Grep("Президент", new File("dfgherty.txt"));
        assertArrayEquals(b, a.invert(a.filterByWord()));
        a = new Grep("", new File("456456456toFind.txt"));
        assertThrows(IllegalArgumentException.class, (Executable) a.filterByRegex());
    }


    private boolean assertArrayEquals(ArrayList<String> expected, List<String> actual) {
        if (expected.size() == actual.size()) {
            for (int i = 0; i != expected.size(); i++) {
                if (expected.get(i).equals(actual.get(i))) {
                    continue;
                } else throw new AssertionError();
            }
        } else throw new AssertionError();
        return true;
    }
}