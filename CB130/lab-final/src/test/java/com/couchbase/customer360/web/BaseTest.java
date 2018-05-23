package com.couchbase.customer360.web;

import static java.lang.String.valueOf;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.couchbase.customer360.Application;

public abstract class BaseTest {
	public final static String BASE_PATH = "http://localhost:4567/customer360";
	public final static Random RANDOM = new Random();
	private final static String[] ARGS = {};

	protected BaseTest() {
		Application.main(ARGS);
	}

	protected String randomStreetAddress() {
		return randomNumberAsString(9999) + " " + randomString(STREET_NAMES) + 
			" " + randomString(ROAD_TYPES);
	}

	protected String randomState() {
		return randomString(STATES);
	}

	protected String randomCity() {
		return randomString(CITY_NAMES);
	}

	protected String randomEmailAddress(String prefix) {
		return prefix + randomNumberAsString(9999) + "@" + randomDomainName();
	}

	protected String randomPhoneNumber() {
		return String.format("%s-%s-%s", randomNumberAsString(999), 
			randomNumberAsString(999), randomNumberAsString(9999));
	}

	protected String randomNumberAsString(int max) {
		return StringUtils.leftPad(valueOf(RANDOM.nextInt(max+1)), 
			valueOf(max).length(), "0");
	}

	protected String randomDomainName() {
		return randomString(DOMAIN_NAMES);
	}

	protected String randomFirstName() {
		return randomString(FIRST_NAMES);
	}

	protected String randomLastName() {
		return randomString(LAST_NAMES);
	}

	private String randomString(String[] array) {
		return array[RANDOM.nextInt(array.length)];
	}

	private final static String[] DOMAIN_NAMES = { "aol.com", "att.net",
		"comcast.net", "facebook.com", "gmail.com", "gmx.com",
		"googlemail.com", "google.com", "hotmail.com", "hotmail.co.uk",
		"mac.com", "me.com", "mail.com", "msn.com", "live.com",
		"sbcglobal.net", "verizon.net", "yahoo.com", "yahoo.co.uk",
		"bellsouth.net", "charter.net", "cox.net", "earthlink.net",
		"juno.com", "sina.com", "qq.com", "naver.com", "hanmail.net",
		"daum.net", "nate.com", "yahoo.co.jp", "yahoo.co.kr",
		"yahoo.co.id", "yahoo.co.in", "yahoo.com.sg", "yahoo.com.ph",
		"btinternet.com", "virginmedia.com", "blueyonder.co.uk",
		"freeserve.co.uk", "live.co.uk", "ntlworld.com", "o2.co.uk",
		"orange.net", "sky.com", "talktalk.co.uk", "tiscali.co.uk",
		"virgin.net", "wanadoo.co.uk", "bt.com", "hotmail.fr", "live.fr",
		"laposte.net", "yahoo.fr", "wanadoo.fr", "orange.fr", "gmx.fr",
		"sfr.fr", "neuf.fr", "free.fr", "gmx.de", "hotmail.de", "live.de",
		"online.de", "t-online.de", "web.de", "yahoo.de", "mail.ru",
		"rambler.ru", "yandex.ru", "ya.ru", "list.ru", "hotmail.be",
		"live.be", "skynet.be", "voo.be", "tvcablenet.be", "telenet.be",
		"hotmail.com.ar", "live.com.ar", "yahoo.com.ar", "fibertel.com.ar",
		"speedy.com.ar", "arnet.com.ar", "yahoo.com.mx", "live.com.mx",
		"hotmail.es", "hotmail.com.mx", "prodigy.net.mx"
	};

	private final static String[] FIRST_NAMES = { "Jacob", "Mason", "William",
		"Jayden", "Noah", "Michael", "Ethan", "Alexander", "Aiden",
		"Daniel", "Anthony", "Matthew", "Elijah", "Joshua", "Liam",
		"Andrew", "James", "David", "Benjamin", "Logan", "Christopher",
		"Joseph", "Jackson", "Gabriel", "Ryan", "Samuel", "John", "Nathan",
		"Lucas", "Christian", "Jonathan", "Caleb", "Dylan", "Landon",
		"Isaac", "Gavin", "Brayden", "Tyler", "Luke", "Evan", "Carter",
		"Nicholas", "Isaiah", "Owen", "Jack", "Jordan", "Brandon", "Wyatt",
		"Julian", "Aaron", "Jeremiah", "Angel", "Cameron", "Connor",
		"Hunter", "Adrian", "Henry", "Eli", "Justin", "Austin", "Robert",
		"Charles", "Thomas", "Zachary", "Jose", "Levi", "Kevin",
		"Sebastian", "Chase", "Ayden", "Jason", "Ian", "Blake", "Colton",
		"Bentley", "Dominic", "Xavier", "Oliver", "Parker", "Josiah",
		"Adam", "Cooper", "Brody", "Nathaniel", "Carson", "Jaxon",
		"Tristan", "Luis", "Juan", "Hayden", "Carlos", "Jesus", "Nolan",
		"Cole", "Alex", "Max", "Grayson", "Bryson", "Diego", "Jaden",
		"Sophia", "Isabella", "Emma", "Olivia", "Ava", "Emily", "Abigail",
		"Madison", "Mia", "Chloe", "Elizabeth", "Ella", "Addison",
		"Natalie", "Lily", "Grace", "Samantha", "Avery", "Sofia", "Aubrey",
		"Brooklyn", "Lillian", "Victoria", "Evelyn", "Hannah", "Alexis",
		"Charlotte", "Zoey", "Leah", "Amelia", "Zoe", "Hailey", "Layla",
		"Gabriella", "Nevaeh", "Kaylee", "Alyssa", "Anna", "Sarah",
		"Allison", "Savannah", "Ashley", "Audrey", "Taylor", "Brianna",
		"Aaliyah", "Riley", "Camila", "Khloe", "Claire", "Sophie",
		"Arianna", "Peyton", "Harper", "Alexa", "Makayla", "Julia",
		"Kylie", "Kayla", "Bella", "Katherine", "Lauren", "Gianna", "Maya",
		"Sydney", "Serenity", "Kimberly", "Mackenzie", "Autumn", "Jocelyn",
		"Faith", "Lucy", "Stella", "Jasmine", "Morgan", "Alexandra",
		"Trinity", "Molly", "Madelyn", "Scarlett", "Andrea", "Genesis",
		"Eva", "Ariana", "Madeline", "Brooke", "Caroline", "Bailey",
		"Melanie", "Kennedy", "Destiny", "Maria", "Naomi", "London",
		"Payton", "Lydia", "Ellie", "Mariah", "Aubree", "Kaitlyn"
	};

	private final static String[] LAST_NAMES = { "Smith", "Johnson",
		"Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
		"Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris",
		"Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark",
		"Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young",
		"Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green",
		"Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell",
		"Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker",
		"Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
		"Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey",
		"Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward",
		"Torres", "Peterson", "Gray", "Ramirez", "James", "Watson",
		"Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes",
		"Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell",
		"Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
		"Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell",
		"Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham",
		"Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens",
		"Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "Mcdonald",
		"Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells",
		"Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter",
		"Hicks", "Crawford", "Henry", "Boyd", "Mason", "Morales",
		"Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns", "Gordon",
		"Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels",
		"Palmer", "Mills", "Nichols", "Grant", "Knight", "Ferguson",
		"Rose", "Stone", "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer",
		"Gardner", "Stephens", "Payne", "Pierce", "Berry", "Matthews",
		"Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll",
		"Duncan", "Snyder", "Hart", "Cunningham", "Bradley", "Lane",
		"Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong",
		"Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez",
		"Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson"
	};

	private final static String[] STREET_NAMES = { "Park", "Second", "Oak",
		"First", "Third", "Maple", "Pine", "Fourth", "Cedar", "Main",
		"Fifth", "Sunset", "Elm", "Sixth", "Walnut", "Willow", "Lake",
		"Washington", "Seventh", "Hickory", "Dogwood", "Church", "Ridge",
		"Lincoln", "River", "Eighth", "Lakeview", "Highland", "Jackson",
		"Meadow", "Forest", "Cherry", "North", "Johnson", "Railroad",
		"Wilson", "Smith", "Woodland", "Hill", "Ninth", "Spruce", "Tenth",
		"Birch", "Chestnut", "Center", "West", "Adams", "Williams",
		"Jefferson", "Evergreen", "Davis", "Taylor", "Poplar", "Magnolia",
		"Sycamore", "South", "Spring", "Orchard", "Hillcrest", "Valley",
		"Miller", "Lee", "Laurel", "Franklin", "Eleventh", "Ash", "Holly",
		"East", "Clark", "Locust", "Hillside", "Madison", "Twelfth",
		"Brown", "Thomas", "Mill", "County Line", "Summit", "Martin",
		"Cypress", "Jones", "James", "Rose", "Hilltop", "Aspen", "Liberty",
		"Grant", "Sunrise", "Eagle", "Scott", "Fairview", "Thirteenth",
		"Allen", "Village", "Short", "School", "Green", "Fourteenth",
		"Cardinal", "Cottonwood"
	};

	private final static String[] ROAD_TYPES = {
		"St", "Ave", "Blvd", "Pl", "Ln", "Rd"
	};

	private final static String[] CITY_NAMES = { "Wetherington",
		"Indian Creek", "Mockingbird Valley", "Lost Creek", "Crows Nest",
		"Clarkson Valley", "Mission Woods", "Rolling Fields",
		"Strathmoor Manor", "North Crows Nest", "Las Palmas", "Plainville",
		"Williams Creek", "West University Place", "Westwood",
		"Lake Aluma", "University Park", "Fox Chapel", "Ten Broeck",
		"Glenview Hills", "Huntleigh", "Taylor Lake Village", "Somerset",
		"Southside Place", "Spring Valley Village", "Manor Creek",
		"Highland Park", "Indian Hills", "East Honolulu",
		"Hunters Creek Village", "Glenview Manor", "El Lago",
		"Henlopen Acres", "Wallace", "Country Life Acres", "Ko Olina",
		"South Beach", "Bunker Hill Village", "Old Brownsboro Place",
		"Hidden Hills", "Broeck Pointe", "Chevy Chase View", "Rollingwood",
		"Monte Sereno", "Ben Avon Heights", "Piney Point Village",
		"Terrell Hills", "Talahi Island", "Hilshire Village", "Kailua",
		"Bayou Country Club", "Bellaire", "Thornhill", "Wimberley",
		"Southlake", "Hills and Dales", "Bronxville", "Gotha",
		"Chevy Chase Village", "Belleair Shore", "Plandome Heights",
		"Palm Beach", "Creekside", "McLean", "Danville", "Ulen",
		"Colleyville", "Westover Hills", "Goose Creek", "Gulf Stream",
		"Heeia", "Salado", "Spring Valley", "Roslyn Estates",
		"Cherry Grove", "The Woodlands", "Westminster", "Brentwood",
		"Darien", "Coldstream", "Chevy Chase Section Five", "Kensington",
		"Rolling Hills Estates", "Friendswood", "Leland Grove", "Bancroft",
		"Plandome", "Jupiter Island", "Jupiter Inlet Colony",
		"Woodlawn Heights", "San Ramon", "Aliso Viejo", "Pawleys Island",
		"Ho-Ho-Kus", "Cinco Ranch", "Norbourne Estates", "Madison",
		"Crestview Hills", "Oak Ridge North", "Lazy Lake"
	};

	private final static String[] STATES = { "AL", "AK", "AZ", "AR", "CA",
		"CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
		"KS", "KY", "LA", "ME", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
		"NC", "ND", "OH", "OK", "OR", "MD", "MA", "MI", "MN", "MS", "MO",
		"PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
		"WI", "WY"
	};
}