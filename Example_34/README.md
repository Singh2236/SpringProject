# Migrating our database from H2 to AWS RDS

1. Create RDS in AWS.
2. Add dependency to our project.
3. Add DB endpoint credentials to application.properties
4. Scripts are stored inside Resource dir.
    1. Create table related scripts are stored in schema.sql file
    2. Insert Table related scripts are stored inside the data.sql file
5. I am using sqlElectron software to for database, so we use our scripts to make database and tables, and we also add
   data there for holidays.

Note: If the page is distorted, possible reasons would be, that the bootstrap, css or some other files are not able to
accessed from the path, I am trying to use get it. 