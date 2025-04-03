package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.exceptions.RecensementException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public boolean traiter(Recensement rec, Scanner scanner) {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		// Permet de vérifier si le code du département existe
		boolean codeDepartementExiste = false;
		for (Ville ville : rec.getVilles()) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				codeDepartementExiste = true;
				break;
			}
		}

		// Si le code renseigné n'existe pas renvoie un message
		if (!codeDepartementExiste) {
			throw new RecensementException("Entrez un département valide");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new RecensementException("Le Min doit être un nombre");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMax)) {
			throw new RecensementException("Le Max doit être un nombre");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		if (min < 0 || max < 0 || min > max) {
			throw new RecensementException("Le champs doit être positif à zéro et le Max doit être supérieur à Min");
		}

		// Définir la variable à false de base
		boolean villeExiste = false;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
					// Ajoute l'état true à la ville existante trouvée
					villeExiste = true;
				}
			}
		}
		// Si aucune ville n'est trouvée renvoie un message si rien n'a été trouvé
		if (!villeExiste) {
			throw new RecensementException("Rien n'a été trouvé selon vos critères de recherche");
		}
		return true;
	}

}
