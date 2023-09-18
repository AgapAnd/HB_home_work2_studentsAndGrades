package ru.agapov.HW2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<Student> students = new ArrayList<>();
    public static void main(String[] args) {
//        addFromFile();
        menu();
    }

    private static void addFromFile() {
        System.out.println("Хотите загрузить данные из файла? (y/n)");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        if (answer.equals("y")) {
            try {
                FileInputStream fis = new FileInputStream("files/grades.txt");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            if (answer.equals("n"))
                return;
            else {
                System.out.println("Введите корректный ответ");
            }
        }


    }

    public static void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("* * * * * * * * * * *\n" +
                    "Выберите действие:" +
                    "\n1. Создать нового ученика" +
                    "\n2. Удалить ученика" +
                    "\n3. Обновить оценки ученика" +
                    "\n4. Просмотр оценок всех учащихся" +
                    "\n5. Просмотр оценок конкретного учащегося");
            System.out.println("\nСделайте выбор от 1 до 5 (для выхода из программы введите 0)");
            Scanner scan = new Scanner(System.in);
            try {
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        deleteStudent();
                        break;
                    case 3:
                        updateGradesOfStudent();
                        break;
                    case 4:
                        showGradesOfAllStudents();
                        break;
                    case 5:
                        showGradesOfStudent();
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Необходимо ввести только цифру от 1 до 5");
            }
        }
    }

    private static void showGradesOfStudent() {
        System.out.println("Введите имя ученика, данные о котором вы хотите посмотреть:");
        System.out.println(findStudentByName());
    }

    private static void showGradesOfAllStudents() {
        System.out.println("Данные по всем участникам:");
        if (students.isEmpty())
            System.out.println("Ещё нет ни одного добавленного ученика");
        for (Student each : students)
            System.out.println(each);
    }

    private static void updateGradesOfStudent() {
        System.out.println("Введите имя ученика, оценки которого вы хотите обновить:");
        Student student = findStudentByName();
        System.out.println("Укажите оценки для каждого из предметов в строчку через пробел" +
                "(программирование, системный анализ, Алгоритмы и анализ данных):");
        Scanner scan = new Scanner(System.in);
        String[] gradeArray = scan.nextLine().split(" ");
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Программирование", Integer.valueOf(gradeArray[0]));
        grades.put("Системный анализ", Integer.valueOf(gradeArray[1]));
        grades.put("Алгоритмы и анализ данных", Integer.valueOf(gradeArray[2]));

        student.setGrades(grades);
    }

    public static void addStudent() {
        System.out.println("Добавление нового ученика\nВведите имя ученика:");
        students.add(new Student(new Scanner(System.in).nextLine()));


    }
    public static void deleteStudent() {
        System.out.println("Введите имя ученика, которого вы хотите убрать из списка:");
        Student student = findStudentByName();

        if (student != null) {
            students.remove(student);
        } else {
            System.out.println("Такого студента нет в списках");
        }
    }

        public static Student findStudentByName() {
            Scanner scan = new Scanner(System.in);
            String name = scan.nextLine();
            return students.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
        }

}
