package fag.com.testes;

import org.apache.poi.ss.usermodel.*;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.text.NumberFormat;

public class Novo_Manipulacao_MegaSena {

	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream(
					"C:\\Users\\lucas\\eclipse-workspace\\ManipulacaoArquivos\\src\\main\\java\\fag\\com\\mega-sena\\Mega-Sena2.xlsx");
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);

			Map<Integer, Integer> frequenciaNumeros = new HashMap<>();
			boolean dezenasSorteadas = false;
			boolean dezenasSorteioAleatorio = false;
			int semGanhadores = 0;
			double minimoPago_4num = Double.MAX_VALUE;
			double minimoPago_5num = Double.MAX_VALUE;
			double minimoPago_6num = Double.MAX_VALUE;
			double maximoPago_4num = 0;
			double maximoPago_5num = 0;
			double maximoPago_6num = 0;
			int Total_Ganhadores4numeros = 0;
			int Total_Ganhadores5numeros = 0;
			int Total_Ganhadores6numeros = 0;

			Scanner scanner = new Scanner(System.in);
			System.out.println("Insira 6 dezenas (separadas por espaço):");
			String input = scanner.nextLine();
			String[] NumerosUsuario = input.split(" ");

			if (NumerosUsuario.length != 6) {
				System.out.println("Por favor, insira exatamente 6 dezenas.");
			} else {
				boolean entradaValida = true;

				for (String numeroStr : NumerosUsuario) {
					int numero = Integer.parseInt(numeroStr);
					if (numero < 1 || numero > 60) {
						System.out.println("Por favor, insira números entre 1 e 60.");
						entradaValida = false;
						break;
					}
				}

				if (entradaValida) {
					int[] numeroUsuario_Array = new int[6];
					int[] sorteioAleatorio = gerarSorteioAleatorio();

					for (int i = 0; i < 6; i++) {
						numeroUsuario_Array[i] = Integer.parseInt(NumerosUsuario[i]);
					}

					System.out.println(" ");
					System.out.println("Dezenas sorteadas aleatoriamente:");
					for (int i = 0; i < sorteioAleatorio.length; i++) {
						System.out.print(sorteioAleatorio[i] + " ");
					}
					System.out.println(" ");
					scanner.close();

					for (Row row : sheet) {
						if (row.getRowNum() == 0)
							continue; // Ignora o cabeçalho

						int[] dezenasConcurso = new int[6];

						for (int i = 2; i <= 7; i++) {
							Cell cell = row.getCell(i);
							if (cell != null) {
								int bola = getCellValorInt(cell);
								dezenasConcurso[i - 2] = bola;
								atualizarFrequencia(frequenciaNumeros, bola);
							}
						}

						if (Verificador(numeroUsuario_Array, dezenasConcurso)) {
							dezenasSorteadas = true;
							System.out.println("\nAs dezenas escolhidas foram sorteadas no \nconcurso #"
									+ row.getCell(0).getNumericCellValue() + " em "
									+ row.getCell(1).getStringCellValue());
						}

						if (Verificador(sorteioAleatorio, dezenasConcurso)) {
							dezenasSorteioAleatorio = true;
							System.out.println("\nAs dezenas do sorteio aleatório já ocorreram antes no \nconcurso #"
									+ row.getCell(0).getNumericCellValue() + " em "
									+ row.getCell(1).getStringCellValue());
						}

						int Ganhadores6numeros = getCellValorInt(row.getCell(8));
						int Ganhadores5numeros = getCellValorInt(row.getCell(11));
						int Ganhadores4numeros = getCellValorInt(row.getCell(13));

						double Pago_6num = getCellValorDouble(row.getCell(15));
						double Pago_5num = getCellValorDouble(row.getCell(12));
						double Pago_4num = getCellValorDouble(row.getCell(14));

						minimoPago_4num = Math.min(minimoPago_4num, Pago_4num);
						minimoPago_5num = Math.min(minimoPago_5num, Pago_5num);
						minimoPago_6num = Math.min(minimoPago_6num, Pago_6num);

						maximoPago_4num = Math.max(maximoPago_4num, Pago_4num);
						maximoPago_5num = Math.max(maximoPago_5num, Pago_5num);
						maximoPago_6num = Math.max(maximoPago_6num, Pago_6num);

						Total_Ganhadores4numeros += Ganhadores4numeros;
						Total_Ganhadores5numeros += Ganhadores5numeros;
						Total_Ganhadores6numeros += Ganhadores6numeros;

						if (Ganhadores6numeros == 0) {
							semGanhadores++;
						}
					}

					if (!dezenasSorteadas) {
						System.out.println("\nAs dezenas digitadas ainda não ocorreram! \n");
					}

					if (!dezenasSorteioAleatorio) {
						System.out.println("\nAs dezenas do sorteio aleatório ainda não ocorreram! \n");
					}

					System.out.println("\nFrequência dos números:");
					for (Map.Entry<Integer, Integer> entry : frequenciaNumeros.entrySet()) {
						System.out.println("|Número " + entry.getKey() + ": " + entry.getValue() + " vezes");
					}
					
					NumberFormat formato = NumberFormat.getNumberInstance();
					String QuantidadeTotal_4 = formato.format(Total_Ganhadores4numeros);
					String QuantidadeTotal_5 = formato.format(Total_Ganhadores5numeros);
					String QuantidadeTotal_6 = formato.format(Total_Ganhadores6numeros);
					
					System.out.println("");
					System.out.println("|Quantidade de concursos sem ganhador das 6 dezenas: " + semGanhadores + "\n");
					System.out.println("|Menor valor pago para apostas com 4 dezenas: " + (minimoPago_4num) / 100);
					System.out.println("|Menor valor pago para apostas com 5 dezenas: " + (minimoPago_5num) / 100);
					System.out.println("|Menor valor pago para apostas com 6 dezenas: " + (minimoPago_6num) / 100 + "\n");
					System.out.println("|Maior valor pago para apostas com 4 dezenas: " + (maximoPago_4num) / 100);
					System.out.println("|Maior valor pago para apostas com 5 dezenas: " + (maximoPago_5num) / 100);
					System.out.println("|Maior valor pago para apostas com 6 dezenas: " + (maximoPago_6num) / 100 + "\n");
					System.out.println("|Quantidade de ganhadores com 4 dezenas em todos os concursos: "+ QuantidadeTotal_4);
					System.out.println("|Quantidade de ganhadores com 5 dezenas em todos os concursos: "+ QuantidadeTotal_5);
					System.out.println("|Quantidade de ganhadores com 6 dezenas em todos os concursos: "+ QuantidadeTotal_6);
				}
			}

			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void atualizarFrequencia(Map<Integer, Integer> frequenciaNumeros, int numero) {
		if (frequenciaNumeros.containsKey(numero)) {
			frequenciaNumeros.put(numero, frequenciaNumeros.get(numero) + 1);
		} else {
			frequenciaNumeros.put(numero, 1);
		}
	}

	static int getCellValorInt(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == CellType.NUMERIC) {
				return (int) cell.getNumericCellValue();
			} else if (cell.getCellType() == CellType.STRING) {
				return Integer.parseInt(cell.getStringCellValue());
			}
		}
		return 0;
	}

	static double getCellValorDouble(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == CellType.NUMERIC) {
				return cell.getNumericCellValue();
			} else if (cell.getCellType() == CellType.STRING) {
				String cellValue = cell.getStringCellValue().replaceAll("[^\\d.,]", "");
				cellValue = cellValue.replace(",", "");
				cellValue = cellValue.replace(".", "");
				cellValue = cellValue.replace(",", ".");
				return Double.parseDouble(cellValue);
			}
		}
		return 0.0;
	}

	static boolean Verificador(int[] NumerosUsuario, int[] NumerosChecar) {
		return Arrays.equals(NumerosUsuario, NumerosChecar);
	}

	static int[] gerarSorteioAleatorio() {
		int[] sorteio = new int[6];
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sorteio[i] = random.nextInt(60) + 1;
		}
		return sorteio;
	}
}
