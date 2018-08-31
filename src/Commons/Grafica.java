package Commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import Dao.Squadra;
import Views.Master;

public class Grafica {

	/**
	 * Visualizzazione grafica di ogni squadra
	 *
	 * @param squadre
	 */
	public static void inferfaccia(ArrayList<Squadra> squadre, String path) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		while (true) {
			try {
				Utils.CLS();
				File fileStatistiche = new File(Utils.connectionFile(path, Costanti.FILE_STATISTICHE));
				File fileVoti = new File(Utils.connectionFiles(path, Costanti.FILE_VOTI)
						.get(Utils.connectionFiles(path, Costanti.FILE_VOTI).size() - 1));
				File fileQuotazioni = new File(Utils.connectionFile(path, Costanti.FILE_QUOTAZIONI));

				int index = 0;
				for (Squadra squadra : squadre) {
					System.out.println(index + " - " + squadra.getNome());
					index++;
				}
				System.out.println("S - Scarica file Statistiche (" + fileStatistiche.getName() + " "
						+ sdf.format(fileStatistiche.lastModified()) + ")");
				System.out.println("V - Scarica file Voti (" + fileVoti.getName() + " "
						+ sdf.format(fileVoti.lastModified()) + ")");
				System.out.println("Q - Scarica file Quotazioni (" + fileQuotazioni.getName() + " "
						+ sdf.format(fileQuotazioni.lastModified()) + ")");
				System.out.println("R - Ricarica tutto");
				System.out.println("E - Exit");

				System.out.print("Inserire un valore per proseguire: ");
				String input = br.readLine();

				Utils.CLS();

				if (StringUtils.isNumeric(input)) {
					System.out.println(Utils.printRosa(squadre.get(Integer.parseInt(input)))); // stampa
																								// rosa
																								// completa
					// System.out.println(Utils.printTutteFormazioni(squadre.get(Integer.parseInt(input))));
					// //stampa tutte le formazioni ordinate
					System.out.println("Premere invio per continuare");
					br.readLine();
				} else if (input.toUpperCase().equals("S")) {
					java.awt.Desktop.getDesktop().browse(new URI(Costanti.URL_STATISTICHE));
				} else if (input.toUpperCase().equals("V")) {
					java.awt.Desktop.getDesktop().browse(new URI(Costanti.URL_VOTI));
				} else if (input.toUpperCase().equals("Q")) {
					java.awt.Desktop.getDesktop().browse(new URI(Costanti.URL_QUOTAZIONI));
				} else if (input.toUpperCase().equals("R")) {
					Master.caricaInfo();
				} else if (input.toUpperCase().equals("E")) {
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("Valore non ammesso.");
			}
		}
	}
}
