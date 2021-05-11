/*
 * Name: Bryan Talavera
 * PID:  A1478593
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Bryan Talavera
 * @since  05/09/21
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {

            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            if (this.dataList.contains(data)) {
                this.dataList.remove(data);
                return true;
            }else {
                return false;
            }
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        BSTNode insertNode = new BSTNode(null, null, key);
        if (this.getRoot() == null) {
            this.root = insertNode;
            this.nelems++;
        } else {
            if (this.findKey(key)) return false;
            BSTNode root = this.getRoot();
            while (root != null) {
                if (key.compareTo(root.getKey()) < 0) {
                    if (root.getLeft() == null) {
                        root.setleft(insertNode);
                        this.nelems++;
                        root = null;
                    } else {
                        root = root.getLeft();
                    }
                } else {
                    if (root.getRight() == null) {
                        root.setright(insertNode);
                        this.nelems++;
                        root = null;
                    } else {
                        root = root.getRight();
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        BSTNode toFind = new BSTNode(null,null,key);
        if (key == null ) throw new NullPointerException();
        BSTNode currentNode = this.getRoot();
        while(currentNode != null){
            if (currentNode.getKey().compareTo(toFind.getKey()) == 0){
                return true;
            }else if(toFind.getKey().compareTo(currentNode.getKey()) < 0){
                currentNode = currentNode.getLeft();
            }else{
                currentNode = currentNode.getRight();
            }
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (!this.findKey(key)) throw new IllegalArgumentException();
        if (key == null || data == null) throw new NullPointerException();

        BSTNode node = this.getRoot();
        while (node != null) {
            if (key.compareTo(node.getKey()) == 0) {
                node.getDataList().add(data);
                node = null;
            } else if (key.compareTo(node.getKey()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) throw new NullPointerException();
        if (!findKey(key)) throw new IllegalArgumentException();
        BSTNode root = this.getRoot();
        while(root != null){
            if (key.compareTo(root.getKey()) == 0){
                return root.getDataList();
            }else if(key.compareTo(root.getKey()) < 0){
                root = root.getLeft();
            }else {
                root = root.getRight();
            }
        }
        return null;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (this.getRoot() == null) return -1;
        if (this.getRoot().getLeft() == null && this.getRoot().getRight() == null) return 0;
        return findHeightHelper(this.getRoot());
    }


    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) return -1;
        int lHeight = findHeightHelper(root.getLeft());
        int rHeight = findHeightHelper(root.getRight());
        return 1 + Math.max(lHeight,rHeight);
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> stack;

        public BSTree_Iterator() {
            this.stack = new Stack<>();
            leftPathTraverse(root);
        }

        public boolean hasNext() {
            if (stack.isEmpty()){
                return false;
            }
            return true;
        }


        public T next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            BSTNode node = stack.pop();
            leftPathTraverse(node.getRight());
            return node.getKey();
        }
        private void leftPathTraverse(BSTNode node){
            if (node != null){
                stack.push(node);
                leftPathTraverse(node.getLeft());
            }
        }

    }




    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }



    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
