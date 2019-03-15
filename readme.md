
building

running

in order to run this project, run the following after building:

```
java -jar target/mixtape-0.0.1-SNAPSHOT.jar <in> <change> <out>
```

h3. changes for large file sizes

in order to scale to large datasets and change orders, we xan implement
the following changes:

for large datasets

* import changes first
* convert the 'add playlist' and 'remove playlist' changes to maps
* append the 'add playlist to user' method to each user in the changelist
* possibly do this in two passes, verification and mutation
* parse the input file progressively (sax streaming style) and stream to tht output
* apply changes as they flow past
* track which changes have been used and alert on unused changes (operations that don't apply

for large changes and datasets

* import changes to three separate sets: new playlist, add song, remove playlist
* export these as maps
* process the input as a sax process
* for each item, apply changes (use id fields to link data to change requests)

