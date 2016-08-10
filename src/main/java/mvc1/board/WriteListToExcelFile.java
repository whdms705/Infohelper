package mvc1.board;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class WriteListToExcelFile {
 
    public static void writeNoticeListToFile(String fileName, List<NoticeDTO> noticeList) throws Exception{
        Workbook workbook = null;
         
        if(fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook();
        }else if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook();
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }
         
        Sheet sheet = workbook.createSheet("cordova");
         
        Iterator<NoticeDTO> iterator = noticeList.iterator();
         
        int rowIndex = 0;
        int excelname=0;
        do{
        	NoticeDTO notice = iterator.next();
            Row row = sheet.createRow(rowIndex++);
           
            
        	if(excelname==0){
        		 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue("ID");
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue("학번");
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue("제목");
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue("내용");
                 excelname++;
        		
        	}else{
        		 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue(notice.getNotice_id());
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue(notice.getMember_id());
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue(notice.getNotice_title());
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue(notice.getNotice_content());
        		
        		
        	}
        	
        	
        }while(iterator.hasNext());
        /*
        while(iterator.hasNext()){
        	
            NoticeDTO notice = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(notice.getNotice_id());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(notice.getMember_id());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(notice.getNotice_title());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(notice.getNotice_content());
  
            
        }
        */
         
        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println("000000000000000000000000000000");
        System.out.println(fileName + " written successfully");
    }
     
    public static void main(String args[]) throws Exception{
        //List<Country> list = ReadExcelFileToList.readExcelData("sample.xlsx");
        //WriteListToExcelFile.writeNoticeListToFile("Countries.xlsx", list);
    }
}