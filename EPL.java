import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.lang.Exception;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * This class implements a graphical login window and a simple text
 * interface for interacting with the branch table 
 */ 
public class EPL
{
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private Connection con;
    // user is allowed 3 login attempts
    private int loginAttempts = 0;
    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame mainFrame;
    /*
     * constructs login display and loads JDBC driver
     */ 
    public EPL()
    {
      try 
      {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());    // Load the Oracle JDBC driver
		if(connect("xyz", "xyz"))                        			// connect with your username and password
		    showMenu();
      }
      catch (SQLException ex)
      {
	    System.out.println("Message: " + ex.getMessage());
	    System.exit(-1);
      }
    }

    /*
     * connects to Oracle database named ug using user supplied username and password
     */ 
    private boolean connect(String username, String password)
    {
      String connectURL = "jdbc:oracle:thin:@xyz";                             

      try 
      {
	    con = DriverManager.getConnection(connectURL,username,password);
	    System.out.println("\nConnected to English Premier League 2018/19 Database");
	    return true;
      }
      catch (SQLException ex)
      {
	    System.out.println("Message: " + ex.getMessage());
	    return false;
      }
    }

    /*
     * displays simple text interface
     */ 
    private void showMenu()
    {
	int choice;
	boolean quit;
	quit = false;
	try 
	{
	    // disable auto commit mode
	    con.setAutoCommit(false);
	    while (!quit)
	    {
            System.out.print("\n\nPlease choose one of the following: \n");
            System.out.print("1.  Fans\n");
            System.out.print("2.  Managers\n");
            System.out.print("3.  League Admin\n");
            System.out.print("4.  Quit\n");
            choice = Integer.parseInt(in.readLine());
            System.out.println(" ");
            switch(choice)
            {
               case 1:  Fans(); break;
               case 2:  Managers(); break;
               case 3:  LeagueAdmin(); break;
               case 4:  quit = true;
            }
	    }
	    con.close();
	    in.close();
	    System.out.println("\nGood Bye!\n\n");
	    System.exit(0);
	}
	catch (IOException e)
	{
	    System.out.println("IOException!");
	    try
	    {
            con.close();
            System.exit(-1);
	    }
	    catch (SQLException ex)
	    {
		    System.out.println("Message: " + ex.getMessage());
	    }
	}
	catch (SQLException ex)
    {
	    System.out.println("Message: " + ex.getMessage());
	}
    }

    private void Managers()
    {
        int choice;
        boolean quit;
        quit = false;
        try
        {
            // disable auto commit mode
            con.setAutoCommit(false);
            while (!quit) {
                System.out.print("\n\nPlease choose one of the following: \n");
                System.out.print("1.  Insert Players\n");
                System.out.print("2.  Insert Injured Players\n");
                System.out.print("3.  Update Player Position\n");
                System.out.print("4.  View Suspended Players\n");
                System.out.print("5.  Number of players by nationality\n");
                System.out.print("6.  View Injured Players\n");
                System.out.print("7.  Quit\n");
                choice = Integer.parseInt(in.readLine());
                System.out.println(" ");

                switch(choice)
                {
                    case 1: insertPlayers(); break;
                    case 2: insertInjuredPlayers(); break;
                    case 3: updatePlayerPosition(); break;
                    case 4: viewSuspendedPlayers(); break;
                    case 5: playersNationality();break;
                    case 6: viewInjured(); break;
                    case 7:  quit = true;
                }
            }
            con.close();
            in.close();
            System.out.println("\nGood Bye!\n\n");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
            try
            {
                con.close();
                System.exit(-1);
            }
            catch (SQLException ex)
            {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void LeagueAdmin()
    {
        int choice;
        boolean quit;
        quit = false;

        try
        {
            // disable auto commit mode
            con.setAutoCommit(false);
            while (!quit) {
                System.out.print("\n\nPlease choose one of the following: \n");
                System.out.print("1.  Insert Players\n");
                System.out.print("2.  Insert Clubs\n");
                System.out.print("3.  Insert Referees\n");
                System.out.print("4.  Insert Matches\n");
                System.out.print("5.  Insert Suspended Players\n");
                System.out.print("6.  Delete Players\n");
                System.out.print("7.  Delete Clubs\n");
                System.out.print("8.  Delete Referees\n");
                System.out.print("9.  Remove Suspended Players\n");
                System.out.print("10.  Delete Managers\n");
                System.out.print("11.  Number of Fans\n");
                System.out.print("12.  View Matches\n");
                System.out.print("13.  Show names of all fans that support Manchester United\n");
                System.out.print("14.  Fan names that have tickets to every match\n");
                System.out.print("15.  Quit\n");

                choice = Integer.parseInt(in.readLine());
                System.out.println(" ");
                switch(choice)
                {
                    case 1: insertPlayers(); break;
                    case 2: insertClubs(); break;
                    case 3: insertRef(); break;
                    case 4: insertMatches(); break;
                    case 5: insertSuspendedPlayers(); break;
                    case 6: deletePlayers();break;
                    case 7: deleteClubs();break;
                    case 8: deleteReferees();break;
                    case 9: deleteSuspendedPlayers();break;
                    case 10: deleteManagers();break;
                    case 11: numberFans();break;
                    case 12: viewMatches();break;
                    case 14: loyalFans();break;
                    case 15:  quit = true;
                }
            }

            con.close();
            in.close();
            System.out.println("\nGood Bye!\n\n");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
            try
            {
                con.close();
                System.exit(-1);
            }
            catch (SQLException ex)
            {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void Fans(){
        int choice;
        boolean quit;
        quit = false;
        try
        {
            // disable auto commit mode
            con.setAutoCommit(false);
            while (!quit)
            {
                System.out.print("\n\nPlease choose one of the following: \n");
                System.out.print("1.  View Matches\n");
                System.out.print("2.  Place a Bet\n");
                System.out.print("3.  View Teams\n");
                System.out.print("4.  Buy Tickets\n");
                System.out.print("5.  View the names of Injured players\n");
                System.out.print("6.  Quit\n");

                choice = Integer.parseInt(in.readLine());
                System.out.println(" ");
                switch(choice)
                {
                    case 1: viewMatches(); break;
                    case 2: insertBet(); break;
                    case 3: viewTeams(); break;
                    case 4: buyTickets(); break;
                    case 5: viewInjured();break;
                    case 6:  quit = true;
                }
            }
            con.close();
            in.close();
            System.out.println("\nGood Bye!\n\n");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
            try
            {
                con.close();
                System.exit(-1);
            }
            catch (SQLException ex)
            {
                System.out.println("Message: " + ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void buyTickets(){
        int mid;
        int sectio;
        int sno;
        String emai;
        String stadium;
        PreparedStatement ps1;
        viewMatches();
        try{
            ps1 = con.prepareStatement("INSERT INTO ticket values (?,?,?,?,?)");

            System.out.print("\nInsert the Match ID from above of the match you are interested in buying tickets for: ");
            mid = Integer.parseInt(in.readLine());
            ps1.setInt(1, mid);

            System.out.print("\nChoose Section: ");
            sectio = Integer.parseInt(in.readLine());
            ps1.setInt(2, sectio);

            System.out.print("\nSelect seat number (1 - 250): ");
            sno = Integer.parseInt(in.readLine());
            ps1.setInt(3, sno);

            System.out.print("\nEnter your email address:");
            emai = in.readLine();
            ps1.setString(4, emai);

            System.out.print("\nEnter stadium:");
            stadium = in.readLine();
            ps1.setString(5, stadium);
            ps1.executeUpdate();
            con.commit();
            ps1.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }


    private void manchesterUnited(){								// SELECTION QUERY
        String name;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT name FROM fan WHERE club_supported = 'Manchester United'");
            ResultSetMetaData rsmd = rs.getMetaData();
            // get number of columns
            int numCols = rsmd.getColumnCount();
            System.out.println(" ");
            // display column names;
            for (int i = 0; i < numCols; i++)
                System.out.printf("%-30s", rsmd.getColumnName(i + 1));
            System.out.println(" ");

            while(rs.next()) {
                name = rs.getString("Name");
                System.out.printf("%-30.30s\n", name);
            }
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void loyalFans(){									// DIVISION QUERY
        String nameF;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select name from fan f WHERE NOT EXISTS (SELECT * from match m WHERE NOT EXISTS (SELECT t.match_id FROM ticket t WHERE t.email = f.email AND t.match_id = m.match_id))");
            ResultSetMetaData rsmd = rs.getMetaData();
            // get number of columns
            int numCols = rsmd.getColumnCount();
            System.out.println(" ");
            // display column names;
            for (int i = 0; i < numCols; i++)
                System.out.printf("%-30s", rsmd.getColumnName(i + 1));
            System.out.println(" ");

            while(rs.next()) {
                nameF = rs.getString("name");
                System.out.printf("%-30.30s", nameF);
            }
            stmt.close();
        }
        catch (SQLException ex){
            System.out.println("Message: " + ex.getMessage());
        }
    }
    private void playersNationality(){								// NESTED AGGREGATION
        String noPs, nat;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nationality, COUNT(*) AS Player_Count FROM players GROUP BY nationality");
            ResultSetMetaData rsmd = rs.getMetaData();
            // get number of columns
            int numCols = rsmd.getColumnCount();
            System.out.println(" ");
            // display column names;
            for (int i = 0; i < numCols; i++)
                System.out.printf("%-30s", rsmd.getColumnName(i + 1));
            System.out.println(" ");

            while(rs.next()) {
                nat = rs.getString("nationality");
                System.out.printf("%-30.30s", nat);

                noPs = rs.getString("Player_Count");
                System.out.printf("%-30.30s\n", noPs);
            }

            stmt.close();
        }
        catch (SQLException ex){
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void numberFans(){									// AGGREGATION QUERY
        String noFans;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS Number_Of_Fans FROM fan");
            ResultSetMetaData rsmd = rs.getMetaData();
            // get number of columns
            int numCols = rsmd.getColumnCount();
            System.out.println(" ");
            // display column names;
            for (int i = 0; i < numCols; i++)
                System.out.printf("%-15s", rsmd.getColumnName(i + 1));
            System.out.println(" ");

            rs.next();
            noFans = rs.getString("Number_Of_Fans");
            System.out.printf("%-10.10s", noFans);
             stmt.close();
            }
        catch (SQLException ex){
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void insertPlayers(){
        int pid;
        String pname;
        String pdate;
        String Nationality;
        String Position;
        String ClubN;
        String sincePlayed;
        PreparedStatement ps;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try{
            ps = con.prepareStatement("INSERT INTO players VALUES (?,?,?,?,?,?,?)");

            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(1, pid);

            System.out.print("\nName: ");
            pname = in.readLine();
            ps.setString(2, pname);

            System.out.print("\nDate of Birth (DD-MN-YYYY): ");
            pdate = in.readLine();
            java.util.Date date = sdf.parse(pdate);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlD);

            System.out.print("\nNationality: ");
            Nationality = in.readLine();
            ps.setString(4, Nationality);

            System.out.print("\nPosition (Goalkeeper, Defender, Midfielder, Forward): ");
            Position = in.readLine();
            ps.setString(5, Position);

            System.out.print("\nClub: ");
            ClubN = in.readLine();
            ps.setString(6, ClubN);

            System.out.print("\nSince Played (DD-MN-YYYY): ");
            sincePlayed = in.readLine();
            java.util.Date date1 = sdf.parse(sincePlayed);
            java.sql.Date sqlD1 = new java.sql.Date(date1.getTime());
            ps.setDate(7, sqlD1);
            ps.executeUpdate();

            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch (Exception e){
            System.out.println("date error");
        }
    }

    private void insertInjuredPlayers(){
        int pid;
        String InjuryType;
        String InjuredTill;
        PreparedStatement ps;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try{
            ps = con.prepareStatement("INSERT INTO Injured VALUES (?,?,?)");

            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(1, pid);

            System.out.print("\nInjured Till (DD-MN-YYYY): ");
            InjuredTill = in.readLine();
            java.util.Date date = sdf.parse(InjuredTill);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(2, sqlD);

            System.out.print("\nInjury Type: ");
            InjuryType = in.readLine();
            ps.setString(3, InjuryType);
            ps.executeUpdate();
            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch (Exception e){
            System.out.println("Date error");
        }
    }

    private void updatePlayerPosition(){
        int pid;
        String Position;
        PreparedStatement ps;
        try
        {
            ps = con.prepareStatement("UPDATE players SET Position = ? WHERE Player_ID = ?");

            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(2, pid);

            System.out.print("\nChange position to (Goalkeeper, Defender, Midfielder, Forward): ");
            Position = in.readLine();
            ps.setString(1, Position);

            int rowC = ps.executeUpdate();
            if(rowC == 0){
                System.out.println("\nPlayer " + pid + " does not exist ");
            }
            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }


    private void insertRef() {
        int rid;
        String rname;
        int age;
        String Nationality;
        String dates;
        PreparedStatement ps;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try
        {
            ps = con.prepareStatement("INSERT INTO referee VALUES (?,?,?,?,?)");

            System.out.print("\nReferee ID: ");
            rid = Integer.parseInt(in.readLine());
            ps.setInt(1, rid);

            System.out.print("\nReferee Name: ");
            rname = in.readLine();
            ps.setString(2, rname);

            System.out.print("\nDate of Birth (DD-MM-YYYY): ");
            dates = in.readLine();
            java.util.Date date = sdf.parse(dates);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlD);

            System.out.print("\nNationality: ");
            Nationality = in.readLine();
            ps.setString(4, Nationality);

            System.out.print("\nRefereing Since (DD-MM-YYYY): ");
            dates = in.readLine();
            java.util.Date date1 = sdf.parse(dates);
            java.sql.Date sqlD1 = new java.sql.Date(date1.getTime());
            ps.setDate(5, sqlD1);
            ps.executeUpdate();
            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch (Exception e){
            System.out.println("Date error");
        }
    }

    private void insertMatches(){
        int mid;
        String homeT;
        String awayT;
        int ref, homeS, awayS, homeG, awayG;
        String dates;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        PreparedStatement ps;

        try{
            ps = con.prepareStatement("INSERT INTO match VALUES(?,?,?,?,?,?,?,?,?)");

            System.out.print("\nMatch ID: ");
            mid = Integer.parseInt(in.readLine());
            ps.setInt(1, mid);

            System.out.print("\nHome Team: ");
            homeT = in.readLine();
            ps.setString(2, homeT);

            System.out.print("\nAway Team: ");
            awayT = in.readLine();
            ps.setString(3, awayT);

            System.out.print("\nReferee ID: ");
            ref = Integer.parseInt(in.readLine());
            ps.setInt(4, ref);

            System.out.print("\nDate of match (DD-MM-YYYY): ");
            dates = in.readLine();
            java.util.Date date = sdf.parse(dates);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(5, sqlD);

            System.out.print("\nHome Shots: ");
            homeS = Integer.parseInt(in.readLine());
            ps.setInt(6, homeS);

            System.out.print("\nAway Shots: ");
            awayS = Integer.parseInt(in.readLine());
            ps.setInt(7, awayS);

            System.out.print("\nHome Goals: ");
            homeG = Integer.parseInt(in.readLine());
            ps.setInt(8, homeG);

            System.out.print("\nAway Goals: ");
            awayG = Integer.parseInt(in.readLine());
            ps.setInt(9, awayG);
            ps.executeUpdate();
            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch(Exception e){
            System.out.println("Date error");
        }
    }

    private void insertSuspendedPlayers(){
        int pid;
        String SuspensionType;
        String SuspendedTill;
        PreparedStatement ps;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try{
            ps = con.prepareStatement("INSERT INTO Suspended VALUES (?,?,?)");

            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(1, pid);

            System.out.print("\nSuspension Type: ");
            SuspensionType = in.readLine();
            ps.setString(2, SuspensionType);

            System.out.print("\nSuspended Till (DD-MN-YYYY): ");
            SuspendedTill = in.readLine();
            java.util.Date date = sdf.parse(SuspendedTill);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlD);
            ps.executeUpdate();
            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch (Exception e){
            System.out.println("Date error");
        }
    }

    private void insertClubs() {
        String cname;
        String ownerN;
        String dateEst;
        String formm;
        float valuation;
        String stadiumN;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO club VALUES (?,?,?,?,?,?)");
            System.out.print("\nClub Name: ");
            cname = in.readLine();
            ps.setString(1, cname);

            System.out.print("\nOwner: ");
            ownerN = in.readLine();
            ps.setString(2, ownerN);

            System.out.print("\nDate Of Establishment (DD-MM-YYYY): ");
            dateEst = in.readLine();
            java.util.Date date = sdf.parse(dateEst);
            java.sql.Date sqlD = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlD);

            System.out.print("\nForm: ");
            formm = in.readLine();
            ps.setString(4, formm);

            System.out.print("\nValuation: ");
            valuation = Float.parseFloat(in.readLine());
            ps.setFloat(5, valuation);

            System.out.print("\nStadium: ");
            stadiumN = in.readLine();
            ps.setString(6, stadiumN);

            ps.executeUpdate();
            // commit work
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                // undo the insert
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        catch (Exception e) {
            System.out.println("Date error");
        }
    }

    private void insertBet(){
    int mid;
    String gamblerEmail;
    float odds;
    float betValue;
    int paid;
    PreparedStatement ps;

    try{
        ps = con.prepareStatement("INSERT INTO bet VALUES (?,?,?,?,?)");

        System.out.print("\nMatch Id: ");
        mid = Integer.parseInt(in.readLine());
        ps.setInt(1, mid);

        System.out.print("\nGambler Email Addres: ");
        gamblerEmail = in.readLine();
        ps.setString(2, gamblerEmail);

        System.out.print("\nOdds: ");
        odds = Float.parseFloat(in.readLine());
        ps.setFloat(3, odds);

        System.out.print("\nBeting Amount: ");
        betValue = Float.parseFloat(in.readLine());
        if (betValue < 10){
            System.out.print("\nThe minimum amount to bet is $10, we will revert your amount to $10");
            betValue = 10;
            ps.setFloat(4, betValue);
        } else if (betValue > 1000){
            System.out.print("The maximum amount to bet is $1000, we will revert your amount to $1000");
            betValue = 1000;
            ps.setFloat(4, betValue);
        } else {
            ps.setFloat(4, betValue);
        }
        System.out.print("\nPaid: ");
        paid = Integer.parseInt(in.readLine());
        ps.setInt(5, paid);
        ps.executeUpdate();
        // commit work
        con.commit();
        ps.close();
    }
    catch (IOException e)
    {
        System.out.println("IOException!");
    }
    catch (SQLException ex)
    {
        System.out.println("Message: " + ex.getMessage());
        try
        {
            // undo the insert
            con.rollback();
        }
        catch (SQLException ex2)
        {
            System.out.println("Message: " + ex2.getMessage());
            System.exit(-1);
        }
    }
}

    private void viewMatches()
    {
        String mid, homeT, awayT, dateM, reff, homeS, awayS, homeG, awayG;
        Statement stmt;
        ResultSet rs;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM match");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numC = rsmd.getColumnCount();
            System.out.println(" ");

            for (int i = 0; i < numC; i++){
                System.out.printf("%-20s", rsmd.getColumnName(i+1));
            }
            System.out.println(" ");

            while(rs.next()){
                mid = rs.getString("Match_ID");
                System.out.printf("%-20.20s", mid);

                homeT = rs.getString("Home");
                System.out.printf("%-20.20s", homeT);

                awayT = rs.getString("Away");
                System.out.printf("%-20.20s", awayT);

                reff = rs.getString("Referee");
                System.out.printf("%-20.20s", reff);

                dateM = rs.getString("Match_Date");
                System.out.printf("%-20.20s", dateM.substring(0,10));

                homeS = rs.getString("HShots");
                System.out.printf("%-20.20s", homeS);

                awayS = rs.getString("AShots");
                System.out.printf("%-20.20s", awayS);

                homeG = rs.getString("HG");
                System.out.printf("%-20.20s", homeG);

                awayG = rs.getString("AG");
                System.out.printf("%-20.20s\n", awayG);

            }
            stmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }


    private void viewSuspendedPlayers(){							// JOIN QUERY
        String pname;
        String club;
        Statement stmt;
        ResultSet rs;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT name, club FROM Players p, Suspended sp WHERE sp.Player_ID = p.Player_ID");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numC = rsmd.getColumnCount();
            System.out.println(" ");

            for (int i = 0; i < numC; i++){
                System.out.printf("%-30s", rsmd.getColumnName(i+1));
            }
            System.out.println(" ");
            while(rs.next()){
                pname = rs.getString("name");
                System.out.printf("%-30.30s", pname);

                club = rs.getString("club");
                System.out.printf("%-30.30s\n", club);

            }
            stmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void viewInjured(){									// JOIN QUERY
        String pname;
        String club;
        Statement stmt;
        ResultSet rs;

        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT name, club FROM Players p, Injured ip WHERE ip.Player_ID = p.Player_ID");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numC = rsmd.getColumnCount();
            System.out.println(" ");

            for (int i = 0; i < numC; i++){
                System.out.printf("%-30s", rsmd.getColumnName(i+1));
            }
            System.out.println(" ");
            while(rs.next()){
                pname = rs.getString("name");
                System.out.printf("%-30.30s", pname);

                club = rs.getString("club");
                System.out.printf("%-30.30s\n", club);

            }
            stmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void viewTeams()									// PROJECTION QUERY
    {
        String tname, clubOwner, managerN;
        Statement stmt;
        ResultSet rs;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Name, Owner, Stadium_Name FROM club");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numC = rsmd.getColumnCount();
            System.out.println(" ");

            for (int i = 0; i < numC; i++){
                System.out.printf("%-35s", rsmd.getColumnName(i+1));
            }
            System.out.println(" ");
            while(rs.next()){
                tname = rs.getString("Name");
                System.out.printf("%-30.30s", tname);

                clubOwner = rs.getString("owner");
                System.out.printf("%-40.40s", clubOwner);

                managerN = rs.getString("Stadium_Name");
                System.out.printf("%-30.30s\n", managerN);
            }
            stmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
        }
    }

    private void deleteManagers(){
        String nameM, teamN;
        PreparedStatement ps;
        try{
            ps = con.prepareStatement("DELETE FROM manager WHERE Name = (?) and Team = (?)");

            System.out.print("\nManager Name: ");
            nameM = in.readLine();
            ps.setString(1, nameM);

            System.out.print("\nTeam Name: ");
            teamN = in.readLine();
            ps.setString(2, teamN);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0)
            {
                System.out.println("\nManager " + nameM + " or " + teamN + " does not exist");
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void deleteClubs(){
        String cName;
        PreparedStatement ps;
        try
        {
            ps = con.prepareStatement("DELETE FROM club WHERE Name = ?");

            System.out.print("\nClub Name: ");
            cName = in.readLine();
            ps.setString(1, cName);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0)
            {
                System.out.println("\n " + cName + " does not exist");
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void deleteReferees(){
        int rid;
        PreparedStatement ps;

        try
        {
            ps = con.prepareStatement("DELETE FROM referee WHERE Referee_ID = ?");
            System.out.print("\nReferee ID: ");
            rid = Integer.parseInt(in.readLine());
            ps.setInt(1, rid);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0)
            {
                System.out.println("\nReferee " + rid + " does not exist");
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void deleteSuspendedPlayers(){
        int pid;
        PreparedStatement ps;

        try
        {
            ps = con.prepareStatement("DELETE FROM suspended WHERE Player_ID = ?");

            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(1, pid);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0)
            {
                System.out.println("\nPlayer " + pid + " does not exist");
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void deletePlayers(){
        int pid;
        PreparedStatement ps;

        try
        {
            ps = con.prepareStatement("DELETE FROM players WHERE Player_ID = ?");
            System.out.print("\nPlayer ID: ");
            pid = Integer.parseInt(in.readLine());
            ps.setInt(1, pid);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0)
            {
                System.out.println("\nPlayer " + pid + " does not exist");
            }
            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            try
            {
                con.rollback();
            }
            catch (SQLException ex2)
            {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }
    public static void main(String args[])
    {
      EPL e = new EPL();
    }
}

