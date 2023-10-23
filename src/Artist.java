import java.util.Arrays;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;


public class Artist{   
    // addArtist is used to add a new artist to the artists.txt file by using BufferedWriter. This function also checks if the artist information is valid.
    public boolean addArtist(String artistId, String name, String Address, String birthDate, String Bio, String[] Awards, String[] Genres, String[] Occupations) {
        if(verifyArtistId(artistId) && verifyAddress(Address) && VerifyDOB(birthDate) && verifyBio(Bio) && verifyAwards(Awards) && verifyGenres(Genres) && verifyOccupation(Occupations) ){
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("ex1.txt", true));
                    String line = artistId + "," + name + "," + Address + "," + birthDate + "," + Bio + "," + String.join("|", Awards) + "," + String.join("|", Genres) + "," + String.join("|", Occupations) +"\n" ;
                    writer.write(line);
                    writer.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            else{
                return false;
            }
    }
    public boolean verifyArtistId(String artistId) {
        String regex = "^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|]{2}$";
        if (Pattern.matches(regex, artistId) && artistId.length() == 10){
            return true;
        }        
        else{
            System.out.println("The artist id is invalid!!!");
            return false;
        }
    }
    public boolean verifyAddress(String Address){
        String regex = "^[^|]+\\|[^|]+\\|[^|]+$";
        if (Pattern.matches(regex, Address)){
            return true;
        }        
        else{
            System.out.println("The address is invalid!!!");
            return false;
        }
    }
    public boolean VerifyDOB(String birthDate){
	    SimpleDateFormat frmtdate = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date verifyDate = frmtdate.parse(birthDate);
            return true;
        } catch (ParseException e){
            System.out.println("The date of birth is invalid!!!");
            return false;
        }
    }
    public boolean verifyYear(String birthDate){
	    SimpleDateFormat frmtdate = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date verifyDate = frmtdate.parse(birthDate);
            // Extract the year from the parsed date
            Calendar cal = Calendar.getInstance();
            cal.setTime(verifyDate);
            int year = cal.get(Calendar.YEAR);
            if (year > 2000) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            System.out.println("The date of birth is invalid!!!");
            return false;
        }
    }
    public boolean verifyBio(String Bio) {
        int wordCount = Bio.split("\s+").length;
        if ( wordCount >= 10 && wordCount <= 30){
            return true;
        }        
        else{
            System.out.println("The bio data is invalid!!!");
            return false;
        }
    }
    public boolean verifyAwards(String[] Awards) {
        List<String> list = Arrays.asList(Awards);
        int itemCount = list.size();
        if(itemCount > 3) {
            System.out.println("The awards data is invalid!!!");
            return false;
        }
        for(String award : Awards) {
            String[] parts = award.split(",");
            try {
                int year = Integer.parseInt(parts[0].trim());
                    return true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year in awards data.");
                return false;
            }
        }
        return true;
    }
    public boolean verifyAwardsYear(String[] Awards) {
        List<String> list = Arrays.asList(Awards);
        int itemCount = list.size();
        if(itemCount > 3) {
            System.out.println("The awards data is invalid!!!");
            return false;
        }
        for(String award : Awards) {
            String[] parts = award.split(",");
            try {
                int award_year = Integer.parseInt(parts[0].trim());
                if (award_year >= 2000) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid year in awards data.");
                return false;
            }
        }
        return true;
    }
    public boolean verifyGenres(String[] Genres) {
        List<String> list = Arrays.asList(Genres);
        boolean hasPop = false;
        boolean hasRock = false;
        int itemCount = list.size();
        if ( itemCount < 2 || itemCount > 5){
            System.out.println("The genre data is invalid!!!");
            return false;
        }
        for (String genre : Genres) {
            if (genre.equals("pop")){
                if(hasRock){
                    System.out.println("The genre data is invalid!!! (has both pop and rock)");
                    return false;
                }
                hasPop = true;
                }
                else if(genre.equals("rock")){
                if(hasPop){
                    System.out.println("The genre data is invalid!!! (has both pop and rock)");
                    return false;
                }
                hasRock = true;
                }
        }
        return true;
    }
    public boolean verifyOccupation(String[] Occupations) {
        List<String> list = Arrays.asList(Occupations);
        int itemCount = list.size();
        if ( itemCount >= 1 && itemCount <= 5){
            return true;
        }        
        else{
            System.out.println("The occupations data is invalid!!!");
            return false;
        }
    }
    // updateArtist is used to update an existing artist ID to the artists.txt file by using BufferedWriter and temp file. This function also checks if the artist information is valid.
    public boolean updateArtist(String artistId, String name, String Address, String birthDate, String Bio, String[] Awards, String[] Genres, String[] Occupations){
        String update_file = "ex1.txt";
        String updateInfo = artistId + "," + name + "," + Address + "," + birthDate + ","
                + Bio + "," + String.join("|", Awards) + ","
                + String.join("|", Genres) + "," + String.join("|", Occupations);
        if (verifyArtistId(artistId) && verifyAddress(Address) && VerifyDOB(birthDate) && verifyBio(Bio) && verifyAwards(Awards) && verifyGenres(Genres) && verifyOccupation(Occupations)){
        try {
            File file = new File(update_file);
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
            String line;
            boolean artistFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(artistId + ",")) {
                    artistFound = true;
                    writer.write(updateInfo + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            reader.close();
            writer.close();

            if (artistFound) {
                file.delete();
                tempFile.renameTo(new File(update_file));
                return true;
            } else {
                System.out.println("Artist with ID " + artistId + " not found.");
                tempFile.delete();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    } else {
        return false;
    }
    }

    public static void main(String[] args) {
        Artist artist = new Artist();
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
        System.out.println("The result is : " + result );
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
        System.out.println("The update result is : " + update_result );
    }
}

//Reference: prashant_srivastava, 'How to validate identifier using Regular Expression in Java', https://www.geeksforgeeks.org/how-to-validate-identifier-using-regular-expression-in-java/
//Chaitanya, 'Java Date Validation Example',https://beginnersbook.com/2013/05/java-date-format-validation/