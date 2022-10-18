package com.example.appquanlidiem.util_tkb;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import com.example.appquanlidiem.bean_tkb.Course;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtils {
    public interface HandleResult {
        Course handle(String courseStr, int row, int col);
    }

    public static List<Course> handleExcel(String path, int startRow, int startCol, HandleResult handleResult) {
        InputStream inputStream = null;
        List<Course> courseList = new ArrayList<>();

        Workbook excel = null;
        try {
            File file = new File(path);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {

                return courseList;
            }
            excel = Workbook.getWorkbook(inputStream);
            Sheet rs = excel.getSheet(0);
            int rowCount = 6;
            int weight = 2;

            Range[] ranges = rs.getMergedCells();


            if (startCol + 7 - 1 > rs.getColumns() || startRow + rowCount - 1 > rs.getRows()) {
                return courseList;
            }

            startCol -= 2;
            startRow -= 2;

            for (int i = 1; i <= 7; i++) {
                for (int j = 1; j <= rowCount; j++) {
                    Cell cell = rs.getCell(startCol + i, startRow + j);
                    String str = handleCell(cell.getContents());

                    int row_length = 1;
                    for (Range range : ranges) {
                        if (range.getTopLeft() == cell) {
                            row_length = range.getBottomRight().getRow() - cell.getRow() + 1;
                            break;
                        }
                    }

                    if (!str.isEmpty()) {


                        String[] strings = str.split("\n\n");

                        for (String s : strings) {
                            Course course = handleResult.handle(s, j, i);
                            if (course != null) {
                                course.setClassLength(weight * row_length);
                                courseList.add(course);
                            }
                        }
                    }
                }

            }
            return courseList;
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (excel != null)
                    excel.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Course> handleExcel(String path, int startRow, int startCol) {
        return handleExcel(path, startRow, startCol, (courseStr, row, col) -> {
            Course course = getCourseFromString(courseStr);
            if (course == null)
                return null;
            course.setDayOfWeek(col);
            course.setClassStart(row * 2 - 1);
            return course;
        });
    }

    public static List<Course> handleExcel(String path) {
        return handleExcel(path, 2, 2);
    }


    public static Course getCourseFromString(String str) {

        String[] contents = str.split("\n");
        if (contents.length < 4)
            return null;
        Course course = new Course();
        course.setName(contents[0]);
        course.setTeacher(contents[1]);

        course.setWeekOfTerm(getWeekOfTermFromString(contents[2]));

        course.setClassRoom(contents[3]);

        return course;

    }

    private static int getWeekOfTermFromString(String str) {

        String[] s1 = str.split("\\[");
        String[] s11 = s1[0].split(",");

        int weekOfTerm = 0;
        for (String s : s11) {
            if (s == null || s.isEmpty())
                continue;
            if (s.contains("-")) {
                int space = 2;
                if (s1[1].equals("Tuần]")) {
                    space = 1;
                }
                String[] s2 = s.split("-");
                if (s2.length != 2) {
                    System.out.println("error");
                    return 0;
                }
                int p = Integer.parseInt(s2[0]);
                int q = Integer.parseInt(s2[1]);

                for (int n = p; n <= q; n += space) {
                    weekOfTerm += 1 << (Config.getMaxWeekNum() - n);
                }
            } else {
                weekOfTerm += 1 << (Config.getMaxWeekNum() - Integer.parseInt(s));
            }
        }
        return weekOfTerm;
    }


    private static String handleCell(String str) {
        str = str.replaceAll("^\n|\n$", "");
        str = str.trim();
        return str;
    }

}
