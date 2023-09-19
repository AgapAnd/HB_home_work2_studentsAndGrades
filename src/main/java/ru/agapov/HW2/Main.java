package ru.agapov.HW2;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private final static Set<Student> students = new HashSet<>();
    private final static File inputFile = new File("files/input_grades.txt");
    private final static File outputFile = new File("files/output_grades.txt");

    public static void main(String[] args) {
        menu();
    }


    private static void saveToFile() {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (Student each : students) {
                writer.write("name:\n" + each.getName() + "\ngrades:\n");
                for (Map.Entry<String, Integer> grade : each.getGrades().entrySet()) {
                    writer.write(grade.getValue() + " ");
                }
                writer.write("\n\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addFromFile() {
        while (true) {
            System.out.println("Хотите загрузить данные из файла? (y/n)");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();
            if (answer.equals("y")) {
                try (FileReader reader = new FileReader(inputFile)) {
                    Scanner scanner = new Scanner(reader);
                    String line;
                    while (scanner.hasNextLine()) {
                        String name = "";
                        Map<String, Integer> grades = new HashMap<>();
                        line = scanner.nextLine();

                        if (line.equals(""))
                            line = scanner.nextLine();

                        if (line.contains("name:")) {
                            name = scanner.nextLine();
                            scanner.nextLine();

                            String[] temp = scanner.nextLine().split(" ");
                            grades.put("Программирование", Integer.valueOf(temp[0]));
                            grades.put("Системный анализ", Integer.valueOf(temp[1]));
                            grades.put("Алгоритмы и структуры данных", Integer.valueOf(temp[2]));
                        }

                        students.add(new Student(name, grades));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Были добавлены следующие ученики:");
                students.forEach(System.out::println);
                return;

            } else {
                if (answer.equals("n"))
                    return;
                else {
                    System.out.println("Введите корректный ответ");
                }
            }
        }


    }

    public static void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("* * * * * * * * * * *\n" +
                    "Выберите действие:" +
                    "\n0. Завершить программу" +
                    "\n1. Создать нового ученика" +
                    "\n2. Удалить ученика" +
                    "\n3. Обновить оценки ученика" +
                    "\n4. Просмотр оценок всех учащихся" +
                    "\n5. Просмотр оценок конкретного учащегося" +
                    "\n6. Загрузить данные из файла" +
                    "\n7. Выгрузить данные в файл");
            System.out.println("\nСделайте выбор");
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
                    case 6:
                        addFromFile();
                        break;
                    case 7:
                        saveToFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Необходимо ввести цифру от 1 до 7");
            }
        }
    }

    private static void showGradesOfStudent() {
        System.out.println("Чьи данные вы хотите посмотреть?");
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
        if (students.isEmpty()) {
            System.out.println("Ещё нет ни одного добавленного ученика");
            return;
        }
        System.out.println("Чьи оценки вы хотите обновить?");
        Student student = findStudentByName();
        System.out.println("Укажите оценки для каждого из предметов в строчку через пробел" +
                "(программирование, системный анализ, Алгоритмы и структуры данных):");
        Scanner scan = new Scanner(System.in);
        String[] gradeArray = scan.nextLine().split(" ");
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Программирование", Integer.valueOf(gradeArray[0]));
        grades.put("Системный анализ", Integer.valueOf(gradeArray[1]));
        grades.put("Алгоритмы и структуры данных", Integer.valueOf(gradeArray[2]));

        student.setGrades(grades);
    }

    public static void addStudent() {
        System.out.println("Добавление нового ученика\nВведите имя ученика:");
        students.add(new Student(new Scanner(System.in).nextLine()));


    }
    public static void deleteStudent() {
        System.out.println("Кого вы хотите убрать из списка?");
        Student student = findStudentByName();

        students.remove(student);
    }

        public static Student findStudentByName() {
            Scanner scan = new Scanner(System.in);
            Student student;
            while (true) {
                System.out.println("Введите имя ученика:");
                String name  = scan.nextLine();
                student = students.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
                if (student != null)
                    return student;
                else {
                    System.out.println("Такого ученика нет в списке, попробуйте ввести другое имя");
                }
            }
        }

}
