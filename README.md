# iDatabase
### Summary
This application is a webtool used for accessing a vast database of immunological data.
Not only does this tool provide access to this data but also provides visualization for
the expression patterns of Diffrentially Expressed genes.

This is an ongoing project.

### Getting started
This application requires a MySQL database the sql script to create the necessary 
tables is available for download in the download section. Additionaly a .csv file 
containing some sample data useable for testing can be downloaded as well.

The application properties file will have to modified to include your database
credentials in oder to connect.

### Basic usage
Using the iDatabase application is pretty straightforward. There are two different 
types of searches. If you're interested in a specific gene and want to check whether
it's expression changes when in contact with certain stress factors you can use the 
gene name box. Just start typing the gene name or ensembl id and all of the options 
available in the database will be shown and can be auto completed.

The other way of searching is by SNP id. This search requires an internet connection.
simply input your SNP and press search. The application will search for the gene
associated with the SNP and if it's present in the database you'll be taken to the 
result page.

After a succesful search you'll be taken to the result page where you can see the
available information about your chosen gene. You can click 'search for another gene'
to be taken back to the search page.
