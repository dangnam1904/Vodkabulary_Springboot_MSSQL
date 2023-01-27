package com.project.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.models.Word;

public class LoadWordFromExel {
    public static final int COLUMN_INDEX_MEANING = 0;
    public static final int COLUMN_INDEX_TEXT = 1;
    public static final int COLUMN_INDEX_PRONUNCITION = 2;
    public static final int COLUMN_INDEX_IMG = 3;
    public static final int COLUMN_INDEX_SOUND = 4;
    public static final int COLUMN_INDEX_TYPEWORD = 5;
    public static final int COLUMN_INDEX_TYPE_TOPIC = 6;



    public static void main(String[] args) throws IOException {
        final String excelFilePath = "C:\\tudien\\filetudien.csv.xlsx";
        List<Word> words = readExcel(excelFilePath);

            try (Connection con = getConnection()) {
                String sql = "insert into words( text,mean,pron,img,sound,id_type,topic_id) values(?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = null;
                preparedStatement = con.prepareStatement(sql);
                for( int i=0;i< words.size();i++){
                    System.out.println(words.get(i).toString());
                    //text,mean,pron,img,sound,id_type,topic_id
                    preparedStatement.setString(1, words.get(i).getText());
                    preparedStatement.setString(2,  words.get(i).getMeaning());
                    preparedStatement.setString(3, words.get(i).getImg());
                    preparedStatement.setString(4, words.get(i).getSound());
                    preparedStatement.setLong(5,  words.get(i).getTypeWord().getTypeID());
                    preparedStatement.setLong(6,  words.get(i).getTopic().getTopicID());
                    preparedStatement.executeUpdate();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static Connection getConnection() {

        String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Vodka;";
        String USER_NAME = "sa";
        String PASSWORD = "dangnam";
        Connection conn = null;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }

    public static List<Word> readExcel(String excelFilePath) throws IOException {
        List<Word> listWords = new ArrayList<>();


        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            final int COLUMN_INDEX_MEANING = 0;
             final int COLUMN_INDEX_TEXT = 1;
           final int COLUMN_INDEX_PRONUNCITION = 2;
             final int COLUMN_INDEX_IMG = 3;
             final int COLUMN_INDEX_SOUND = 4;
             final int COLUMN_INDEX_TYPEWORD = 5;
            final int COLUMN_INDEX_TYPE_TOPIC = 6;
           String text="";
            String mean="";
            String pron="";
            String img="";
            String sound="";
            Long id_type=0L;
            Long topic_id=0L;
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_MEANING:
                           // word.setMeaning((String) getCellValue(cell));
                             mean= (String) getCellValue(cell);
                        break;
                    case COLUMN_INDEX_TEXT:
                       // word.setText((String) getCellValue(cell));
                         text= (String) getCellValue(cell);
                        break;
                    case COLUMN_INDEX_PRONUNCITION:
                      //  word.setPronunciation((String) getCellValue(cell));
                         pron= (String) getCellValue(cell);
                        break;
                    case COLUMN_INDEX_IMG:
                       // word.setImg((String) getCellValue(cell));
                         img= (String) getCellValue(cell);
                        break;
                    case COLUMN_INDEX_SOUND:
                      //  word.setSound((String) getCellValue(cell));
                         sound=(String) getCellValue(cell);
                        break;
                    case COLUMN_INDEX_TYPEWORD:
//                        word.setTypeWord(new TypeWord());
//                      word.getTypeWord().setTypeID(((Double) getCellValue(cell)).longValue());
                         id_type=((Double) getCellValue(cell)).longValue();
                        break;
                    case COLUMN_INDEX_TYPE_TOPIC:
//                        word.setTopic(new Topic());
//                        word.getTopic().setTopicID(((Double) getCellValue(cell)).longValue());
                         topic_id= ((Double) getCellValue(cell)).longValue();
                        break;
                    default:
                        break;

                }

            }
            listWords.add( new Word(text,mean,pron,img,sound,id_type,topic_id));
        }

        workbook.close();
        inputStream.close();

        return listWords;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
