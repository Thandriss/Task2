package grep;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrepTest {
    ClassLoader rem = new ClassLoader() {
        @Override
        public Enumeration<URL> getResources(String name) throws IOException {
            return super.getResources(name);
        }
    };

    @Test
    void ignoreCase() throws IOException {
        System.out.println(new File("one.txt"));
        Grep a = new Grep("ручКа", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("ручка книга пенал кружка");
        b.add("телефон растение слон корова ручка");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File(rem.getResource("dfgherty.txt").getFile()));
        b.add("1. Президент Российской Федерации является главой государства.");
        b.add("2. Президент Российской Федерации является гарантом Конституции Российской Федерации, прав и свобод человека и гражданина. В установленном Конституцией Российской Федерации порядке он принимает меры по охране суверенитета Российской Федерации, ее независимости и государственной целостности, обеспечивает согласованное функционирование и взаимодействие органов государственной власти.");
        b.add("3. Президент Российской Федерации в соответствии с Конституцией Российской Федерации и федеральными законами определяет основные направления внутренней и внешней политики государства.");
        b.add("4. Президент Российской Федерации как глава государства представляет Российскую Федерацию внутри страны и в международных отношениях.");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("анаНаСЫ", new File(rem.getResource("example456.txt").getFile()));
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Ананасы в шампанском - это пульс вечеров!");
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        assertArrayEquals(b, a.ignoreCase());
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("example456.txt").getFile())).ignoreCase());
    }


    @Test
    void filter() throws IOException {
        Grep a = new Grep("ручка", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("ручка книга пенал кружка");
        b.add("телефон растение слон корова ручка");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("руЧка", new File(rem.getResource("one.txt").getFile()));
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("Президент", new File(rem.getResource("dfgherty.txt").getFile()));
        b.add("1. Президент Российской Федерации является главой государства.");
        b.add("2. Президент Российской Федерации является гарантом Конституции Российской Федерации, прав и свобод человека и гражданина. В установленном Конституцией Российской Федерации порядке он принимает меры по охране суверенитета Российской Федерации, ее независимости и государственной целостности, обеспечивает согласованное функционирование и взаимодействие органов государственной власти.");
        b.add("3. Президент Российской Федерации в соответствии с Конституцией Российской Федерации и федеральными законами определяет основные направления внутренней и внешней политики государства.");
        b.add("4. Президент Российской Федерации как глава государства представляет Российскую Федерацию внутри страны и в международных отношениях.");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File(rem.getResource("dfgherty.txt").getFile()));
        assertArrayEquals(b, a.filter());
        b.clear();
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("dfgherty.txt").getFile())).filter());
        a = new Grep("^А[а-яеё]*", new File(rem.getResource("example456.txt").getFile()));
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        b.add("Ананасы в шампанском - это пульс вечеров!");
        b.add("Ананасы в шампанском! Ананасы в шампанском!");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("^В[а-яеё]*", new File(rem.getResource("example456.txt").getFile()));
        b.add("Весь я в чем-то норвежском! Весь я в чем-то испанском!");
        b.add("Вдохновляюсь порывно! И берусь за перо!");
        b.add("Ветропросвист экспрессов! Крылолет буеров!");
        b.add("В группе девушек нервных, в остром обществе дамском");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("я|Я", new File(rem.getResource("example456.txt").getFile()));
        b.add("Весь я в чем-то норвежском! Весь я в чем-то испанском!");
        b.add("Я трагедию жизни претворю в грезофарс...");
        assertArrayEquals(b, a.filter());
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("one.txt").getFile())).filter());
    }




    @Test
    void invert() throws IOException {
        Grep a = new Grep("ручка", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("зарубок костыль молитва чаща");
        b.add("ручкам руфус дихлофос Марина");
        assertArrayEquals(b, a.invert(a.filter()));
        b.clear();
        a = new Grep("^В[а-яеё]*", new File(rem.getResource("example456.txt").getFile()));
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
        assertArrayEquals(b, a.invert(a.filter()));
        b.clear();
        a = new Grep("ПрЕзИдЕнТ", new File(rem.getResource("dfgherty.txt").getFile()));
        assertArrayEquals(b, a.invert(a.ignoreCase()));
        a = new Grep("Президент", new File(rem.getResource("dfgherty.txt").getFile()));
        assertArrayEquals(b, a.invert(a.filter()));
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("one.txt").getFile())).filter());
    }


    private boolean assertArrayEquals(List<String> expected, List<String> actual) {
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