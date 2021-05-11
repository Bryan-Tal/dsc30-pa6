/*
 * Name: Bryan Talavera
 * PID:  A14378593
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Bryan Talavera
 * @since  05/09/21
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    private static int RATING_CODE = 2;
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (int i = 0; i < cast.length; i++) {
                    String actor = cast[i];
                    movieTree.insert(actor);
                    ratingTree.insert(actor);
                    studioTree.insert(actor);
                    if (movieTree.findDataList(actor.toLowerCase()).contains(movie.toLowerCase()) == false) {
                        movieTree.insertData(actor,movie);
                    }
                    if (ratingTree.findDataList(actor.toLowerCase()).contains(rating) == false) {
                        ratingTree.insertData(actor,rating);
                    }
                }
                for (int i = 0; i < studios.length; i++) {
                    String studio = studios[i];
                    studioTree.insert(studio);
                    if (studioTree.findDataList(studio.toLowerCase()).contains(movie.toLowerCase()) == false) {
                        studioTree.insertData(studio,movie);
                    }
                }
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> queryToProcess = new LinkedList<>();

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        for (int i = 0; i < keys.length; i++) {
            try {
                LinkedList<String> tempString = searchTree.findDataList(keys[i]);
                for (int j = 0; j < tempString.size(); j++) {
                    if (!queryToProcess.contains(tempString.get(j)))
                        queryToProcess.add(tempString.get(j));
                }
            }catch (IllegalArgumentException e){
                continue;
            }
        }
        for (String key: keys) {
            try {
                LinkedList<String> tempString = searchTree.findDataList(key);
                queryToProcess.retainAll(tempString);
            }catch (IllegalArgumentException e){
                continue;
            }
        }
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful

        LinkedList<String> trashedValues = new LinkedList<>();
        // Printing Query results for all
        print(query,queryToProcess);
        //print individual query results
        for (int i = 0; i < keys.length; i++) {
            try {
                LinkedList<String> nodeInfo = searchTree.findDataList(keys[i]);
                LinkedList<String> tempString = new LinkedList<>();
                for (int j = 0; j < nodeInfo.size(); j++) {
                    if (queryToProcess.contains(nodeInfo.get(j)) == false &&
                            trashedValues.contains(nodeInfo.get(j)) == false) {
                        tempString.add(nodeInfo.get(j));
                        trashedValues.add(nodeInfo.get(j));

                    }
                }
                if (tempString.size() != 0) print(keys[i], tempString);
            }catch (IllegalArgumentException e){
                continue;
            }
        }
        if (trashedValues.isEmpty()){
            for (int i = 0; i < keys.length; i++) {
                print(keys[i],new LinkedList<>());
            }
        }


    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* TODO */
        // initialize search trees
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String extraArgs = "";
        // processing all extra arguments
        for (int i = 2; i < args.length; i++) {
            extraArgs += args[i] + " ";
        }

        // populate search trees
        populateSearchTrees(movieTree,studioTree,ratingTree,fileName);
        // choose the right tree to query
        if (searchKind == 0) searchMyQuery(movieTree,extraArgs);
        if (searchKind == 1) searchMyQuery(studioTree,extraArgs);
        if (searchKind == RATING_CODE) searchMyQuery(ratingTree,extraArgs);

    }
}
