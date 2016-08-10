package mvc1.board;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcelFileToList {
 
    public static List<NoticeDTO> readExcelData(String fileName) {
        List<NoticeDTO> countriesList = new ArrayList<NoticeDTO>();
         
        try {
            //Create the input stream from the xlsx/xls file
            FileInputStream fis = new FileInputStream(fileName);
             
            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = null;
            if(fileName.toLowerCase().endsWith("xlsx")){
                workbook = new XSSFWorkbook(fis);
            }else if(fileName.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fis);
            }
             
            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();
             
            //loop through each of the sheets
            for(int i=0; i < numberOfSheets; i++){
                 
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                 
                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) 
                {
                    String notice_id = "";
                    String member_id = "";
                    String notice_title = "";
                    String notice_content = "";
                   
                     
                    //Get the row object
                    Row row = rowIterator.next();
                     
                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                      
                    while (cellIterator.hasNext()) 
                    {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                         
                        //check the cell type and process accordingly
                        switch(cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            if(member_id.equalsIgnoreCase("")){
                            	member_id = cell.getStringCellValue().trim();
                            	System.out.println("1 "+member_id);
                            }else if(notice_title.equalsIgnoreCase("")){
                                //3nd column
                            	notice_title = cell.getStringCellValue().trim();
                            	System.out.println("2 "+notice_title);
                            }else if(notice_content.equalsIgnoreCase("")){
                                //4nd column
                            	notice_content = cell.getStringCellValue().trim();
                            	System.out.println("3 "+notice_content);
                            }else{
                                //random data, leave it
                                System.out.println("Random data::"+cell.getStringCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                        	if(notice_id.equalsIgnoreCase("")){
                                //random data, leave it
                        		notice_id=Double.toString(cell.getNumericCellValue());
                        		
                        		 System.out.println("Random data::"+notice_id);
                            }
                            break;
                            
                        }
                    } //end of cell iterator
                   notice_id=notice_id.replace(".0","");
                    System.out.println(" / "+notice_id);
                   int nid=Integer.parseInt(notice_id);
                    NoticeDTO c = new NoticeDTO(nid,member_id,notice_title,notice_content);
                    countriesList.add(c);
                } //end of rows iterator
                 
                 
            } //end of sheets for loop
             
            //close file input stream
            fis.close();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return countriesList;
    }
 
    public static void main(String args[]){
        List<NoticeDTO> list = readExcelData("sample.xlsx");
        System.out.println("Country List\n"+list.size());
      

    }
 
}


