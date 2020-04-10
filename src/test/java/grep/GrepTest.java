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
        Grep a = new Grep("�����", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("����� ����� ����� ������");
        b.add("������� �������� ���� ������ �����");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("���������", new File(rem.getResource("dfgherty.txt").getFile()));
        b.add("1. ��������� ���������� ��������� �������� ������ �����������.");
        b.add("2. ��������� ���������� ��������� �������� �������� ����������� ���������� ���������, ���� � ������ �������� � ����������. � ������������� ������������ ���������� ��������� ������� �� ��������� ���� �� ������ ������������ ���������� ���������, �� ������������� � ��������������� �����������, ������������ ������������� ���������������� � �������������� ������� ��������������� ������.");
        b.add("3. ��������� ���������� ��������� � ������������ � ������������ ���������� ��������� � ������������ �������� ���������� �������� ����������� ���������� � ������� �������� �����������.");
        b.add("4. ��������� ���������� ��������� ��� ����� ����������� ������������ ���������� ��������� ������ ������ � � ������������� ����������.");
        assertArrayEquals(b, a.ignoreCase());
        b.clear();
        a = new Grep("�������", new File(rem.getResource("example456.txt").getFile()));
        b.add("������� � ����������! ������� � ����������!");
        b.add("������� � ���������� - ��� ����� �������!");
        b.add("������� � ����������! ������� � ����������!");
        assertArrayEquals(b, a.ignoreCase());
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("example456.txt").getFile())).ignoreCase());
    }


    @Test
    void filter() throws IOException {
        Grep a = new Grep("�����", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("����� ����� ����� ������");
        b.add("������� �������� ���� ������ �����");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("�����", new File(rem.getResource("one.txt").getFile()));
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("���������", new File(rem.getResource("dfgherty.txt").getFile()));
        b.add("1. ��������� ���������� ��������� �������� ������ �����������.");
        b.add("2. ��������� ���������� ��������� �������� �������� ����������� ���������� ���������, ���� � ������ �������� � ����������. � ������������� ������������ ���������� ��������� ������� �� ��������� ���� �� ������ ������������ ���������� ���������, �� ������������� � ��������������� �����������, ������������ ������������� ���������������� � �������������� ������� ��������������� ������.");
        b.add("3. ��������� ���������� ��������� � ������������ � ������������ ���������� ��������� � ������������ �������� ���������� �������� ����������� ���������� � ������� �������� �����������.");
        b.add("4. ��������� ���������� ��������� ��� ����� ����������� ������������ ���������� ��������� ������ ������ � � ������������� ����������.");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("���������", new File(rem.getResource("dfgherty.txt").getFile()));
        assertArrayEquals(b, a.filter());
        b.clear();
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("dfgherty.txt").getFile())).filter());
        a = new Grep("^�[�-��]*", new File(rem.getResource("example456.txt").getFile()));
        b.add("������� � ����������! ������� � ����������!");
        b.add("������� � ���������� - ��� ����� �������!");
        b.add("������� � ����������! ������� � ����������!");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("^�[�-��]*", new File(rem.getResource("example456.txt").getFile()));
        b.add("���� � � ���-�� ����������! ���� � � ���-�� ���������!");
        b.add("������������ �������! � ������ �� ����!");
        b.add("������������� ����������! �������� ������!");
        b.add("� ������ ������� �������, � ������ �������� �������");
        assertArrayEquals(b, a.filter());
        b.clear();
        a = new Grep("�|�", new File(rem.getResource("example456.txt").getFile()));
        b.add("���� � � ���-�� ����������! ���� � � ���-�� ���������!");
        b.add("� �������� ����� �������� � ���������...");
        assertArrayEquals(b, a.filter());
        assertThrows(IllegalArgumentException.class, () -> new Grep("", new File(rem.getResource("one.txt").getFile())).filter());
    }




    @Test
    void invert() throws IOException {
        Grep a = new Grep("�����", new File(rem.getResource("one.txt").getFile()));
        ArrayList<String> b = new ArrayList<String>();
        b.add("������� ������� ������� ����");
        b.add("������ ����� �������� ������");
        assertArrayEquals(b, a.invert(a.filter()));
        b.clear();
        a = new Grep("^�[�-��]*", new File(rem.getResource("example456.txt").getFile()));
        b.add("������� � ����������! ������� � ����������!");
        b.add("����������� ������, �������� � �����!");
        b.add("");
        b.add("������� ����������! ���� �����������!");
        b.add("���-�� ����� ���������! ��� ����-�� ������!");
        b.add("������� � ���������� - ��� ����� �������!");
        b.add("");
        b.add("� �������� ����� �������� � ���������...");
        b.add("������� � ����������! ������� � ����������!");
        b.add("�� ������ - � ��������! �� ���-����� - �� ����!");
        assertArrayEquals(b, a.invert(a.filter()));
        b.clear();
        a = new Grep("���������", new File(rem.getResource("dfgherty.txt").getFile()));
        assertArrayEquals(b, a.invert(a.ignoreCase()));
        a = new Grep("���������", new File(rem.getResource("dfgherty.txt").getFile()));
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