package CurrencyRates;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static Scanner reader = new Scanner(System.in);
	static int meniu;
	private static String date1 = "";
	private static String date2 = "";
	private static String inputCcy;
	static LocalDate dateToday = LocalDate.now();
	static List<String> listOfCcy;

	public static void main(String[] args) throws IOException, ParseException {
		NumberFormat currencyDifferenceFormatter = new DecimalFormat("#0.0000");
		mainInput();

		switch (meniu) {

		case 0:
			System.out.println("Programa išjungta!");
			System.exit(1);
			break;
		case 1:
			while (true) {
				inputDate();
				boolean loop1 = true;

				inputCcy = reader.nextLine();
				inputCcy();
				do {
					FindCurrencyAmt ccy1 = new FindCurrencyAmt(date1, inputCcy);
					int reponseCode = ccy1.getResponeCode();
					System.out.println("užklausos serverio atsakymas: " + reponseCode + "\n");
					if (ccy1.getAmt() != 0) {
						if (date1.equals(ccy1.getCcyDate()) || (date1 == ccy1.getCcyDate())) {
							System.out.println(
									" ----------------------------------------------------------------------------------------------------------------------");
							System.out.println(" | Duomenys šios datos - " + ccy1.getCcyDate()
									+ ":                                                                                  |");

						} else {
							System.out.println(
									" ----------------------------------------------------------------------------------------------------------------------");
							System.out.println(" | Jūsų nurodytai datai: " + date1
									+ " duomenų nėra (Savaitgalis, švenčių diena arba dar šios dienos duomenys nepateikti."
									+ "|");

							System.out.println(" | Yra vėliausi duomenys šiai datai - " + ccy1.getCcyDate() + " : "
									+ "                                                                   |");
						}
						System.out.println(" | Valiutos: " + inputCcy + " kursas: " + ccy1.getAmt() + " už vieną eurą."
								+ "                                                                        |");
						System.out.println(
								" ---------------------------------------------------------------------------------------------------------------------- \n");
						loop1 = false;

					} else {
						LocalDate date3 = LocalDate.parse(date1);
						LocalDate date4 = LocalDate.parse("2014-12-29");
						if (date3.isBefore(date4)) {
							System.out.println("Įvestai datai: " + date1
									+ " nėra duomenų arba klaidingas valiutos kodas. Galimi valiutų kodai:");
							outputCcyListFromXml(ccy1);
							inputDate();
							inputCcy = reader.nextLine();
							inputCcy();
						} else {
							System.out.println("Klaidingas valiutos kodas! Galimi valiutų kodai:");
							outputCcyListFromXml(ccy1);
							inputCcy();
						}
					}

				} while (loop1);
			}
		case 2:

			while (true) {
				inputDate();
				areFirstDateEqualToday(date1);

				inputDateTo();
				if (datesNotEqual(date1, date2) == false) {
					System.out.println("Įvestos vienodos datos, arba antra data mažesnė už pirmą!!!");
					inputDateTo();
				}
				boolean loop1 = true;
				inputCcy = reader.nextLine();
				inputCcy();
				do {
					FindCurrencyAmt ccy1 = new FindCurrencyAmt(date1, inputCcy);
					String dateOfFirstXml = ccy1.getCcyDate();
					int reponseCode1 = ccy1.getResponeCode();
					double amtOfFirstRequest = ccy1.getAmt();

					FindCurrencyAmt ccy2 = new FindCurrencyAmt(date2, inputCcy);
					String dateOfSecondXml = ccy2.getCcyDate();
					double amtOfSecondRequest = ccy2.getAmt();
					int reponseCode2 = ccy2.getResponeCode();

					System.out.println("Pirmos užklausos serverio atsakymas: " + reponseCode1);
					System.out.println("Antros užklausos serverio atsakymas: " + reponseCode2 + "\n");

					if (amtOfFirstRequest != 0.0) {
						if ((!dateOfFirstXml.equals(dateOfSecondXml)) == true) {
							if (amtOfSecondRequest != 0.0) {
								if ((date1.equals(dateOfSecondXml) || (date1 == dateOfSecondXml))
										&& ((date2.equals(dateOfFirstXml) || (date2 == dateOfFirstXml)))) {
									System.out.println(
											" -----------------------------------------------------------------------------------------------------------------------------------------");
									System.out.println(" | Duomenys šio intervalo: " + dateOfFirstXml + " - "
											+ dateOfSecondXml
											+ ":                                                                    |");

								} else {
									System.out.println(
											" -----------------------------------------------------------------------------------------------------------------------------------------");
									System.out.println(" | Jūsų nurodytam intervalui: " + date1 + " - " + date2
											+ " duomenų nėra (Savaitgalis, švenčių diena arba dar šios dienos duomenys nepateikti. |");

									System.out.println(" | Yra artimiausi duomenys šiam intervalui: " + dateOfFirstXml
											+ " - " + dateOfSecondXml
											+ " :                                                                    |");
								}
								System.out.println(" | Valiutos - " + inputCcy + " kursai: " + dateOfFirstXml + ": "
										+ amtOfFirstRequest + "  " + dateOfSecondXml + ": " + amtOfSecondRequest
										+ ".                                                                         |");
								if (amtOfFirstRequest > amtOfSecondRequest) {

									System.out.println(" | Valiutos kursas sumažėjo : -"
											+ currencyDifferenceFormatter.format(amtOfFirstRequest - amtOfSecondRequest)
											+ " " + inputCcy
											+ " Euro atžvilgiu.                                                                                |");
								} else if (amtOfFirstRequest < amtOfSecondRequest) {
									System.out.println(" | Valiutos kursas padidėjo: "
											+ currencyDifferenceFormatter.format(amtOfSecondRequest - amtOfFirstRequest)
											+ " " + inputCcy
											+ " Euro atžvilgiu.                                                                                  |");
								} else {
									System.out.println(" | Valiutos kursas liko nepakitęs ir yra lygus: "
											+ amtOfFirstRequest + " " + inputCcy
											+ " Euro atžvilgiu.                                                                |");
								}
								System.out.println(
										" ----------------------------------------------------------------------------------------------------------------------------------------- \n");
								loop1 = false;
							} else {
								System.out.println("Klaidingas valiutos kodas! Galimi valiutų kodai:");
								outputCcyListFromXml(ccy1);
								inputCcy();
							}
						} else {
							System.out.println("Jūsų nurodytam intervalui: " + date1 + " - " + date2
									+ " duomenų nėra (Savaitgalis arba švenčių diena). Įveskite didesnę antrą datą.");
							inputDateTo();
						}

					} else {

						LocalDate date4 = LocalDate.parse(date1);
						LocalDate date5 = LocalDate.parse("2014-12-29");
						if (date4.isBefore(date5)) {
							System.out.println("Įvestam intervalui: " + date1 + " - " + date2
									+ " nėra duomenų arba klaidingas valiutos kodas.  Galimi valiutų kodai:");
							outputCcyListFromXml(ccy1);	
							inputDate();
							inputDateTo();
							inputCcy = reader.nextLine();
							inputCcy();
						} else {
							System.out.println("Klaidingas valiutos kodas. Galimi valiutų kodai:");
							outputCcyListFromXml(ccy1);	
							inputCcy();
						}
					}

				} while (loop1);
			}
		default:
			System.out.println("Nėra šio pasirinkimo!");
			mainInput();
		}
	}

	private static void outputCcyListFromXml(FindCurrencyAmt ccy1) {
		listOfCcy = ccy1.getCcyList();

		for (int i = 0; i < listOfCcy.size(); i++) {
			if (i != listOfCcy.size() - 1) {
				System.out.print(listOfCcy.get(i) + ", ");
				if (i == 39) {
					System.out.print("\n");
				}
			} else {
				System.out.print(listOfCcy.get(i) + ". \n");
			}
		}							
	}

	private static void areFirstDateEqualToday(String date12) throws IOException, ParseException {
		if (datesNotEqual(date12) == true) {
			System.out.println(
					"Įvesta pirmoji data lygi šiandien " + dateToday + " dienai! Įveskite senesnę pirmą datą.");
			inputDate();
			areFirstDateEqualToday(date1);
		}
	}

	private static boolean datesNotEqual(String date) {
		LocalDate date1 = LocalDate.parse(date);
		if ((date1.isEqual(dateToday) == true)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean datesNotEqual(String date12, String date22) throws ParseException {

		LocalDate date1 = LocalDate.parse(date12);
		LocalDate date2 = LocalDate.parse(date22);

		if ((date1.isAfter(date2) == true) || (date1.isEqual(date2) == true)) {
			return false;
		} else {
			return true;
		}
	}

	private static void backToThePreviousMenu(String value) throws IOException, ParseException {
		if ((value == "-1") || (value.equals("-1"))) {
			main(null);
		}
	}

	private static int mainInput() {
		boolean loop = true;
		do {
			System.out.println(
					"Įveskite pasirinkimą:  1 - Valiutų kursai nurodytai datai, 2 - Valiutų kursai nurodytam periodui.    0 - Programos išjungimas. Kai parašote skaičių - spauskite ENTER. ");
			try {
				meniu = reader.nextInt();
				loop = false;

			} catch (InputMismatchException a) {
				System.out.print("Įvesti ne sakaičiai! \n");

				reader.next();
			}
		} while (loop);
		return meniu;
	}

	private static String inputDate() throws IOException, ParseException {
		boolean loop = true;
		do {
			System.out.println(
					"Įveskite datą, pvz: 2015-01-01. Jei norite grįžti į ankstesnį meniu rašykite -1 ir spauskite Enter.");

			date1 = reader.next();
			backToThePreviousMenu(date1);

			if (date1.length() == 10) {
				if (isValidDate(date1) == true) {
					LocalDate date3 = LocalDate.parse(date1);
					LocalDate date4 = LocalDate.parse("2014-09-29");

					if (dateToday.isEqual(date3)) {
						if (date3.isAfter(date4)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra senesnė nei 2014-09-30. Yra duomenys nuo 2014-09-30.");
						}
					} else {
						if (date3.isAfter(date4)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra senesnė nei 2014-09-30. Yra duomenys nuo 2014-09-30.");
							continue;
						}
						if (date3.isBefore(dateToday)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra didesnė už šiandienos - " + dateToday + " datą.");
							loop = true;
						}
					}
				} else {
					System.out.println("Blogas datos formatas!!");
					loop = true;
				}
			} else {
				System.out.println("Blogas datos ilgis!");
				date1 = "";
			}
		} while (loop);

		return date1;
	}

	private static String inputDateTo() throws IOException, ParseException {
		boolean loop = true;
		do {
			System.out.println("Įveskite galine intervalo datą, pvz: " + dateToday
					+ ". Jei norite grįžti į ankstesnį meniu rašykite -1 ir spauskite Enter.");

			date2 = reader.next();
			backToThePreviousMenu(date2);
			if (date2.length() == 10) {

				if (isValidDate(date2) == true) {
					LocalDate date3 = LocalDate.parse(date2);
					LocalDate date4 = LocalDate.parse("2014-09-29");

					if (dateToday.isEqual(date3)) {
						if (date3.isAfter(date4)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra senesnė nei 2014-09-30. Yra duomenys nuo 2014-09-30.");
						}
					} else {

						if (date3.isAfter(date4)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra senesnė nei 2014-09-30. Yra duomenys nuo 2014-09-30.");
						}
						if (dateToday.isAfter(date3)) {
							loop = false;
						} else {
							System.out.println("Įvesta data yra didesnė už šiandienos - " + dateToday + " datą.");
							loop = true;
						}
					}
				} else {
					System.out.println("Blogas datos formatas!!");
					loop = true;
				}

			} else {
				System.out.println("Blogas datos ilgis!");
				loop = true;
			}

		} while (loop);

		return date2;
	}

	private static boolean isValidDate(String d) {
		String regex = "^([1-2]{1}[0-9]{3}[-]?((0[13-9]|1[012])[-]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-]?31|02[-]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-]?02[-/]?29)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) d);
		return matcher.matches();
	}

	private static String inputCcy() throws IOException, ParseException {

		boolean loop = true;
		do {
			System.out.println(
					"Įveskite valiutos kodą iš trijų raidžių.  Jei norite grįžti į ankstesnį meniu rašykite -1 ir spauskite Enter.");
			inputCcy = reader.nextLine().toUpperCase();
			backToThePreviousMenu(inputCcy);
			try {
				if (isValidCcy(inputCcy) == true) {

					loop = false;
				} else {
					System.out.println("Blogas valiutos formatas!!");
					loop = true;
				}
			} catch (Throwable e) {
				System.out.println("Blogas valiutos formatas  !");
				reader.next();
			}
		} while (loop);
		return inputCcy;
	}

	private static boolean isValidCcy(String ccy) {
		String regex = "[A-Z]{3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) ccy);
		return matcher.matches();
	}

}
