
# building

```sh
run mvn package. this will build the entire project
```

# running

in order to run this project, run the following after building:

```sh
java -jar target/mixtape-0.0.1-SNAPSHOT.jar <in> <change> <out>
```

## changes file

changes is a json file matching the following structure
```json
{
  addSongToPlaylist [
    {
    	songId: 1,
    	playlistId: 1
    }
  ],
  addUserPlaylist: [
    {
    	userId: 1,
    	playlist: [
    		{
    			playlist_id: 1,
    			songs: [1,2,3]
    		}
    	]
    }
  ],
  removePlaylist: [
    {
    	playlistId: 1
    }
  ]
}
```

## changes for large file sizes

in order to scale to large datasets and change orders, we xan implement
the following changes:


* import jukebox using SAX style parsing and store in a tuple store
* import changes (also sax style)
* apply the changes in sequence. track errors as they occur
* at the end, if no errors have occured, dump tuple store to outFile
* delete the tuple store


