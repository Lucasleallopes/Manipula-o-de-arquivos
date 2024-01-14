package fag.com.testes;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

public class Teste_CellValorInt {
	 @Test
	    public void testGetCellValorInt_NumeroNumerico() {
	        try (// Caso de teste: vai verificar se o método retorna o valor numérico de uma célula numérica.
			Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet();
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0);
				cell.setCellValue(42.0); // Configurando a célula com um valor numérico
				int valor = Novo_Manipulacao_MegaSena.getCellValorInt(cell);
				assertEquals(42, valor);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @Test
	    public void testGetCellValorInt_NumeroString() {
	        try (// Caso de teste: vai verificar se o método converte corretamente uma célula de string em um valor inteiro.
			Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet();
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0);
				cell.setCellValue("7"); // Configurando a célula com um valor de string "7"
				int valor = Novo_Manipulacao_MegaSena.getCellValorInt(cell);
				assertEquals(7, valor);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @Test
	    public void testGetCellValorInt_CelulaNula() {
	        // Caso de teste: vai verificar se o método retorna 0 quando a célula é nula.
	        Cell cell = null; // Célula nula
	        int valor = Novo_Manipulacao_MegaSena.getCellValorInt(cell);
	        assertEquals(0, valor);
	    }

	    @Test
	    public void testGetCellValorInt_TipoNaoSuportado() { 
	        try (
	        // Caso de teste: vai verificar se o método retorna 0 quando a célula não é numérica nem string.		
			Workbook workbook = new XSSFWorkbook()) { 
				Sheet sheet = workbook.createSheet();
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0);
				cell.setCellType(CellType.BOOLEAN); // Configurando a célula com um tipo não suportado
				int valor = Novo_Manipulacao_MegaSena.getCellValorInt(cell);
				assertEquals(0, valor);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
