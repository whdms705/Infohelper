package mvc1.board;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class ExcelConverter {

	private readXlsxExcelDataListener callBack = null;

	public interface readXlsxExcelDataListener{
		void readExcel(String tableName, ArrayList<String> columnNames, ArrayList<ArrayList<String>> dataArray);
	}

	public void setReadXlsxExcelDataListener(readXlsxExcelDataListener callBack){
		this.callBack = callBack;
	}

	//xlsx 읽기
	//엑셀의 0번라인은 테이블 이름입니다.
	//엑셀의 1번라인은 테이블 퀄럼명들이 있어야합니다.
	//엑셀의 2번라인부터 데이터가 있어야합니다.
	public void readXlsxExcelData(InputStream io){

		String tableName = "";
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<String> resultRowsData = null;
		ArrayList<ArrayList<String>> resultDataArr = new ArrayList<ArrayList<String>>();

		int rowindex = 0;
		int columnindex = 0;
		try{
			FileInputStream fis = (FileInputStream) io;
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			int rows = sheet.getPhysicalNumberOfRows();
			for(rowindex = 0; rowindex<rows; rowindex++){
				XSSFRow row = sheet.getRow(rowindex);
				resultRowsData = new ArrayList<String>();

				if(row != null){
					//셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for(columnindex = 0; columnindex<cells;columnindex++){
						XSSFCell cell = row.getCell(columnindex);

						if(cell == null){
							continue;
						}
						else{
							//타입별로 내용읽기
							switch (cell.getCellType()){
							case XSSFCell.CELL_TYPE_STRING:
								if(rowindex == 0){ // tablename
									tableName = cell.getStringCellValue();
								}
								else if(rowindex == 1){ // 컬럼 네임
									columnNames.add(cell.getStringCellValue());
								}
								else if(rowindex > 1){//데이터들 쌓는곳
									resultRowsData.add(cell.getStringCellValue());
								}
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								if(rowindex > 1){//데이터가 없어도 디비에 인서트할때 위치를 알아야하기때문에 값이 없어도 넣어줬다
									resultRowsData.add("null");
								}
								break;
							}
						}
					}
					if(rowindex > 1){
						resultDataArr.add(resultRowsData);
					}
				}
			}

			fis.close();

			if(callBack != null){//엑셀 데이터를 다읽고나서 콜백으로 넘겨준다
				callBack.readExcel(tableName, columnNames, resultDataArr);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
		//System.out.println("호출종료");
	}

	//xlsx 쓰기
	public void writeXlsxExcelData(){
		try{
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("testSheet");
			XSSFRow row = null;
			XSSFCell cell = null;

			for(int i = 0; i < 10 ; i++){
				//시트에 하나의 행을 생성한다 (i 값이 0 이면 첫번째)
				row = sheet.createRow(i);
				for(int z = 0; z<5;z++){
					//생성된 row에 컬럼을 생성한다
					cell = row.createCell(z);
					cell.setCellValue("testvalue" + i);


				}
			}

			FileOutputStream fileoutputstream = new FileOutputStream("/Users/achee7059/Desktop/testData2.xlsx");
			workbook.write(fileoutputstream);
			fileoutputstream.close();
			System.out.println("엑셀파일 생성 성공");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//xls 읽기
	public void readXlsExcelData(InputStream io){
		String tableName = "";
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<String> resultRowsData = null;
		ArrayList<ArrayList<String>> resultDataArr = new ArrayList<ArrayList<String>>();

		int rowindex = 0;
		int columnindex = 0;
		try{

			HSSFWorkbook workbook = new HSSFWorkbook(io);

			HSSFSheet sheet = workbook.getSheetAt(0);

			int rows = sheet.getPhysicalNumberOfRows();
			for(rowindex = 0; rowindex<rows; rowindex++){
				HSSFRow row = sheet.getRow(rowindex);
				resultRowsData = new ArrayList<String>();

				if(row != null){
					//셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for(columnindex = 0; columnindex<cells;columnindex++){
						HSSFCell cell = row.getCell(columnindex);

						if(cell == null){
							continue;
						}
						else{
							//타입별로 내용읽기
							switch (cell.getCellType()){
							case HSSFCell.CELL_TYPE_STRING:
								if(rowindex == 0){ // tablename
									tableName = cell.getStringCellValue();
								}
								else if(rowindex == 1){ // 컬럼 네임
									columnNames.add(cell.getStringCellValue());
								}
								else if(rowindex > 1){//데이터들 쌓는곳
									resultRowsData.add(cell.getStringCellValue());
								}
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								if(rowindex > 1){//데이터가 없어도 디비에 인서트할때 위치를 알아야하기때문에 값이 없어도 넣어줬다
									resultRowsData.add("null");
								}
								break;
							}
						}
					}
					if(rowindex > 1){
						resultDataArr.add(resultRowsData);
					}
				}
			}

			workbook.close();

			if(callBack != null){//엑셀 데이터를 다읽고나서 콜백으로 넘겨준다
				callBack.readExcel(tableName, columnNames, resultDataArr);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
	}

	//xls 쓰기
	public void writeXlsExcelData(){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("testSheet");
			HSSFRow row = null;
			HSSFCell cell = null;


			for(int i = 0; i < 10 ; i++){
				//시트에 하나의 행을 생성한다 (i 값이 0 이면 첫번째)
				row = sheet.createRow(i);
				for(int z = 0; z<5;z++){
					//생성된 row에 컬럼을 생성한다
					cell = row.createCell(z);
					cell.setCellValue("testvalue" + i);


				}
			}

			FileOutputStream fileoutputstream = new FileOutputStream("/Users/achee7059/Desktop/testData3.xls");
			workbook.write(fileoutputstream);
			fileoutputstream.close();
			workbook.close();
			System.out.println("엑셀파일 생성 성공");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
