drop table Suspended;
drop table Injured;
drop table Players;
drop table Bet;
drop table Gambler;
drop table Manager;
drop table Ticket;
drop table Match;
drop table Referee;
drop table Fan;
drop table Club;
drop table Seat;
drop table Stadium;

CREATE TABLE Stadium(
	Name varchar(25),
	Location varchar(20),
	Mailing_Address varchar(80),
	Primary Key(Name)
);

CREATE TABLE Seat( 
	Section int,
	Seat_No int,
	Sale_Price real,
	Stadium varchar(40),
	Primary Key(Section, Seat_no, Stadium),
	Foreign Key(Stadium) REFERENCES Stadium(Name)
);

CREATE TABLE Club(
	Name varchar(20)Primary Key,
	Owner varchar(40) NOT NULL,
	Established date,
	Form varchar(20),
	Valuation real,
	Stadium_Name varchar(40),
	Foreign Key(Stadium_Name) REFERENCES Stadium(Name)
);

CREATE TABLE Fan(
	Email varchar(40) Primary Key,
	Name varchar(25) NOT NULL,
	Nationality varchar(20),
	DoB date,
	Club_supported varchar(20),
	Foreign Key(Club_supported) REFERENCES Club(Name)
);

CREATE TABLE Referee(
	Referee_ID int Primary Key,
	Name varchar(30) NOT NULL,
	Birthdate date,
	Nationality varchar(20),
	Ref_since date
);

CREATE TABLE Match(
	Match_ID int Primary Key,
	Home varchar(20),
	Away varchar(20),
	Referee int,
	Match_Date date,
	HShots int,
	AShots int,
	HG int,
	AG int,
	Foreign Key(Home) REFERENCES Club(Name),
	Foreign Key(Away) REFERENCES Club(Name),
	Foreign Key(Referee) REFERENCES Referee(Referee_ID)
);

CREATE TABLE Ticket(
	Match_ID int,
	Section int,
	Seat_No int,
	Email varchar(40),
	Stadium varchar(40),
	Primary Key(Match_ID, Section, Seat_No),
	Foreign Key(Match_ID) REFERENCES Match(Match_ID),
	Foreign Key(Section, Seat_No, Stadium) REFERENCES Seat(Section, Seat_No, Stadium),
	Foreign Key(Email) REFERENCES Fan(Email)
);

CREATE TABLE Manager(
	Name varchar(30),
	Team varchar(20),
	Nationality varchar(30),
	Primary Key(Name, Team),
	Foreign Key(Team) REFERENCES Club(Name)
);

CREATE TABLE Gambler(
	Email varchar(40) Primary Key,
	Credit_card int NOT NULL,
	Foreign Key(Email) REFERENCES Fan(Email)
);

CREATE TABLE Bet(
	Match_ID int,
	Gambler varchar(40),
	Odds real,
	Amount real,
	Paid int,
	Primary Key(Match_ID, Gambler),
	Foreign Key(Match_ID) REFERENCES Match(Match_ID),
	Foreign Key(Gambler) REFERENCES Gambler(Email)
);

CREATE TABLE Players(
	Player_ID int Primary Key,
	Name varchar(30),
	DoB date,
	Nationality varchar(20),
	Position char(15),
	Club varchar(20),
	End_date date,
	Foreign Key(Club) REFERENCES Club(Name)
);

CREATE TABLE Injured(
    Player_ID int Primary Key,
    Expected_Recovery_Date Date,
    Injury_Type char(50),
    Foreign Key(Player_ID) REFERENCES Players(Player_ID)
);

CREATE TABLE Suspended(
    Player_ID int Primary Key,
    Suspension_Type char(35) NOT NULL,
    Suspended_Till Date NOT NULL,
    Foreign Key(Player_ID) REFERENCES PLayers(Player_ID)
);

-- stadiums

insert into Stadium values
('Emirates Stadium', 'Islington', 'Hornsey Rd, London N7 7AJ, UK');

insert into Stadium values
('Dean Court', 'Bournemouth', 'Kings Park Drive, Bournemouth BH7 7AF, UK');

insert into Stadium values
('Turf Moor', 'Burnley', '52-56 Harry Potts Way, Burnley BB10 4BX, UK');

insert into Stadium values
('Old Trafford', 'Stretford', 'Sir Matt Busby Way, Stretford, Manchester M16 0RA, UK');

insert into Stadium values
('Stamford Bridge', 'Fulham', 'Fulham Rd, Fulham, London SW6 1HS, UK');

-- seats

insert into Seat values
(1, 1, 100, 'Emirates Stadium');

insert into Seat values
(1, 10, 50, 'Emirates Stadium');

insert into Seat values
(5, 26, 25, 'Emirates Stadium');

insert into Seat values
(2, 3, 30, 'Dean Court');

insert into Seat values
(5, 20, 10, 'Dean Court');

insert into Seat values
(10, 99, 5, 'Dean Court');

insert into Seat values
(3, 22, 99, 'Turf Moor');

insert into Seat values
(5, 200, 44, 'Turf Moor');

insert into Seat values
(100, 233, 22, 'Turf Moor');

insert into Seat values
(1, 50, 30, 'Old Trafford');

insert into Seat values
(2, 50, 30, 'Old Trafford');

insert into Seat values
(5, 200, 10, 'Old Trafford');

insert into Seat values
(3, 22, 597, 'Stamford Bridge');

insert into Seat values
(1, 600, 254, 'Stamford Bridge');

insert into Seat values
(100, 12, 174, 'Stamford Bridge');

-- clubs

insert into Club values
('Arsenal', 'Kroenke Sports and Entertainment', TO_DATE('01-10-1886', 'DD-MM-YYYY'), 'Good', 2240000000, 'Emirates Stadium');

insert into Club values
('AFC Bournemouth', 'Maxim Demin', TO_DATE('01-JAN-1899', 'DD-MM-YYYY'), 'Good', NULL, 'Dean Court');

insert into Club values
('Burnley', 'Mike Garlick', TO_DATE('18-MAY-1882', 'DD-MM-YYYY'), 'Bad', 139000000, 'Turf Moor');

insert into Club values
('Chelsea', 'Roman Abramovich', TO_DATE('10-MAR-1905', 'DD-MM-YYYY'), 'Good', 1540000000, 'Stamford Bridge');

insert into Club values
('Manchester United', 'Manchester United PLC (NYSE:MANU)', TO_DATE('01-JAN-1878', 'DD-MM-YYYY'), 'Bad', 2930000000, 'Old Trafford');

-- fans 3 per club

insert into Fan values
('kevin.long@gmail.com', 'Kevin Long', 'Chinese', TO_DATE('05-MAY-1991', 'DD-MM-YYYY'), 'Arsenal');

insert into Fan values
('jack@gmail.com', 'Jack Keith', 'American', TO_DATE('11-JUN-1996', 'DD-MM-YYYY'), 'Arsenal');

insert into Fan values
('liu@gmail.com', 'Jordan Liu', 'Chinese', TO_DATE('24-APR-1998', 'DD-MM-YYYY'), 'Arsenal');

insert into Fan values
('charles@gmail.com', 'Charles Stevens', 'Canadian', TO_DATE('11-JUN-1996', 'DD-MM-YYYY'), 'AFC Bournemouth');

insert into Fan values
('henry@gmail.com', 'Henry Liu', 'Chinese', TO_DATE('01-DEC-1990', 'DD-MM-YYYY'), 'AFC Bournemouth');

insert into Fan values
('charles2@gmail.com', 'Charles Kevin', 'Canadian', TO_DATE('11-JUN-1996', 'DD-MM-YYYY'), 'AFC Bournemouth');

insert into Fan values
('justin@gmail.com', 'Justin Hello', 'American', TO_DATE('01-MAR-1999', 'DD-MM-YYYY'), 'Burnley');

insert into Fan values
('justin2@gmail.com', 'Justin Hello', 'Korean', TO_DATE('15-AUG-1983', 'DD-MM-YYYY'), 'Burnley');

insert into Fan values
('cbai@gmail.com', 'Charles Bai', 'Chinese', TO_DATE('25-NOV-1998', 'DD-MM-YYYY'), 'Burnley');

insert into Fan values
('abc@gmail.com', 'Hello Kitty', 'Japanese', TO_DATE('25-NOV-1998', 'DD-MM-YYYY'), 'Chelsea');

insert into Fan values
('asdasd@gmail.com', 'Mouse Cat', 'American', TO_DATE('01-MAY-2000', 'DD-MM-YYYY'), 'Chelsea');

insert into Fan values
('holyplease@gmail.com', 'Holy Please', 'Canadian', TO_DATE('07-JUL-1995', 'DD-MM-YYYY'), 'Chelsea');

insert into Fan values
('woma@gmail.com', 'Wo Ma', 'Chinese', TO_DATE('01-JUL-1999', 'DD-MM-YYYY'), 'Manchester United');

insert into Fan values
('cao@gmail.com', 'Cao Li', 'Chinese', TO_DATE('22-JAN-1994', 'DD-MM-YYYY'), 'Manchester United');

insert into Fan values
('grace@gmail.com', 'Grace Zhang', 'Canadian', TO_DATE('02-AUG-1992', 'DD-MM-YYYY'), 'Manchester United');

-- Referees

insert into Referee values
(01, 'Steve Smith', TO_DATE('05-MAY-1977', 'DD-MM-YYYY'), 'England', TO_DATE('09-MAY-2010', 'DD-MM-YYYY'));

insert into Referee values
(02, 'Michael Scott', TO_DATE('22-APR-1967' , 'DD-MM-YYYY'), 'England', TO_DATE('21-JUN-2015', 'DD-MM-YYYY'));

insert into Referee values
(03, 'Phillip Martin', TO_DATE('19-OCT-1990', 'DD-MM-YYYY'), 'France', TO_DATE('01-JAN-2016', 'DD-MM-YYYY'));

-- Matches

insert into Match values
(01, 'Arsenal', 'Manchester United', 01, TO_DATE('06-SEP-2011', 'DD-MM-YYYY'), 8, 6, 2, 1);

insert into Match values
(02, 'AFC Bournemouth', 'Arsenal', 02, TO_DATE('09-SEP-2011', 'DD-MM-YYYY'), 5, 4, 0, 0);

insert into Match values
(03, 'Chelsea', 'Arsenal', 01, TO_DATE('18-SEP-2011', 'DD-MM-YYYY'), 8, 9, 1, 3);

insert into Match values
(04, 'Arsenal', 'Burnley', 03, TO_DATE('21-SEP-2011', 'DD-MM-YYYY'), 5, 6, 1, 1);

insert into Match values
(05,  'Burnley','Manchester United', 01, TO_DATE('10-SEP-2011', 'DD-MM-YYYY'), 10, 7, 4, 1);

insert into Match values
(06, 'Manchester United', 'Chelsea', 01, TO_DATE('13-SEP-2011', 'DD-MM-YYYY'), 6, 5, 1, 0);

insert into Match values
(07, 'Manchester United', 'AFC Bournemouth', 02, TO_DATE('20-SEP-2011', 'DD-MM-YYYY'), 7, 7, 2, 1);

insert into Match values
(08,  'Chelsea', 'AFC Bournemouth', 03, TO_DATE('17-SEP-2011', 'DD-MM-YYYY'), 6, 7, 1, 1);

insert into Match values
(09, 'AFC Bournemouth', 'Burnley', 02, TO_DATE('24-SEP-2011', 'DD-MM-YYYY'), 4, 8, 1, 2);

insert into Match values
(10, 'Chelsea', 'Burnley', 03, TO_DATE('07-SEP-2011', 'DD-MM-YYYY'), 5, 7, 1, 1);

-- Tickets

insert into Ticket values
(01, 1, 1, 'cbai@gmail.com', 'Emirates Stadium');

insert into Ticket values
(08, 1, 600, 'henry@gmail.com', 'Stamford Bridge');

insert into Ticket values
(09, 5, 20, 'jack@gmail.com', 'Dean Court');

-- Managers

insert into Manager values
('Sam Yan', 'Arsenal', 'Canadian');

insert into Manager values
('Charles Bai', 'AFC Bournemouth', 'American');

insert into Manager values
('Jack Stanely', 'Burnley', 'American');

insert into Manager values
('Nigel Tan', 'Chelsea', 'Canadian');

insert into Manager values
('Sam Chen', 'Manchester United', 'Chinese');

-- Gamblers

insert into Gambler values
('cbai@gmail.com', 1234567891123456);

insert into Gambler values
('grace@gmail.com', 1029384019283920);

insert into Gambler values
('justin@gmail.com', 6859203948273849);

insert into Gambler values
('jack@gmail.com', 3928403928394829);

insert into Gambler values
('henry@gmail.com', 3928347192837281);

-- Bets

insert into Bet values
(01, 'cbai@gmail.com', 0.20, 500, 1);

insert into Bet values
(02, 'cbai@gmail.com', 0.05, 10000, 1);

insert into Bet values
(01, 'henry@gmail.com', 0.90, 2341, 0);

insert into Bet values
(09, 'jack@gmail.com', 0.90, 500, 1);

insert into Bet values
(03, 'justin@gmail.com', 0.20, 600, 1);

insert into Bet values
(05, 'grace@gmail.com', 0.001, 20, 0);

insert into Bet values
(10, 'cbai@gmail.com', 0.0001, 10000, 1);

insert into Bet values
(05, 'cbai@gmail.com', 0.90, 10, 1);

-- Players

insert into Players values
(10000, 'Petr Cech', TO_DATE('21-SEP-1985', 'DD-MM-YYYY'), 'Czech Republic', 'Goalkeeper', 'Arsenal', TO_DATE('18-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(10001, 'Rob Holding', TO_DATE('11-JAN-1995', 'DD-MM-YYYY'), 'England', 'Defender', 'Arsenal', TO_DATE('17-SEP-2012', 'DD-MM-YYYY'));

insert into Players values
(10002, 'Nacho Monreal', TO_DATE('13-DEC-1994', 'DD-MM-YYYY'), 'Spain', 'Defender', 'Arsenal', TO_DATE('08-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(10003, 'Danny Welbeck', TO_DATE('11-FEB-1995', 'DD-MM-YYYY'), 'England', 'Forward', 'Arsenal', TO_DATE('11-AUG-2015', 'DD-MM-YYYY'));

insert into Players values
(10004, 'David Ospina', TO_DATE('21-MAR-1989', 'DD-MM-YYYY'), 'Columbia', 'Goalkeeper', 'Arsenal', TO_DATE('08-AUG-2017', 'DD-MM-YYYY'));

insert into Players values
(10005, 'Hector Bellerin', TO_DATE('20-JAN-1992', 'DD-MM-YYYY'), 'Spain', 'Defender', 'Arsenal', TO_DATE('08-JUL-2016', 'DD-MM-YYYY'));

insert into Players values
(10006, 'Per Mertesacker', TO_DATE('22-NOV-1990', 'DD-MM-YYYY'), 'Germany', 'Defender', 'Arsenal', TO_DATE('11-AUG-2014', 'DD-MM-YYYY'));

insert into Players values
(10007, 'Laurent Koscielny', TO_DATE('01-SEP-1992', 'DD-MM-YYYY'), 'France', 'Defender', 'Arsenal', TO_DATE('08-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(10008, 'Shkodran Mustafi', TO_DATE('11-SEP-1989', 'DD-MM-YYYY'), 'Germany', 'Defender', 'Arsenal', TO_DATE('18-DEC-2016', 'DD-MM-YYYY'));

insert into Players values
(10009, 'Calum Chambers', TO_DATE('21-OCT-1996', 'DD-MM-YYYY'), 'England', 'Defender', 'Arsenal', TO_DATE('01-AUG-2018', 'DD-MM-YYYY'));

insert into Players values
(10010, 'Mohammad Elneny', TO_DATE('29-SEP-1994', 'DD-MM-YYYY'), 'Egypt', 'Midfielder', 'Arsenal', TO_DATE('11-AUG-2017', 'DD-MM-YYYY'));

insert into Players values
(10011, 'Mesut Ozil', TO_DATE('13-DEC-1990', 'DD-MM-YYYY'), 'Germany', 'Midfielder', 'Arsenal', TO_DATE('28-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(10012, 'Granit Xhaka', TO_DATE('02-APR-1991', 'DD-MM-YYYY'), 'Switzerland', 'Midfielder', 'Arsenal', TO_DATE('10-DEC-2016', 'DD-MM-YYYY'));

insert into Players values
(10013, 'Vlad Dragomir', TO_DATE('11-FEB-1996', 'DD-MM-YYYY'), 'Romania', 'Midfielder', 'Arsenal', TO_DATE('08-DEC-2013', 'DD-MM-YYYY'));

insert into Players values
(10014, 'Alexandre Lacazette', TO_DATE('29-MAR-1992', 'DD-MM-YYYY'), 'France', 'Forward', 'Arsenal', TO_DATE('24-DEC-2017', 'DD-MM-YYYY'));

insert into Players values
(10015, 'Joel Campbell', TO_DATE('13-MAY-1996', 'DD-MM-YYYY'), 'Costa Rica', 'Forward', 'Arsenal', TO_DATE('02-JAN-2016', 'DD-MM-YYYY'));

insert into Players values
(10016, 'Lucas Perez', TO_DATE('16-OCT-1995', 'DD-MM-YYYY'), 'Spain', 'Forward', 'Arsenal', TO_DATE('09-AUG-2012', 'DD-MM-YYYY'));

insert into Players values
(10017, 'Chuba Akpom', TO_DATE('04-JUN-1993', 'DD-MM-YYYY'), 'England', 'Forward', 'Arsenal', TO_DATE('11-JAN-2013', 'DD-MM-YYYY'));

insert into Players values
(20000, 'Nathan Ake', TO_DATE('21-MAR-1993', 'DD-MM-YYYY'), 'Netherlands', 'Defender', 'AFC Bournemouth', TO_DATE('21-JUL-2011', 'DD-MM-YYYY'));

insert into Players values
(20001, 'Diego Rico', TO_DATE('30-MAY-1996', 'DD-MM-YYYY'), 'Spain', 'Forward', 'AFC Bournemouth', TO_DATE('02-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(20002, 'Artur Boruc', TO_DATE('27-NOV-1996', 'DD-MM-YYYY'), 'Poland', 'Goalkeeper', 'AFC Bournemouth', TO_DATE('22-AUG-2015', 'DD-MM-YYYY'));

insert into Players values
(20003, 'Adam Federici', TO_DATE('02-SEP-1994', 'DD-MM-YYYY'), 'Australia', 'Goalkeeper', 'AFC Bournemouth', TO_DATE('30-JUN-2013', 'DD-MM-YYYY'));

insert into Players values
(20004, 'Simon Francis', TO_DATE('17-MAR-1993', 'DD-MM-YYYY'), 'England', 'Defender', 'AFC Bournemouth', TO_DATE('21-JUL-2010', 'DD-MM-YYYY'));

insert into Players values
(20005, 'Steve Cook', TO_DATE('11-DEC-1990', 'DD-MM-YYYY'), 'England', 'Defender', 'AFC Bournemouth', TO_DATE('03-AUG-2011', 'DD-MM-YYYY'));

insert into Players values
(20006, 'Brad Smith', TO_DATE('21-MAY-1992', 'DD-MM-YYYY'), 'Australia', 'Defender', 'AFC Bournemouth', TO_DATE('10-DEC-2016', 'DD-MM-YYYY'));

insert into Players values
(20007, 'Dan Gosling', TO_DATE('18-JUL-1994', 'DD-MM-YYYY'), 'England', 'Midfielder', 'AFC Bournemouth', TO_DATE('09-JAN-2017', 'DD-MM-YYYY'));

insert into Players values
(20008, 'Ryan Fraser', TO_DATE('02-MAR-1993', 'DD-MM-YYYY'), 'Scotland', 'Midfielder', 'AFC Bournemouth', TO_DATE('07-AUG-2015', 'DD-MM-YYYY'));

insert into Players values
(20009, 'Kyle Taylor', TO_DATE('09-JAN-1996', 'DD-MM-YYYY'), 'England', 'Midfielder', 'AFC Bournemouth', TO_DATE('01-AUG-2017', 'DD-MM-YYYY'));

insert into Players values
(20010, 'Jordan Ibe', TO_DATE('27-OCT-1995', 'DD-MM-YYYY'), 'England', 'Midfielder', 'AFC Bournemouth', TO_DATE('25-AUG-2018', 'DD-MM-YYYY'));

insert into Players values
(20011, 'Sam Surridge', TO_DATE('11-FEB-1994', 'DD-MM-YYYY'), 'England', 'Forward', 'AFC Bournemouth', TO_DATE('30-JUL-2016', 'DD-MM-YYYY'));

insert into Players values
(20012, 'Dominic Solanke', TO_DATE('09-AUG-1996', 'DD-MM-YYYY'), 'England', 'Forward', 'AFC Bournemouth', TO_DATE('01-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(20013, 'Lewis Grabban', TO_DATE('20-MAR-1991', 'DD-MM-YYYY'), 'England', 'Forward', 'AFC Bournemouth', TO_DATE('31-JAN-2012', 'DD-MM-YYYY'));

insert into Players values
(30000, 'Nick Pope', TO_DATE('21-JAN-1995', 'DD-MM-YYYY'), 'England', 'Goalkeeper', 'Burnley', TO_DATE('09-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(30001, 'Chris Wood', TO_DATE('11-NOV-1994', 'DD-MM-YYYY'), 'New Zealand', 'Forward', 'Burnley', TO_DATE('11-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(30002, 'Tom Heaton', TO_DATE('11-MAY-1994', 'DD-MM-YYYY'), 'England', 'Goalkeeper', 'Burnley', TO_DATE('10-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(30003, 'Charlie Taylor', TO_DATE('01-JAN-1994', 'DD-MM-YYYY'), 'England', 'Defender', 'Burnley', TO_DATE('21-AUG-2012', 'DD-MM-YYYY'));

insert into Players values
(30004, 'Stephen Ward', TO_DATE('18-NOV-1994', 'DD-MM-YYYY'), 'Ireland', 'Defender', 'Burnley', TO_DATE('09-DEC-2016', 'DD-MM-YYYY'));

insert into Players values
(30005, 'Phil Bardsley', TO_DATE('09-FEB-1992', 'DD-MM-YYYY'), 'Scotland', 'Defender', 'Burnley', TO_DATE('03-JAN-2014', 'DD-MM-YYYY'));

insert into Players values
(30006, 'Robbie Brady', TO_DATE('22-MAY-1991', 'DD-MM-YYYY'), 'Ireland', 'Midfielder', 'Burnley', TO_DATE('12-AUG-2011', 'DD-MM-YYYY'));

insert into Players values
(30007, 'Steven Defour', TO_DATE('23-NOV-1990', 'DD-MM-YYYY'), 'Belgium', 'Midfielder', 'Burnley', TO_DATE('04-SEP-2015', 'DD-MM-YYYY'));

insert into Players values
(30008, 'Ashley Barnes', TO_DATE('29-AUG-1992', 'DD-MM-YYYY'), 'Austria', 'Forward', 'Burnley', TO_DATE('30-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(30009, 'Matej Vydra', TO_DATE('14-JAN-1985', 'DD-MM-YYYY'), 'Czech Republic', 'Forward', 'Burnley', TO_DATE('15-JAN-2013', 'DD-MM-YYYY'));

insert into Players values
(30010, 'Nakhi Wells', TO_DATE('10-DEC-1996', 'DD-MM-YYYY'), 'Bermuda', 'Forward', 'Burnley', TO_DATE('01-SEP-2018', 'DD-MM-YYYY'));

insert into Players values
(30011, 'Jeff Hendrick', TO_DATE('06-JUN-1990', 'DD-MM-YYYY'), 'Ireland', 'Midfielder', 'Burnley', TO_DATE('21-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(30012, 'Aaron Lennon', TO_DATE('23-OCT-1995', 'DD-MM-YYYY'), 'England', 'Midfielder', 'Burnley', TO_DATE('15-JAN-2016', 'DD-MM-YYYY'));

insert into Players values
(30013, 'Chris Wood', TO_DATE('11-NOV-1994', 'DD-MM-YYYY'), 'New Zealand', 'Forward', 'Burnley', TO_DATE('11-AUG-2013', 'DD-MM-YYYY'));

insert into Players values
(40000, 'Jesse Lingard', TO_DATE('15-DEC-1992', 'DD-MM-YYYY'), 'England', 'Midfielder', 'Manchester United', TO_DATE('01-JUL-2012', 'DD-MM-YYYY'));

insert into Players values
(40001, 'David de Gea', TO_DATE('18-NOV-2002', 'DD-MM-YYYY'), 'Spain', 'Goalkeeper', 'Manchester United', TO_DATE('02-JUN-2012', 'DD-MM-YYYY'));

insert into Players values
(40002, 'Victor Lindelof', TO_DATE('22-APR-2002', 'DD-MM-YYYY'), 'Sweden', 'Defender', 'Manchester United', TO_DATE('03-JUN-2012', 'DD-MM-YYYY'));

insert into Players values
(40003, 'Eric Bailly', TO_DATE('28-NOV-2004', 'DD-MM-YYYY'), 'Cote DIvoire', 'Defender', 'Manchester United', TO_DATE('06-JAN-2012', 'DD-MM-YYYY'));

insert into Players values
(40004, 'Phil Jones', TO_DATE('11-DEC-1998', 'DD-MM-YYYY'), 'England', 'Defender', 'Manchester United', TO_DATE('03-May-2010', 'DD-MM-YYYY'));

insert into Players values
(40005, 'Paul Pogba', TO_DATE('18-SEP-1997', 'DD-MM-YYYY'), 'France', 'Midfielder', 'Manchester United', TO_DATE('07-JUL-2012', 'DD-MM-YYYY'));

insert into Players values
(40006, 'Juan Mata', TO_DATE('07-OCT-2000', 'DD-MM-YYYY'), 'Spain', 'Midfielder', 'Manchester United', TO_DATE('02-JUN-2012', 'DD-MM-YYYY'));

insert into Players values
(40007, 'Michael Carrick', TO_DATE('18-APR-1996', 'DD-MM-YYYY'), 'England', 'Midfielder', 'Manchester United', TO_DATE('07-JUL-2012', 'DD-MM-YYYY'));

insert into Players values
(40008, 'Alexis Sanchez', TO_DATE('08-AUG-1992', 'DD-MM-YYYY'), 'Chile', 'Forward', 'Manchester United', TO_DATE('06-MAY-2012', 'DD-MM-YYYY'));

insert into Players values
(40009, 'Anthony Martial', TO_DATE('19-OCT-1994', 'DD-MM-YYYY'), 'France', 'Forward', 'Manchester United', TO_DATE('09-JUN-2009', 'DD-MM-YYYY'));

insert into Players values
(40010, 'Ethan Hamilton', TO_DATE('26-DEC-1996', 'DD-MM-YYYY'), 'Scotland', 'Forward', 'Manchester United', TO_DATE('02-May-2012', 'DD-MM-YYYY'));

insert into Players values
(50000, 'Marcos Alonso', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Defender', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50001, 'David Luiz', TO_DATE('09-FEB-1992', 'DD-MM-YYYY'), 'Brazil', 'Defender', 'Chelsea', TO_DATE('30-DEC-2016', 'DD-MM-YYYY'));

insert into Players values
(50002, 'Willy Caballero', TO_DATE('01-APR-1994', 'DD-MM-YYYY'), 'Spain', 'Goalkeeper', 'Chelsea', TO_DATE('01-AUG-2014', 'DD-MM-YYYY'));

insert into Players values
(50003, 'Victor Moses', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Nigeria', 'Defender', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50004, 'Gary Cahill', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'England', 'Defender', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50005, 'Ross Barkley', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Midfielder', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50006, 'Kyle Scott', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'United States', 'Midfielder', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50007, 'Daniel Drinkwater', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'England', 'Midfielder', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50008, 'Tiemoue Bakayoko', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Midfielder', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50009, 'Alvaro Morata', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Forward', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50010, 'Pedro', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Forward', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

insert into Players values
(50011, 'Olivier Giroud', TO_DATE('01-MAY-1994', 'DD-MM-YYYY'), 'Spain', 'Forward', 'Chelsea', TO_DATE('01-AUG-2016', 'DD-MM-YYYY'));

-- Injured

insert into Injured values
(50010, TO_DATE('11-DEC-2019', 'DD-MM-YYYY'), 'Broken Ankle');

insert into Injured values
(10000, TO_DATE('25-JAN-2020', 'DD-MM-YYYY'), 'Broken Ankle');

insert into Injured values
(20005, TO_DATE('07-FEB-2020', 'DD-MM-YYYY'), 'Broken Arm');

insert into Injured values
(20009, TO_DATE('12-FEB-2020', 'DD-MM-YYYY'), 'Broken Neck');

insert into Injured values
(30003, TO_DATE('21-AUG-2019', 'DD-MM-YYYY'), 'Fractured Arm');

-- Suspended

insert into Suspended values
(10001, 'Match Fixing', TO_DATE('25-DEC-2050', 'DD-MM-YYYY'));

insert into Suspended values
(10006, 'Match Fixing', TO_DATE('25-DEC-2050', 'DD-MM-YYYY'));

insert into Suspended values
(20004, 'Substance Usage', TO_DATE('01-JAN-2020', 'DD-MM-YYYY'));

insert into Suspended values
(20010, 'Substance Usage', TO_DATE('01-JAN-2022', 'DD-MM-YYYY'));

insert into Suspended values
(30006, 'Detrimental Conduct', TO_DATE('01-JUN-2030', 'DD-MM-YYYY'));

commit;