## DEMO GITHUB BROWSER USING PAGING LIBRARY 

This is a sample app that uses Android Architecture Components

## Introduction
### Search Activity 
Allows you to search repositories on Github. Each search result is kept in the database in RepoSearchResult table where the list of repository IDs are denormalized into a single column. The actual Repo instances live in the Repo table.

Each time a new page is fetched, the same RepoSearchResult record in the Database is updated with the new list of repository ids.

### Library 
- LiveData 
- Room
- Retrofit2
- Paging
