// Description: This program models an MP3 track with its details and playback functionality.

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

/**
 * 
 * Represents an MP3 track with its details and playback functionality.
 *
 * The MP3 class encapsulates the properties and behavior of an MP3 track,
 * including its title, artist, album, year, and lyrics. It provides methods
 * to display track details, play the track, and stop the track playback.
 *
 * The class allows creating an MP3 track by specifying its details through
 * the constructor, or by using the default constructor and setting the
 * individual properties manually.
 */
class MP3 {
public:
    /**
     * Constructs an MP3 track with the given details.
     *
     * @param newTitle The title of the MP3 track.
     * @param newArtist The artist of the MP3 track.
     * @param newAlbum The album of the MP3 track.
     * @param newYear The year of the MP3 track.
     * @param newLyrics The lyrics of the MP3 track.
     */
    string title;
    string artist;
    string album;
    int year;
    string lyrics;
    
    MP3(string newTitle, string newArtist, string newAlbum, int newYear, string newLyrics) {
        title = newTitle;
        artist = newArtist;
        album = newAlbum;
        year = newYear;
        lyrics = newLyrics;
    }
    
    /**
     * Constructs an empty MP3 track with default values.
     *
     * The default constructor initializes the MP3 track with an empty title,
     * artist, album, and lyrics. The year is set to 0.
     */
    MP3() {
        title = "";
        artist = "";
        album = "";
        year = 0;
        lyrics = "";
    }
    
    /**
     * Displays the details of the MP3 track.
     *
     * This function prints the title, artist, album, year, and lyrics of the
     * MP3 track to the standard output.
     */
    void displayDetails();
    
    /**
     * Plays the MP3 track.
     *
     * This function starts playing the MP3 track by setting the isPlaying flag
     * to true and displaying the track details and lyrics. If the track is
     * already playing, it displays a message indicating that it is already
     * playing.
     */
    void play();
    
    /**
     * Stops the MP3 track.
     *
     * This function stops the playback of the MP3 track by setting the isPlaying
     * flag to false and displaying the stopped track details. If the track is
     * already stopped, it displays a message indicating that it is already
     * stopped.
     */
    void stop();

private:
    /* Flag indicating if the track is currently playing or not. */
    bool isPlaying = false;
    
    /**
     * Prints the lyrics of the MP3 track.
     *
     * This function prints the lyrics of the MP3 track to the standard output.
     */
    void printLyrics();
};

 /**
 * Displays the details of the MP3 object aside from lyrics.
 */
void MP3::displayDetails() {
    cout << "Track title: " << title << ", by " << artist << " in " << album << ", " << year << "." << endl;
}

  /**
 * Prints the lyrics of the MP3 song.
 */
void MP3::printLyrics() {
    cout << "Lyrics:" << endl;
    cout << lyrics << endl;
}

/**
 * Plays the MP3 song by setting the isPlaying flag to true and displaying the song details and lyrics.
 * If the song is already playing, it displays a message indicating that it is already playing.
 */
void MP3::play() {
    if (!isPlaying) {
        isPlaying = true;
        displayDetails();
        printLyrics();
    } else {
        cout << "The track is already playing." << endl;
    }
}

/**
 * Stops the MP3 song by setting the isPlaying flag to false and displaying the stopped song details.
 * If the song is already stopped, it displays a message indicating that it is already stopped.
 */
void MP3::stop() {
    if (isPlaying) {
        isPlaying = false;
        cout << "Track stopped: " << title << endl;
    } else {
        cout << "The track is not playing." << endl;
    }
}

/**
 * Adds track details from a file to the music collection.
 * The user is prompted to enter the filename.
 * The function reads the title, artist, album, and lyrics from the file and creates an MP3 object with those details.
 * The MP3 object is then added to the musicCollection vector.
 *
 * @param musicCollection The vector representing the music collection.
 */
void addTrackFromFile(vector<MP3>& musicCollection,  const string& filename = "") {
    string file;
    if (filename.empty()) {
        cout << "Enter the filename: ";
        cin >> file;
    } else {
        file = filename;
    }

    ifstream inputFile(file);
    if (!inputFile.is_open()) {
        cerr << "Error opening file: " << file << endl;
        return;
    }

    string title, artist, album, lyrics;
    int year;

    getline(inputFile, title);
    getline(inputFile, artist);
    getline(inputFile, album);
    inputFile >> year;
    inputFile.ignore();  // To skip the newline after the year
    getline(inputFile, lyrics, '\0'); // Read till EOF

    MP3 track(title, artist, album, year, lyrics);
    musicCollection.push_back(track);

    inputFile.close();
    cout << "Track added successfully.\n";
}

/**
 * Searches the music collection for tracks by artist (partial matching).
 * Displays the search results (tracks) along with their details.
 * If no tracks are found for the given artist, it displays a message indicating that.
 *
 * @param musicCollection The vector representing the music collection.
 * @param artist The artist name to search for.
 */
void searchByArtist( vector<MP3>& musicCollection,  string& artist) {
    bool found = false;
    for (auto& track : musicCollection) {
        if (track.artist.find(artist) != string::npos) {
            track.displayDetails();
            found = true;
        }
    }
    if (!found) {
        cout << "No tracks found for artist: " << artist << endl;
    }
}

/**
 * Searches the music collection for tracks by title (partial matching).
 * Displays the search results (tracks) along with their details.
 * If no tracks are found for the given title, it displays a message indicating that.
 *
 * @param musicCollection The vector representing the music collection.
 * @param title The title to search for.
 */
void searchByTitle(vector<MP3>& musicCollection, string& title) {
    bool found = false;
    for (auto& track : musicCollection) {
        if (track.title.find(title) != string::npos) {
            track.displayDetails();
            found = true;
        }
    }
    if (!found) {
        cout << "No tracks found for title: " << title << endl;
    }
}


/**
 * Searches the music collection for tracks by lyrics.
 * Displays the search results (tracks) along with their details.
 * If no tracks are found for the given lyrics, it displays a message indicating that.
 *
 * @param musicCollection The vector representing the music collection.
 * @param lyrics The lyrics to search for.
 */
void searchByLyrics(vector<MP3>& musicCollection, string& lyrics) {
    bool found = false;
    for (auto& track : musicCollection) {
        if (track.lyrics.find(lyrics) != string::npos) {
            track.displayDetails();
            found = true;
        }
    }
    if (!found) {
        cout << "No tracks found with the given lyrics." << endl;
    }
}

/**
 * Displays all tracks in the music collection.
 *
 * @param musicCollection The vector representing the music collection.
 */
void displayTracks(vector<MP3>& musicCollection) {
    for (auto &track : musicCollection) {
        track.displayDetails();
    }
}

/**
 * Prompts the user with a menu of options and performs the corresponding actions based on user input.
 * Manages the interaction with the music collection and performs various operations such as adding tracks,
 * searching tracks, playing songs, stopping songs, and displaying track details.
 *
 * @param musicCollection The vector representing the music collection.
 */
void displayMenu(vector<MP3>& musicCollection) {
    int choice;
    do {
        cout << endl;
        cout << "Menu:" << endl;
        cout << "1. Add track details from a file" << endl;
        cout << "2. Search tracks by artist" << endl;
        cout << "3. Search tracks by title" << endl;
        cout << "4. Search tracks by lyrics" << endl;
        cout << "5. Display all tracks" << endl;
        cout << "6. Play a song" << endl;
        cout << "7. Stop a song" << endl;
        cout << "8. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                addTrackFromFile(musicCollection);
                break;
            case 2:
                {
                    string artist;
                    cout << "Enter the artist: ";
                    cin.ignore();
                    getline(cin, artist);
                    searchByArtist(musicCollection, artist);
                }
                break;
            case 3:
                {
                    string title;
                    cout << "Enter the title: ";
                    cin.ignore();
                    getline(cin, title);
                    searchByTitle(musicCollection, title);
                }
                break;
            case 4:
                {
                    string lyrics;
                    cout << "Enter the lyrics: ";
                    cin.ignore();
                    getline(cin, lyrics);
                    searchByLyrics(musicCollection, lyrics);
                }
                break;
            case 5:
                displayTracks(musicCollection);
                break;
            case 6:
                {
                    cout << "List of tracks by index:" << endl;
                    for (int i = 0; i < musicCollection.size(); ++i) {
                        cout << "Index: " << i << ", Title: " << musicCollection[i].title << endl;
                    }

                    int trackIndex;
                    cout << "Enter the index of the song to play: ";
                    cin >> trackIndex;
                    if (trackIndex >= 0 && trackIndex < musicCollection.size()) {
                        musicCollection[trackIndex].play();
                    } else {
                        cout << "Invalid track index." << endl;
                    }
                }
                break;
            case 7:
                {
                    cout << "List of tracks by index:" << endl;
                    for (int i = 0; i < musicCollection.size(); ++i) {
                        cout << "Index: " << i << ", Title: " << musicCollection[i].title << endl;
                    }
                    int trackIndex;
                    cout << "Enter the index of the song to stop: ";
                    cin >> trackIndex;
                    if (trackIndex >= 0 && trackIndex < musicCollection.size()) {
                        musicCollection[trackIndex].stop();
                    } else {
                        cout << "Invalid track index." << endl;
                    }
                }
                break;
            case 8:
                cout << "Exiting the program..." << endl;
                break;
            default:
                cout << "Invalid choice. Please try again." << endl;
                break;
        }
    } while (choice != 8);
}

int main() {
    vector<MP3> musicCollection;

    // Open files mp3_1.txt to mp3_9.txt
    for (int i = 1; i <= 9; ++i) {
        string filename = "mp3_" + to_string(i) + ".txt";
        addTrackFromFile(musicCollection, filename);
    }

    displayMenu(musicCollection);

    return 0;
}