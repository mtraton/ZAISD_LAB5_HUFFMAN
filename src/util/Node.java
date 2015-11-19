package util;

import java.util.Comparator;

/**
 * Created by Rael on 19.11.2015.
 */
public class Node  {

        private HuffmanData data;

        //private Node parent;
        private Node left;
        private Node right;

    public Node() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public Node(HuffmanData data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node  left)
        {
            this.left = left;
        }

        public HuffmanData getData() {
            return data;
        }

        public void setData(HuffmanData data) {
            this.data = data;
        }

        /*public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }*/

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }



