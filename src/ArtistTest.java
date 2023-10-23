import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ArtistTest {
    public Artist artist;

    @Before
    public void setUp() {
        artist = new Artist();
    }

    @Test
    public void testAddArtist() {
        String artistId = "559ABCDE@@";
        String name = "BB";
        String address = "Melbourne|Victoria|Australia";
        String birthDate = "20-04-1980";
        String bio = "THIS IS UPDATE BIOO asd a asd ad asd adsa adas sdamet, fringilla";
        String[] awards = {"2020, Grammy Award for Best Rock Song", "2003, Grammy Award for Best Rap Song", "2011, Grammy Award for Best R&B Song"};
        String[] genres = {"pop", "classical"};
        String[] occupations = {"Musician", "Creator"};

        boolean result = artist.addArtist(artistId, name, address, birthDate, bio, awards, genres, occupations);
        assertTrue(result);
    }
    @Test
    public void updateArtist() {
        String artistId_update = "559ABCDE@@";
        String name_update = "Karntawan";
        String Address_update = "Melbourne|Victoria|Australia";
        String birthDate_update = "20-04-2001";
        String Bio_update = "THIS IS UPDATE BIO Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards_update = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres_update = {"pop", "classical"};
        String[] Occupations_update = {"Musician", "Creator", "Director"};
        boolean update_result = artist.updateArtist(artistId_update, name_update, Address_update, birthDate_update, Bio_update, Awards_update, Genres_update, Occupations_update);
        assertTrue(update_result);
    }
    //Test Scenario 1: Check the functionality of validate_artist_ID function.
    @Test 
    public void invalidArtistId() {
        String artistId = "ABC587%GB";
        boolean result = artist.verifyArtistId(artistId);
        assertFalse(result);
    }
    @Test
    public void validArtistId() {
        String artistId = "567ABCDE@#";
        boolean result = artist.verifyArtistId(artistId);
        assertTrue(result);
    }
    //Test Scenario 2: Check functionality to verify ‘Date of birth’.
    @Test
    public void invalidBirthDate() {
        String birthDate = "10-Apr-1997";
        boolean result = artist.verifyYear(birthDate);
        assertFalse(result);
    }
    @Test
    public void validBirthDate() {
        String birthDate = "20-04-2001";
        boolean result = artist.verifyYear(birthDate);
        assertTrue(result);
    }
    //Test Scenario 3: Check functionality to verify ‘address’.
    @Test
    public void invalidAddress() {
        String address = "Sydney";
        boolean result = artist.verifyAddress(address);
        assertFalse(result);
    }
    @Test
    public void validAddress() {
        String address = "Melbourne|Victoria|Australia";
        boolean result = artist.verifyAddress(address);
        assertTrue(result);
    }
    //Test Scenario 4: Check functionality to verify ‘Bio’
    @Test //Test data 1 : 40 words
    public void invalidBio() {
        String bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque porttitor purus vitae sem ornare lacinia. In quis velit vel sem scelerisque tristique lobortis quis tortor. Aenean in pretium turpis. Quisque in imperdiet urna, id cursus metus. In placerat a erat.";
        boolean result = artist.verifyBio(bio);
        assertFalse(result);
    }
    //Test data 2 : 20 words
    @Test
    public void validBio() {
        String bio = "Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla. ";
        boolean result = artist.verifyBio(bio);
        assertTrue(result);
    }
    //Test Scenario 5: Check functionality to verify occupations EXPECTED FALSE
    @Test //Test Case 1: Verify with empty occupation.
    public void invalidOccupations() {
        String[] occupations = {};
        boolean result = artist.verifyOccupation(occupations);
        assertFalse(result);
    }
    @Test //Test Case 2: Verify with six occupations. EXPECTED FALSE
    public void invalidOccupations2() {
        String[] occupations = {"Musician", "Creator", "Director", "Musician", "Creator", "Director"};
        boolean result = artist.verifyOccupation(occupations);
        assertFalse(result);
    }
    @Test //Test Case 3: Verify with occupations between one to five. EXPECTED TRUE
    public void validOccupations() {
        String[] occupations = {"Musician", "Creator", "Director", "Musician", "Creator"};
        boolean result = artist.verifyOccupation(occupations);
        assertTrue(result);
    }

    //Test Scenario 6: Check functionality to verify awards list
    @Test //Test Case 1: Verify with four awards with correct format. EXPECTED FALSE
    public void invalidAwards() {
        String[] awards = {"2020, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song","2023, Grammy Award for Best Country Song"};
        boolean result = artist.verifyAwards(awards);
        assertFalse(result);
    }
    @Test //Test Case 2: Verify with four awards with incorrect format. EXPECTED FALSE
    public void invalidAwards2() {
        String[] awards = {"Year 1999, Billboard Latin Music Award for Latin Pop Song of the Year","2020, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        boolean result = artist.verifyAwards(awards);
        assertFalse(result);
    }
    @Test //Test Case 3: Verify with three awards with incorrect format. EXPECTED FALSE
    public void invalidAwards3() {
        String[] awards = {"Year 1999, Billboard Latin Music Award for Latin Pop Song of the Year","2020, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song"};
        boolean result = artist.verifyAwards(awards);
        assertFalse(result);
    }
    @Test //Test Case 4: Verify with none award to three awards with correct format EXPECTED TRUE
    public void validAwards() {
        String[] awards = {"2020, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        boolean result = artist.verifyAwards(awards);
        assertTrue(result);
    }
    //Test Scenario 7: Check functionality to verify genres active list
    @Test //Test Case 1: Verify with one genre. EXPECTED FALSE
    public void invalidGenres() {
        String[] genres = {"pop"};
        boolean result = artist.verifyGenres(genres);
        assertFalse(result);
    }
    @Test //Test Case 2: Verify with two to five genres list.  EXPECTED TRUE
    public void validGenres() {
        String[] genres = {"pop","Jazz","Rock","Indy"};
        boolean result = artist.verifyGenres(genres);
        assertTrue(result);
    }
    @Test //Test Case 3: Verify with two to five genres list including pop and rock at the same time. EXPECTED FALSE
    public void invalidGenres2() {
        String[] genres = {"pop","Jazz","rock","Indy","Rock"};
        boolean result = artist.verifyGenres(genres);
        assertFalse(result);
    }
    //TEST Update Artist
    //Test Scenario 1: Update occupation 
    @Test // Test case 1: The artist was born after year 2000
    public void updateArtistTC1() {
        //addArtist
        String artistId = "569RANDO@@";
        String name = "Karntawan";
        String Address = "Melbourne|Victoria|Australia";
        String birthDate = "20-04-2001";
        String Bio = "Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres = {"pop", "classical"};
        String[] Occupations = {"Musician", "Creator"};
        boolean result = artist.addArtist(artistId, name, Address, birthDate, Bio, Awards, Genres, Occupations);
        assertTrue(result);
        //updateArtist
        String artistId_update = "569RANDO@@";
        String name_update = "Karntawan";
        String Address_update = "Melbourne|Victoria|Australia";
        String birthDate_update = "20-04-2001";
        String Bio_update = "THIS IS UPDATE BIO Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards_update = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres_update = {"pop", "classical"};
        String[] Occupations_update = {"Musician", "Creator", "Director"};
        //Check if DOB of artists is before or after 2000. if an artist was born before 2000, their occupation cannot be changed.
        if (artist.verifyYear(birthDate) == false){
            System.out.println("If an artist was born before 2000, their occupation cannot be changed.");
            Occupations_update = Occupations;
        }
        //Check if Awards were given to artists are before or after 2000. if an award was given before 2000, cannot be changed.
        if (artist.verifyAwardsYear(Awards) == false){
            System.out.println("Awards that were given to an artist before 2000 can not be changed.");
            Awards_update = Awards;
        }
        boolean update_result = artist.updateArtist(artistId_update, name_update, Address_update, birthDate_update, Bio_update, Awards_update, Genres_update, Occupations_update);
        assertTrue(update_result);
        assertTrue(artist.verifyYear(birthDate));
    }
    @Test //Test case 2: The artist was born before year 2000
    public void updateArtistTC2() {
        //addArtist
        String artistId = "569RANDO@@";
        String name = "Karntawan";
        String Address = "Melbourne|Victoria|Australia";
        String birthDate = "20-04-1998";
        String Bio = "Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres = {"pop", "classical"};
        String[] Occupations = {"Musician", "Creator"};
        boolean result = artist.addArtist(artistId, name, Address, birthDate, Bio, Awards, Genres, Occupations);
        assertTrue(result);
        //updateArtist
        String artistId_update = "569RANDO@@";
        String name_update = "Karntawan";
        String Address_update = "Melbourne|Victoria|Australia";
        String birthDate_update = "20-04-1998";
        String Bio_update = "THIS IS UPDATE BIO Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards_update = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres_update = {"pop", "classical"};
        String[] Occupations_update = {"Musician", "Creator", "Director"};
        //Check if DOB of artists is before or after 2000. if an artist was born before 2000, their occupation cannot be changed.
        if (artist.verifyYear(birthDate) == false){
            System.out.println("If an artist was born before 2000, their occupation cannot be changed.");
            Occupations_update = Occupations;
        }
        //Check if Awards were given to artists are before or after 2000. if an award was given before 2000, cannot be changed.
        if (artist.verifyAwardsYear(Awards) == false){
            System.out.println("Awards that were given to an artist before 2000 can not be changed.");
            Awards_update = Awards;
        }
        boolean update_result = artist.updateArtist(artistId_update, name_update, Address_update, birthDate_update, Bio_update, Awards_update, Genres_update, Occupations_update);
        assertTrue(update_result);
        assertFalse(artist.verifyYear(birthDate));
        assertArrayEquals(Occupations,Occupations_update);
    }
    //Test Scenario 2: Update awards year or title.
    @Test // Test case : Update awards title after year 2000
    public void updateArtistTC3() {
        //addArtist
        String artistId = "569RANDO@@";
        String name = "Karntawan";
        String Address = "Melbourne|Victoria|Australia";
        String birthDate = "20-04-2001";
        String Bio = "Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres = {"pop", "classical"};
        String[] Occupations = {"Musician", "Creator"};
        boolean result = artist.addArtist(artistId, name, Address, birthDate, Bio, Awards, Genres, Occupations);
        assertTrue(result);
        //updateArtist
        String artistId_update = "569RANDO@@";
        String name_update = "Karntawan";
        String Address_update = "Melbourne|Victoria|Australia";
        String birthDate_update = "20-04-2001";
        String Bio_update = "THIS IS UPDATE BIO Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards_update = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres_update = {"pop", "classical"};
        String[] Occupations_update = {"Musician", "Creator", "Director"};
        //Check if DOB of artists is before or after 2000. if an artist was born before 2000, their occupation cannot be changed.
        if (artist.verifyYear(birthDate) == false){
            System.out.println("If an artist was born before 2000, their occupation cannot be changed.");
            Occupations_update = Occupations;
        }
        //Check if Awards were given to artists are before or after 2000. if an award was given before 2000, cannot be changed.
        if (artist.verifyAwardsYear(Awards) == false){
            System.out.println("Awards that were given to an artist before 2000 can not be changed.");
            Awards_update = Awards;
        }
        boolean update_result = artist.updateArtist(artistId_update, name_update, Address_update, birthDate_update, Bio_update, Awards_update, Genres_update, Occupations_update);
        assertTrue(update_result);
        assertTrue(artist.verifyAwardsYear(Awards));
    }
    @Test // Test case : Update awards title before year 2000
    public void updateArtistTC4() {
        //addArtist
        String artistId = "569RANDO@@";
        String name = "Karntawan";
        String Address = "Melbourne|Victoria|Australia";
        String birthDate = "20-04-2001";
        String Bio = "Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards = {"1990, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres = {"pop", "classical"};
        String[] Occupations = {"Musician", "Creator"};
        boolean result = artist.addArtist(artistId, name, Address, birthDate, Bio, Awards, Genres, Occupations);
        assertTrue(result);
        //updateArtist
        String artistId_update = "569RANDO@@";
        String name_update = "Karntawan";
        String Address_update = "Melbourne|Victoria|Australia";
        String birthDate_update = "20-04-2001";
        String Bio_update = "THIS IS UPDATE BIO Sed eleifend risus sem, ateuismod libero pellentesque et. Nulla facilisi. Donec ac elit dapibus, viverra libero sit amet, fringilla.";
        String[] Awards_update = {"2000, Grammy Award for Best Rock Song","2003, Grammy Award for Best Rap Song","2011, Grammy Award for Best R&B Song"};
        String[] Genres_update = {"pop", "classical"};
        String[] Occupations_update = {"Musician", "Creator", "Director"};
        //Check if DOB of artists is before or after 2000. if an artist was born before 2000, their occupation cannot be changed.
        if (artist.verifyYear(birthDate) == false){
            System.out.println("If an artist was born before 2000, their occupation cannot be changed.");
            Occupations_update = Occupations;
        }
        //Check if Awards were given to artists are before or after 2000. if an award was given before 2000, cannot be changed.
        if (artist.verifyAwardsYear(Awards) == false){
            System.out.println("Awards that were given to an artist before 2000 can not be changed.");
            Awards_update = Awards;
        }
        boolean update_result = artist.updateArtist(artistId_update, name_update, Address_update, birthDate_update, Bio_update, Awards_update, Genres_update, Occupations_update);
        assertTrue(update_result);
        assertFalse(artist.verifyAwardsYear(Awards));
        assertArrayEquals(Awards,Awards_update);
    }
}

//Reference: Lars Vogel, 'JUnit 5 tutorial - Learn how to write unit tests',https://www.vogella.com/tutorials/JUnit/article.html