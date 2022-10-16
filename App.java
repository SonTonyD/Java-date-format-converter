import java.io.File; // Import the File class

import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class App {

	public static void main(String[] args) {
		String inputFilePath = "InputData.txt";
		String outputFilePath = "MyData.txt";
		convertDate(inputFilePath, outputFilePath);
	}

	public static void convertDate(String inputFilePath, String outputFilePath) {
		LinkedList<MyData> listDate = new LinkedList<MyData>();
		try {
			File myObj = new File(inputFilePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				listDate.add(initializeData(data));

			}
			myReader.close();
			sortData(listDate);
			removeDuplicates(listDate);
			System.out.println(listDate);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter(outputFilePath);
			
			for (MyData date : listDate) {
				myWriter.write(date.toString()+"\n");
			}
			
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static MyData initializeData(String date) {
		MyData myDate = new MyData(0, 0, 0, "Monday");
		Pattern patternDash = Pattern.compile("-", Pattern.CASE_INSENSITIVE);
		Pattern patternSlash = Pattern.compile("/", Pattern.CASE_INSENSITIVE);
		Pattern patternComma = Pattern.compile(",", Pattern.CASE_INSENSITIVE);

		Matcher matcherDash = patternDash.matcher(date);
		Matcher matcherSlash = patternSlash.matcher(date);
		Matcher matcherComma1 = patternComma.matcher(date.substring(0, 14));
		Matcher matcherComma2 = patternComma.matcher(date.substring(14));

		boolean matchDash = matcherDash.find();
		boolean matchSlash = matcherSlash.find();
		boolean matchComma1 = matcherComma1.find();
		boolean matchComma2 = matcherComma2.find();
		boolean matchStartNumber = Character.isDigit(date.charAt(0));

		if (matchDash) {
			// System.out.println("Format 1 found");
			parseDateFormat1(date, myDate);
		}
		if (matchSlash && matchStartNumber) {
			// System.out.println("Format 2 found");
			parseDateFormat2(date, myDate);
		}
		if (matchComma1 && matchComma2) {
			// System.out.println("Format 3 found");
			parseDateFormat3(date, myDate);
		}
		if (matchComma1 && !matchComma2) {
			// System.out.println("Format 4 found");
			parseDateFormat4(date, myDate);
		}
		if (matchSlash && !matchStartNumber) {
			// System.out.println("Format 5 found");
			parseDateFormat5(date, myDate);
		}
		return myDate;
	}

	public static void parseDateFormat1(String str, MyData myData) {
		String[] value = str.split("-");
		myData.setYear(Integer.parseInt(value[0]));
		myData.setMonth(Integer.parseInt(value[1]));

		String[] subValue = value[2].split(" ");
		myData.setDay(Integer.parseInt(subValue[0]));
		myData.setWeekday(subValue[1]);
	}

	public static void parseDateFormat2(String str, MyData myData) {
		String[] value = str.split("/");
		myData.setDay(Integer.parseInt(value[0]));
		myData.setMonth(Integer.parseInt(value[1]));

		String[] subValue = value[2].split(" ");
		myData.setYear(Integer.parseInt(subValue[0]));
		myData.setWeekday(subValue[1]);
	}

	public static void parseDateFormat3(String str, MyData myData) {

		String[] value = str.split(",");
		myData.setWeekday(value[0]);
		value[2] = value[2].replaceAll("\\s+", "");
		myData.setYear(Integer.parseInt(value[2]));

		String[] subValue = value[1].split(" ");
		myData.setMonth(Integer.parseInt(convertMonthToNumber(subValue[1])));
		myData.setDay(Integer.parseInt(subValue[2]));
	}

	public static void parseDateFormat4(String str, MyData myData) {

		String[] value = str.split(",");

		String[] subValue1 = value[0].split(" ");
		String[] subValue2 = value[1].split(" ");

		myData.setMonth(Integer.parseInt(convertMonthToNumber(subValue1[0])));
		myData.setDay(Integer.parseInt(subValue1[1]));
		myData.setYear(Integer.parseInt(subValue2[1]));
		myData.setWeekday(subValue2[2]);
	}

	public static void parseDateFormat5(String str, MyData myData) {

		String[] value = str.split(" ");

		myData.setWeekday(value[0]);
		String[] subValue = value[1].split("/");
		myData.setDay(Integer.parseInt(subValue[0]));
		myData.setMonth(Integer.parseInt(subValue[1]));
		myData.setYear(Integer.parseInt(subValue[2]));
	}

	public static String convertMonthToNumber(String month) {
		switch (month) {
		case "January":
			return "01";
		case "February":
			return "02";
		case "March":
			return "03";
		case "April":
			return "04";
		case "May":
			return "05";
		case "June":
			return "06";
		case "July":
			return "07";
		case "August":
			return "08";
		case "September":
			return "09";
		case "October":
			return "10";
		case "November":
			return "11";
		case "December":
			return "12";
		default:
			return "error";
		}
	}

	public static void sortData(LinkedList<MyData> listDate) {
		int operationNumber = -1;
		while (operationNumber != 0) {
			operationNumber = 0;
			for (int i = 0; i < listDate.size() - 1; i++) {
				MyData currentElement = listDate.get(i);
				MyData nextElement = listDate.get(i + 1);

				if (currentElement.getYear() == nextElement.getYear()) {
					if (currentElement.getMonth() == nextElement.getMonth()) {
						if (currentElement.getDay() > nextElement.getDay()) {
							swap(listDate, currentElement, nextElement);
							operationNumber++;
						}
					} else if (currentElement.getMonth() > nextElement.getMonth()) {
						swap(listDate, currentElement, nextElement);
						operationNumber++;
					}
				} else if (currentElement.getYear() > nextElement.getYear()) {
					swap(listDate, currentElement, nextElement);
					operationNumber++;
				}

			}
		}
	}

	public static void swap(LinkedList<MyData> list, MyData ele1, MyData ele2) {

		// Getting the positions of the elements
		int index1 = list.indexOf(ele1);
		int index2 = list.indexOf(ele2);

		// Returning if the element is not present in the
		// LinkedList
		if (index1 == -1 || index2 == -1) {
			return;
		}

		// Swapping the elements
		list.set(index1, ele2);
		list.set(index2, ele1);
	}

	public static void removeDuplicates(LinkedList<MyData> listDate) {
		for (int i = 0; i < listDate.size(); i++) {
			for (int j = 0; j < listDate.size(); j++) {
				if (listDate.get(i).getYear() == listDate.get(j).getYear()
						&& listDate.get(i).getMonth() == listDate.get(j).getMonth()
						&& listDate.get(i).getDay() == listDate.get(j).getDay() && i != j) {
					listDate.remove(i);
				}
			}
		}
	}
}
