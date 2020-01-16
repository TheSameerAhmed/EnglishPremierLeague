# EnglishPremierLeague
Command line program to implement different types of SQL queries.

Purpose of the project was to search for arbitrary queries on an [Oracle database](https://en.wikipedia.org/wiki/Oracle_Database) that contains mock data of the EPL. In this project there were various types of queries such as projection, join, aggregation, selection and division. Oracle's JDBC driver was used to connect Java to the Oracle database. All the queries are searched using SQL and the database is based on an [entity-relationship model](https://en.wikipedia.org/wiki/Entity%E2%80%93relationship_model). 

A mock relational database of the English Premier League (Soccer) was briefly modelled in the following way:
- Some enitites in this database:
  - Players
  - Clubs
  - Managers
  - Stadiums
  - Matches
  - Referees
  - Fans
- Some relationships in this database:
  - Support (fans suppport a club)
  - Officiate
  - Manages
