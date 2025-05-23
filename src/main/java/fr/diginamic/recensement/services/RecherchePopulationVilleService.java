package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.exceptions.VilleIntrouvableException;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {

	@Override
	public boolean traiter(Recensement rec, Scanner scanner) {

		System.out.println("Quel est le nom de la ville recherchée ? ");
		String choix = scanner.nextLine();

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getNom().equalsIgnoreCase(choix)
					|| ville.getNom().toLowerCase().startsWith(choix.toLowerCase())) {
				System.out.println(ville);
				return true;
			}
		}
		throw new VilleIntrouvableException("Cette ville n'existe pas");
    }

}
