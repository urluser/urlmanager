1. Build application
mvn clean package

2. Run application
mvn spring-boot:run
Open a browser and go to http://localhost:8080

3. Manually add a mapping
Enter the abbreviation and URL and click "Add".

4. Automatically add a mapping
Enter only the URL and click "Add".
E.g
http://www.laughing-out-loud.com

5. Delete a mapping
Click "Delete" on the mapping you want to delete.

6. Use the mapped abbreviation:
Make sure your browser allows popup from local computer.
Open a new tab in the brower and enter the address:
http://localhost:8080/getUrl?abbreviation=<the url containing abbreviation>
E.g.
http://localhost:8080/getUrl?abbreviation=http://www.lol.com
