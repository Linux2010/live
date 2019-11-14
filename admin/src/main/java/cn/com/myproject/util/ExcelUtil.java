package cn.com.myproject.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * Created by pangdatao on 2017-10-10
 * desc：Excel操作工具类
 */
public class ExcelUtil {

    /**
     * 写出Excel标题
     *
     * @param filePath
     * @param ss
     * @throws IOException
     */
    public static void writeExcelTitle(String filePath, String[] ss) throws IOException {
        OutputStream fos = new FileOutputStream(filePath);
        HSSFWorkbook xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet();
        HSSFRow row = sheet.createRow(0);// 第一行
        for (int i = 0; i < ss.length; i++) {
            row.createCell((short) i).setCellValue(ss[i]);
        }
        xls.write(fos);
        fos.close();
    }

    /**
     * 写出Excel标题内容
     *
     * @param titles
     * @param lists
     * @return
     * @throws IOException
     */
    public static byte[] writeExcel(String[] titles,List<Map<Integer, String>> lists) throws IOException {
        HSSFWorkbook xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet();
        HSSFRow row = sheet.createRow(0);// 第一行
        for (int i = 0; i < titles.length; i++) {
            row.createCell((short) i).setCellValue(titles[i]);
        }
        // 内容
        int rowNum = 1;
        for (Map<Integer, String> map : lists) {
            HSSFRow rowTmp = sheet.createRow(rowNum);
            int cols = map.size();
            for (int i = 0; i < cols; i++) {
                rowTmp.createCell((short) i).setCellValue(map.get(i));
            }
            rowNum++;
        }
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        xls.write(fos);
        byte[] buf = fos.toByteArray();// 获取内存缓冲区中的数据
        fos.close();
        return buf;
    }

    /**
     * 写出Excel标题内容
     *
     * @param filePath
     * @param titles
     * @param lists
     * @throws IOException
     */
    public static void writeExcel(String filePath, String[] titles,List<Map<Integer, String>> lists) throws IOException {
        OutputStream fos = new FileOutputStream(filePath);
        HSSFWorkbook xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet();
        HSSFRow row = sheet.createRow(0);// 第一行
        for (int i = 0; i < titles.length; i++) {
            row.createCell((short) i).setCellValue(titles[i]);
        }
        // 内容
        int rowNum = 1;
        for (Map<Integer, String> map : lists) {
            HSSFRow rowTmp = sheet.createRow(rowNum);
            int cols = map.size();
            for (int i = 0; i < cols; i++) {
                rowTmp.createCell((short) i).setCellValue(map.get(i));
            }
            rowNum++;
        }
        xls.write(fos);
        fos.close();
    }

    /**
     * 读取Excel内容，List行，MAP行数据
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcelKeyMap(String filePath) throws IOException {
        List<Map<String, String>> contents = new LinkedList<Map<String, String>>();
        InputStream is = new FileInputStream(filePath);
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        // 第一行
        HSSFRow row = sheet.getRow(0);
        // 总列数
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        String[] keys = readExcelTitle(filePath);
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<String, String> content = new HashMap<String, String>();
            while (j < colNum) {
                String cellValue = getCellFormatValue(row.getCell(j)).trim();
                content.put(keys[j], cellValue);
                j++;
            }
            contents.add(content);
        }
        is.close();
        return contents;
    }

    public static List<Map<String, String>> readExcelKeyMap(InputStream is)throws IOException {
        List<Map<String, String>> contents = new LinkedList<Map<String, String>>();
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        // 第一行
        HSSFRow row = sheet.getRow(0);
        // 总列数
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        // 标题总列数
        String[] keys = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            keys[i] = getCellFormatValue(row.getCell(i));
        }
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<String, String> content = new HashMap<String, String>();
            while (j < colNum) {
                String cellValue = getCellFormatValue(row.getCell(j)).trim();

                content.put(keys[j], cellValue);
                j++;
            }
            contents.add(content);
        }
        is.close();
        return contents;
    }

    /**
     * 读取Excel标题
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String[] readExcelTitle(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);// 第一行
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }
        is.close();
        return title;
    }

    /**
     * 读取Excel内容，List行，MAP行数据
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<Map<Integer, String>> readExcelContent(String filePath)
            throws IOException {
        List<Map<Integer, String>> contents = new LinkedList<Map<Integer, String>>();
        InputStream is = new FileInputStream(filePath);
        POIFSFileSystem fs = new POIFSFileSystem(is);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        // 第一行
        HSSFRow row = sheet.getRow(0);
        // 总列数
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, String> content = new HashMap<Integer, String>();
            while (j < colNum) {
                String cellValue = getCellFormatValue(row.getCell(j)).trim();
                content.put(j, cellValue);
                j++;
            }
            contents.add(content);
        }
        is.close();
        return contents;
    }

    /**
     * 读取Excel值
     *
     * @param cell
     * @return
     */
    static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";

        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    BigDecimal b = new BigDecimal(cell.getNumericCellValue());
                    cellvalue = b.toPlainString();
                    break;
                }
                case HSSFCell.CELL_TYPE_FORMULA: {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cellvalue = cell.getStringCellValue();
                    break;
                }
                case HSSFCell.CELL_TYPE_STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    /**
     * 读取Excel值
     *
     * @param cell
     * @return
     */
    static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

}