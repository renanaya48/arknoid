package game;


import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * HighScoresTable class implements Serializable.
 */
public class HighScoresTable implements Serializable {
    //members
    private List<ScoreInfo> higtScoreList;
    private int size;
    //private game.ScoreInfo[] higtScoreTable;


    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size1 the specified size.
     */
    public HighScoresTable(int size1) {
        this.size = size1;
        //this.higtScoreTable=new game.ScoreInfo[size1];
        this.higtScoreList = new ArrayList<ScoreInfo>(size1);
    }


    /**
     * @param score Add a high-score.
     */
    public void add(ScoreInfo score) {
        this.higtScoreList.add(score);
        Collections.sort(higtScoreList);
        if (higtScoreList.size() > this.size) {
            this.higtScoreList.remove(this.size);
        }
    }


    /**
     * @return table size.
     */
    public int size() {
        return this.higtScoreList.size();
    }

    /**
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.higtScoreList;
    }

    /**
     * @param score the score to check
     * @return rank of the current score: where will it be on the list if added?
     */
    public int getRank(int score) {

        if (this.higtScoreList != null) {
            for (int i = 0; i < this.higtScoreList.size(); i++) {
                if (score > this.higtScoreList.get(i).getScore()) {
                    return i + 1;
                }

            }
            if (this.higtScoreList.size() < size) {
                return this.higtScoreList.size() + 1;
            }
            return size + 1;

        }
        return 1;
    }


    /**
     * Clears the table.
     */
    public void clear() {
        this.higtScoreList.removeAll(getHighScores());
    }

    /**
     * Load table data from file..
     *
     * @param fileName file
     * @throws IOException when something get wrong
     */
    public void load(File fileName) throws IOException {
        HighScoresTable highScoresTable = loadFromFile(fileName);
        // ObjectOutputStream objectOutputStream=
        //   new ObjectOutputStream(new FileOutputStream(fileName));
        //objectOutputStream.writeObject(highScoresTable);
        if (highScoresTable != null) {
            this.higtScoreList = highScoresTable.getHighScores();
            this.size = highScoresTable.size;
        }

    }

    /**
     * Save table data to the specified file.
     *
     * @param fileName the specified file.
     * @throws IOException if someting get wrong
     */
    public void save(File fileName) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + fileName);
            }
        }
    }


    /**
     * Read a table from file and return it.
     *
     * @param fileName the file
     * @return the table
     */
    public static HighScoresTable loadFromFile(File fileName) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(fileName));
            HighScoresTable highScoresTable = (HighScoresTable) objectInputStream.readObject();
            return highScoresTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + fileName);
            return null;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + fileName);
            return null;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + fileName);
            }
        }
    }


    /**
     *
     * @return the way to print
     */
 /*   @Override
    public String toString(){
        String stringRank;
        for (int i=0; i<this.higtScoreList.size(); i++){
            stringRank=
        }
    }
*/
}
