# iDatabase
### Summary
This application is a webtool used for accessing a vast database of immunological data.
Data contained in this database consists of DEG (Differentially Expressed Genes) and SNP's
of the 200FG and 500FG cohorts.

The DEG database contains 18557 cases in which a gene was differentially expressed when under 
pressure of a stress factor.
Contained in the SNP database are over 10 million unique SNP's. For every SNP a number of different 
cell types and/or qtl types can be present bringing the total amount of SNP's to 150-200 million.

The aim of this tool is to avoid having to search through tens of files to find the SNP's or genes
you are looking for. Finding a single SNP is doable by hand, all SNP's for a given gene is not as
genes can cover over 50.000 SNP's. This tool does all the work for you and displays the results in 
a neat table or heatmap.

### Getting started
#### Data requirements

##### MongoDB
Data for this project has to be stored in a database. The database used to store all
the SNP data is [MongoDB](https://www.mongodb.com/). A getting started guide for MongoDB
can be found [here](https://docs.mongodb.com/getting-started/shell/). 

Filling the database can be done in one of two ways. The easiest way is to use [mongorestore](https://docs.mongodb.com/manual/reference/program/mongorestore/#bin.mongorestore)
to import a database dump of another database that was already filled. The other way is to fill the 
database yourself, while slightly less convenient still doable. To fill the database yourself take
a look at this [upload tool](https://github.com/veerdonk/mongoUploadIdatabaseData). By specifying 
a path to your data and which columns the relevant fields are the upload tool will add SNP's to your 
database. Note that this process can be very slow, for large datasets (5+ Gb) runtime can be more than one
 day depending on the power of your machine.

Additionally the DEG data can either be imported from a database dump or imported manually from a .csv using a custom tool
or something like [mongoimport](https://docs.mongodb.com/manual/reference/program/mongoimport/), object names should be kept 
the same.

### Features
#### DEG search
Finding differentially expressed genes can be done using the DEG search function. It can be used in two 
ways. By entering a gene name or Ensembl id directly or by entering a SNP id you're interested in.

If you enter a SNP id a check is performed to see on what gene the SNP occurs. This gene is then used for the 
database search.
##### Table
The results of the DEG search are displayed in a table which can be sorted, searched and downloaded

#### SNP search
When searching for SNP's you can enter either a SNP id to search the database directly or a gene name. If a 
gene name is entered the Ensembl [REST API](https://rest.ensembl.org/) is used to find all SNP's known to lie
on that gene in humans. These SNP's are then queried against the database where all matches are returned. As
most genes cover 30.000-50.000 SNP's this function is somewhat slower than searching for a single SNP.

If a broader search is required a region can be given in addition to the gene name to search a region of
x bases up- and downstream of the gene as well. This is limited to 5Mb as well as the size of the chromosome.

##### Table
Results of the SNP search are displayed in a table quite like the DEG search table that can also be sorted, searched and
downloaded. This table has an additional button group called heatmap which generate heatmaps

##### Heatmap
Heatmaps can be generated for every QTL. The SNP's are plotted against the cell types and colors are based on 
the -log10 of each SNP's p-value. The heatmaps are limited to 30 SNP's per page although a heatmap can be 
generated for all SNP's at once (it may not be easily readable).

### Future development
While in a working state this application is still work in progress. In the future more data and functionality 
can be added. 

##### functionality
* Filtering options on DEG and SNP tables
* Dynamic heatmaps generated for current table filters
* Provide linkouts based on gene name, SNP id, etc.
* More visualizations

