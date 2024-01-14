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

public class Teste_CellValorDouble {
	@Test
    public void testGetCellValorDouble_NumeroNumerico() {
        try ( // Caso de teste: vai verificar se o método retorna o valor numérico de uma célula numérica.
		Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet();
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue(42.5); // Configurando a célula com um valor numérico
			double valor = Novo_Manipulacao_MegaSena.getCellValorDouble(cell);
			assertEquals(42.5, valor, 0.001);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testGetCellValorDouble_NumeroString() {
        try ( // Caso de teste: vai verificar se o método converte corretamente uma célula de string em um valor double.
		Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet();
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("7.75"); // Configurando a célula com um valor de string "7.75"
			double valor = Novo_Manipulacao_MegaSena.getCellValorDouble(cell);
			assertEquals(7.75 , valor / 100, 0.001);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testGetCellValorDouble_CelulaNula() {
        // Caso de teste: vai verificar se o método retorna 0.0 quando a célula é nula.
        Cell cell = null;
        double valor = Novo_Manipulacao_MegaSena.getCellValorDouble(cell);
        assertEquals(0.0, valor, 0.001);
    }

    @Test
    public void testGetCellValorDouble_TipoNaoSuportado() {
        try ( // Caso de teste: vai verificar se o método retorna 0.0 quando a célula não é numérica nem string.
		Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet();
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellType(CellType.BOOLEAN); // Configurando a célula com um tipo não suportado
			double valor = Novo_Manipulacao_MegaSena.getCellValorDouble(cell);
			assertEquals(0.0, valor, 0.001);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
