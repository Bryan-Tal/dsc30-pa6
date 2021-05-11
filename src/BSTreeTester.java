import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BSTreeTester {
    BSTree<Integer> firstTree;
    BSTree<Integer> secondTree;
    BSTree<Integer> thirdTree;
    @Before
    public void testInsert() throws Exception{
        firstTree = new BSTree<>();
        secondTree = new BSTree<>();
        thirdTree = new BSTree<>();

        secondTree.insert(3);
        secondTree.insert(8);
        secondTree.insert(6);
        secondTree.insert(7);
        secondTree.insert(9);
        secondTree.insert(1);
        secondTree.insert(2);

        firstTree.insert(5);
        firstTree.insert(4);
        firstTree.insert(6);
        firstTree.insert(2);
        firstTree.insert(9);
        firstTree.insert(3);
        firstTree.insert(1);
    }

    @Test
    public void testGetRoot() {
        int root1 = firstTree.getRoot().getKey();
        assertEquals(5,root1);
        int root2 = secondTree.getRoot().getKey();
        assertEquals(3,root2);
    }

    @Test
    public void testGetSize() {
        assertEquals(firstTree.getSize(),7);
        assertEquals(secondTree.getSize(),7);
        assertEquals(thirdTree.getSize(),0);
    }


    @Test
    public void testFindKey() {
        assertTrue(firstTree.findKey(5));
        assertTrue(secondTree.findKey(7));
        assertTrue(firstTree.findKey(9));
    }

    @Test
    public void testInsertDataAndFindDataList() {
        firstTree.insertData(5,1);
        assertTrue(firstTree.findDataList(5).get(0) == 1);
        secondTree.insertData(9,12);
        assertTrue(secondTree.findDataList(9).get(0) == 12);
        thirdTree.insert(1);
        thirdTree.insertData(1,123);
        assertTrue(thirdTree.findDataList(1).get(0) == 123);

    }

    @Test
    public void testFindHeight() {
        assertEquals(3,firstTree.findHeight());
        assertEquals(3,secondTree.findHeight());
        assertEquals(-1,thirdTree.findHeight());
        thirdTree.insert(1);
        assertEquals(0,thirdTree.findHeight());
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator1 = firstTree.iterator();
        String answer = "1234569";
        String question = "";
        while (iterator1.hasNext()) {
            question += Integer.valueOf(iterator1.next()) + "";
        }
        assertEquals(answer,question);

        Iterator<Integer> iterator2 = secondTree.iterator();
        String answer2 = "1\n2\n3\n6\n7\n8\n9\n";
        String question2 = "";
        while (iterator2.hasNext()) {
            question2 += Integer.valueOf(iterator2.next()) + "\n";
        }
        assertEquals(answer2,question2);

        Iterator<Integer> iterator3 = thirdTree.iterator();
        String answer3 = "";
        String question3 = "";
        while (iterator3.hasNext()) {
            question3 += Integer.valueOf(iterator3.next()) + "";
        }
        assertEquals(answer3,question3);

    }
}